package br.edu.exemploPizzaria.controller;



import br.edu.exemploPizzaria.model.Pizza;

import br.edu.exemploPizzaria.services.PizzaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @GetMapping("/pizza")
    public ModelAndView listarPizzas() {
        return pizzaService.listarPizzas();

    }

    @PostMapping("/pizza")
    public ModelAndView salvarPizza(@Valid @ModelAttribute Pizza pizza, BindingResult bindingResult){

      return pizzaService.salvarPizza(pizza, bindingResult);
    }

    @DeleteMapping("/pizza/{pizzaId}")
    public ResponseEntity<String> deletarPizza(@PathVariable Long pizzaId){
        return pizzaService.deletarPizza(pizzaId);
    }

    @GetMapping("/pizza/{pizzaId}")
    public ResponseEntity<Pizza> buscarPizza(@PathVariable Long pizzaId){
        return pizzaService.buscarPizza(pizzaId);
    }

}
