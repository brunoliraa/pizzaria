package br.edu.exemploPizzaria.controller;

import br.edu.exemploPizzaria.model.Cliente;
import br.edu.exemploPizzaria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PizzariaController {

    @Autowired
    private PizzaRepository pizzaRepository;

    Cliente cliente = new Cliente();
    @GetMapping("/pizzaria")
    public ModelAndView exibirIndex(@RequestParam(required = false, name = "logout") String logout){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("cliente", cliente);
        modelAndView.addObject("pizzas", pizzaRepository.findAll());

        return modelAndView;
    }
}
