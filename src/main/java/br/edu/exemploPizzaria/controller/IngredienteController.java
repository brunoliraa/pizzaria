package br.edu.exemploPizzaria.controller;

import br.edu.exemploPizzaria.IngredienteInvalidoException;
import br.edu.exemploPizzaria.model.Ingrediente;
import br.edu.exemploPizzaria.model.enumerators.CategoriaIngrediente;
import br.edu.exemploPizzaria.model.repository.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/ingrediente")
public class IngredienteController {

        @Autowired
        IngredienteRepository ingredienteRepository;

        @GetMapping
        public String listar(Model model) {
            model.addAttribute("ingredientes", ingredienteRepository.findAll());
            model.addAttribute("categorias", CategoriaIngrediente.values());
            return "ingredientes";
        }
        @PostMapping
        public String salvar(@Valid Ingrediente ingrediente, BindingResult result,
                             Model model){
            //pode usar tbm  o ModelAtribbute no parametro do método
            if(result.hasErrors()){
                throw new IngredienteInvalidoException();
            }else{
                ingredienteRepository.save(ingrediente);
            }
            model.addAttribute("ingredientes", ingredienteRepository.findAll());
            model.addAttribute("categorias", CategoriaIngrediente.values());
            return "tabela-ingredientes";
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<String>delete(@PathVariable Long id){
        try{
            ingredienteRepository.deleteById(id);
            return new ResponseEntity<String>(HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        }

    @GetMapping("/{id}")
    @ResponseBody
    public Optional<Ingrediente> buscarIngrediente(@PathVariable Long id){
        Optional<Ingrediente> ingrediente = ingredienteRepository.findById(id);
        return ingrediente;
    }

    }
