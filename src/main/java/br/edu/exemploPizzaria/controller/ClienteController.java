package br.edu.exemploPizzaria.controller;


import br.edu.exemploPizzaria.model.Cliente;
import br.edu.exemploPizzaria.model.enumerators.CategoriaPizza;
import br.edu.exemploPizzaria.repository.ClienteRepository;
import br.edu.exemploPizzaria.repository.IngredienteRepository;
import br.edu.exemploPizzaria.repository.PizzaRepository;
import br.edu.exemploPizzaria.services.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigInteger;


@Controller
public class ClienteController {


    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    PizzaRepository pizzaRepository;
    @Autowired
    IngredienteRepository ingredienteRepository;
    @Autowired
    private ClienteService clienteService;

    @PostMapping("/cliente")
    public ModelAndView salvarCliente(@Valid Cliente cliente, HttpSession session,
                                BindingResult result, RedirectAttributes attributes){

      return clienteService.salvarCliente(cliente, session, result, attributes);
    }

    @GetMapping("/clientes")
    public ModelAndView buscarTodos(){
        return clienteService.buscarTodos();

    }

    @GetMapping("/cliente/{id}")
    public ModelAndView buscarCliente(@PathVariable BigInteger id){
        return clienteService.buscarClientePorId(id)
        ;
    }


        @GetMapping("/home")
        public ModelAndView irParaHome(){
            ModelAndView model = new ModelAndView("home");
            model.addObject("pizzas", pizzaRepository.findAll());
            model.addObject("categorias", CategoriaPizza.values());
            model.addObject("ingredientes", ingredienteRepository.findAll());
            return model;
        }



    }