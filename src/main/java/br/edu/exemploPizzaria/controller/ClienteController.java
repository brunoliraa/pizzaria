package br.edu.exemploPizzaria.controller;


import br.edu.exemploPizzaria.model.Cliente;
import br.edu.exemploPizzaria.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;

@Controller
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping("/cadastro")
    public String cadastro(){
        return "clienteCadastro";
    }

    @PostMapping("/cliente")
    public String salvarCliente(Cliente cliente){
            clienteRepository.save(cliente);
        return "ok";
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

    @GetMapping("/login")
    public String openLogin(){
        return "clienteCadastro";
    }

    @PostMapping("/login")
    public String logar(Cliente cliente, HttpSession session) {
        try {
            Cliente c = clienteRepository.findByEmail(cliente.getEmail());
            session.setAttribute("cliente", c);
            //Jedis jedis =  new Jedis("127.0.0.1", 6379);
            //jedis.set(c.getEmail(),session.getId(), SetParams.setParams());

            if (c.getSenha().equals(cliente.getSenha())) {
                return "pizza/pizzas";
            }
            else return "clienteCadastro";
        }catch(Exception ex){
                System.out.println(ex.getMessage());
                return "clienteCadastro";
            }


        }
    }