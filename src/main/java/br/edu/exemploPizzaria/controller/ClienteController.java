package br.edu.exemploPizzaria.controller;


import br.edu.exemploPizzaria.model.Cliente;
import br.edu.exemploPizzaria.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@Controller
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;

    @PostMapping("/cliente")
    public String salvarCliente(@RequestBody Cliente cliente){
            clienteRepository.save(cliente);
        return "";
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
}
