package br.edu.exemploPizzaria.controller;


import br.edu.exemploPizzaria.IngredienteInvalidoException;
import br.edu.exemploPizzaria.model.Ingrediente;
import br.edu.exemploPizzaria.model.Pizza;
import br.edu.exemploPizzaria.model.enumerators.CategoriaPizza;
import br.edu.exemploPizzaria.model.repository.IngredienteRepository;
import br.edu.exemploPizzaria.model.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import br.edu.exemploPizzaria.propertyeditors.IngredientePropertyEditor;

import javax.validation.Valid;


@Controller
public class PizzaController {
    @Autowired
    private IngredientePropertyEditor ingredientePropertyEditor;
    @Autowired
    PizzaRepository pizzaRepository;
    @Autowired
    IngredienteRepository ingredienteRepository;

    @GetMapping("/pizza")
    public String listarPizzas(Model model) {
        model.addAttribute("pizzas", pizzaRepository.findAll());
        model.addAttribute("categorias", CategoriaPizza.values());
        model.addAttribute("ingredientes", ingredienteRepository.findAll());
        return "pizza/pizzas";

    }

    @PostMapping("/pizza")
    public String salvarPizza(
            Model model,
            @Valid @ModelAttribute Pizza pizza,
            BindingResult bindingResult){

        if ( bindingResult.hasErrors() ) {
            throw new IngredienteInvalidoException();

        } else {
            pizzaRepository.save(pizza);

        }
        model.addAttribute("pizzas", pizzaRepository.findAll());
        return "tabela-pizzas";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.registerCustomEditor(Ingrediente.class, ingredientePropertyEditor);
    }
}
