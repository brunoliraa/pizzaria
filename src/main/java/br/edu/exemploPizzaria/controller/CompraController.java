package br.edu.exemploPizzaria.controller;


import br.edu.exemploPizzaria.model.Compra;
import br.edu.exemploPizzaria.model.Ingrediente;
import br.edu.exemploPizzaria.repository.CompraRepository;
import br.edu.exemploPizzaria.propertyeditors.PizzaPropertyEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CompraController {

    @Autowired
    PizzaPropertyEditor pizzaPropertyEditor;

    @Autowired
    CompraRepository compraRepository;

    @GetMapping("/")
    public List<Compra> buscarTodas(){
        return compraRepository.findAll();
    }

    @PostMapping("/comprar")
    public String salvarCompra(Compra compra){
        compraRepository.save(compra);
        return "carrinho";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.registerCustomEditor(Ingrediente.class, pizzaPropertyEditor);
    }

}
