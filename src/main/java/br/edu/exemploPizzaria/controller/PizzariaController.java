package br.edu.exemploPizzaria.controller;

import br.edu.exemploPizzaria.model.Cliente;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PizzariaController {
    Cliente cliente = new Cliente();
    @GetMapping("/pizzaria")
    public String exibirIndex(Model model){
        model.addAttribute("cliente",cliente);
        return "index";
    }
}
