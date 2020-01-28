package br.edu.exemploPizzaria.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PizzaController {

    @GetMapping("/pizza")
    public String aaa(){
        return "a";
    }
}
