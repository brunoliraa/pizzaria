package br.edu.exemploPizzaria.controller;


import br.edu.exemploPizzaria.model.Cliente;
import br.edu.exemploPizzaria.model.enumerators.CategoriaPizza;
import br.edu.exemploPizzaria.model.repository.ClienteRepository;
import br.edu.exemploPizzaria.model.repository.IngredienteRepository;
import br.edu.exemploPizzaria.model.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;

@Controller
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    PizzaRepository pizzaRepository;
    @Autowired
    IngredienteRepository ingredienteRepository;

    @PostMapping("/cliente")
    public String salvarCliente(Cliente cliente,  HttpSession session, Model model){

        clienteRepository.save(cliente);
        session.setAttribute("cliente", cliente);
        Jedis jedis =  new Jedis("127.0.0.1", 6379);
        jedis.set(cliente.getEmail(),session.getId(), SetParams.setParams());

        model.addAttribute("pizzas", pizzaRepository.findAll());
        model.addAttribute("categorias", CategoriaPizza.values());
        model.addAttribute("ingredientes", ingredienteRepository.findAll());

            return "home";
    }

    @GetMapping("/clientes")
    public String buscarTodos(){
        clienteRepository.findAll();
        return "";
    }

    @GetMapping("/cliente/{id}")
    public String buscarCliente(@PathVariable BigInteger id){
        clienteRepository.findById(id);
        return "";
    }


    @PostMapping("/login")
    public String logar(Cliente cliente, HttpSession session, Model model) {
        try {
            Cliente c = clienteRepository.findByEmail(cliente.getEmail());
            session.setAttribute("cliente", c);
            Jedis jedis =  new Jedis("127.0.0.1", 6379);
            jedis.set(c.getEmail(),session.getId(), SetParams.setParams());

            if (c.getSenha().equals(cliente.getSenha())) {
                model.addAttribute("pizzas", pizzaRepository.findAll());
                model.addAttribute("categorias", CategoriaPizza.values());
                model.addAttribute("ingredientes", ingredienteRepository.findAll());
                return "home";
            }
            else return "index";
        }catch(Exception ex){
                System.out.println(ex.getMessage());
                return "index";
            }


        }
        @GetMapping("/logout")
         public String logout(HttpSession session){
            session.getAttribute("cliente");
//            Jedis jedis =  new Jedis("127.0.0.1", 6379);
//            jedis.del();
            session.invalidate();
            return "index";

        }


    }