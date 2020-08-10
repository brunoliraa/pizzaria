package br.edu.exemploPizzaria.services;

import br.edu.exemploPizzaria.exception.IngredienteInvalidoException;
import br.edu.exemploPizzaria.model.Ingrediente;
import br.edu.exemploPizzaria.model.Pizza;
import br.edu.exemploPizzaria.model.enumerators.CategoriaPizza;
import br.edu.exemploPizzaria.propertyeditors.IngredientePropertyEditor;
import br.edu.exemploPizzaria.repository.IngredienteRepository;
import br.edu.exemploPizzaria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Service
public class PizzaService {


    @Autowired
    private IngredientePropertyEditor ingredientePropertyEditor;
    @Autowired
    PizzaRepository pizzaRepository;
    @Autowired
    IngredienteRepository ingredienteRepository;

    public ModelAndView listarPizzas() {
        ModelAndView modelAndView = new ModelAndView("pizza/pizzas");
        modelAndView.addObject("pizzas", pizzaRepository.findAll());
        modelAndView.addObject("categorias", CategoriaPizza.values());
        modelAndView.addObject("ingredientes", ingredienteRepository.findAll());
        return modelAndView;

    }

    public ModelAndView salvarPizza(@Valid @ModelAttribute Pizza pizza, BindingResult bindingResult){

        if ( bindingResult.hasErrors() ) {
            throw new IngredienteInvalidoException();
        } else {
            pizzaRepository.save(pizza);
        }
        ModelAndView modelAndView = new ModelAndView("tabela-pizzas");
        modelAndView.addObject("pizzas", pizzaRepository.findAll());

        return modelAndView;
    }

    public ResponseEntity<String> deletarPizza(@PathVariable Long pizzaId){
        try {
            pizzaRepository.deleteById(pizzaId);
            return new ResponseEntity<String>(HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);

        }

    }


    public ResponseEntity<Pizza> buscarPizza(@PathVariable Long pizzaId){
        Pizza pizza = pizzaRepository.findPizzaById(pizzaId);
        return new ResponseEntity<>(pizza, HttpStatus.OK);
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.registerCustomEditor(Ingrediente.class, ingredientePropertyEditor);
    }
}
