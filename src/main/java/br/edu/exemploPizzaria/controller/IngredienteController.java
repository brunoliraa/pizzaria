package br.edu.exemploPizzaria.controller;

import br.edu.exemploPizzaria.exception.IngredienteInvalidoException;
import br.edu.exemploPizzaria.model.Ingrediente;
import br.edu.exemploPizzaria.model.enumerators.CategoriaIngrediente;
import br.edu.exemploPizzaria.repository.IngredienteRepository;
import br.edu.exemploPizzaria.services.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/ingrediente")
public class IngredienteController {

        @Autowired
        IngredienteRepository ingredienteRepository;
        @Autowired
        private IngredienteService ingredienteService;

        @GetMapping
        public String listar(Model model) {
            model.addAttribute("ingredientes", ingredienteRepository.findAll());
            model.addAttribute("categorias", CategoriaIngrediente.values());
            return "ingredientes";
        }
        @PostMapping
        public ModelAndView salvar(@Valid Ingrediente ingrediente, BindingResult result
                                   ){
            return ingredienteService.salvar(ingrediente,result);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<String>delete(@PathVariable Long id){
            return ingredienteService.delete(id);
        }

    @GetMapping("/{id}")
    @ResponseBody
    public Optional<Ingrediente> buscarIngrediente(@PathVariable Long id){
        return ingredienteService.buscarIngrediente(id);
    }

    }
