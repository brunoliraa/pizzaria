package br.edu.exemploPizzaria.services;

import br.edu.exemploPizzaria.exception.IngredienteInvalidoException;
import br.edu.exemploPizzaria.model.Ingrediente;
import br.edu.exemploPizzaria.model.enumerators.CategoriaIngrediente;
import br.edu.exemploPizzaria.repository.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class IngredienteService {

    @Autowired
    IngredienteRepository ingredienteRepository;


    public ModelAndView listarTodos() {
        ModelAndView modelAndView = new ModelAndView("ingredientes");
        modelAndView.addObject("ingredientes", ingredienteRepository.findAll());
        modelAndView.addObject("categorias", CategoriaIngrediente.values());
        return modelAndView;
    }


    public ModelAndView salvar(@Valid Ingrediente ingrediente, BindingResult result
                         ){
        //pode usar tbm  o ModelAtribbute no parametro do método
        if(result.hasErrors()){
            throw new IngredienteInvalidoException();
        }else{
            ingredienteRepository.save(ingrediente);
        }
        ModelAndView modelAndView = new ModelAndView("tabela-ingredientes");
        modelAndView.addObject("ingredientes", ingredienteRepository.findAll());
        modelAndView.addObject("categorias", CategoriaIngrediente.values());
        return modelAndView;
    }


    public ResponseEntity<String> delete(@PathVariable Long id){
        try{
            ingredienteRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


//    @ResponseBody
    public Optional<Ingrediente> buscarIngrediente(@PathVariable Long id){
        Optional<Ingrediente> ingrediente = ingredienteRepository.findById(id);
        return ingrediente;
    }
}
