package br.edu.exemploPizzaria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PizzariaController {

    @GetMapping("/pizzaria")
    public String exibirIndex(){
        return "index";
    }
}
