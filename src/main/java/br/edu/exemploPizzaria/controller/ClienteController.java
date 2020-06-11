package br.edu.exemploPizzaria.controller;


import br.edu.exemploPizzaria.model.Cliente;
import br.edu.exemploPizzaria.model.Mensagem;
import br.edu.exemploPizzaria.model.enumerators.CategoriaPizza;
import br.edu.exemploPizzaria.model.repository.ClienteRepository;
import br.edu.exemploPizzaria.model.repository.IngredienteRepository;
import br.edu.exemploPizzaria.model.repository.PizzaRepository;
import br.edu.exemploPizzaria.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ClienteController {


    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    PizzaRepository pizzaRepository;
    @Autowired
    IngredienteRepository ingredienteRepository;
    @Autowired
    private EmailService emailService;

    @PostMapping("/cliente")
    public String salvarCliente(@Valid Cliente cliente, HttpSession session, Model model,
                                BindingResult result, RedirectAttributes attributes){
       if(result.hasErrors()){

           attributes.addFlashAttribute("mensagem","Erro ao cadastrar");
           return "index";

       }
        cliente.setSenha(new BCryptPasswordEncoder().encode(cliente.getSenha()));
        clienteRepository.save(cliente);
        session.setAttribute("cliente", cliente);
//        Jedis jedis =  new Jedis("127.0.0.1", 6379);
//        jedis.set(cliente.getEmail(),session.getId(), SetParams.setParams());

        /* ---------teste email -----------*/
        Map<String, Object> map = new HashMap<>();
        Mensagem mensagem = new Mensagem();
        mensagem.setRemetente("devteste1963@gmail.com");
        mensagem.setDestinatarios(Arrays.asList("brunoliracz@gmail.com"));
        mensagem.setAssunto("teste");
//        map.put("Name", request.getName());
        map.put("location", "br");
        emailService.sendEmail(mensagem, map);
        /* ---------teste email -----------*/

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
//            Jedis jedis =  new Jedis("127.0.0.1", 6379);
//            jedis.set(c.getEmail(),session.getId(), SetParams.setParams());

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
        @GetMapping("/logoutuseless")
         public String logout(HttpSession session){
            session.getAttribute("cliente");
//            Jedis jedis =  new Jedis("127.0.0.1", 6379);
//            jedis.del();
            session.invalidate();
            return "index";

        }

        @GetMapping("/home")
        public ModelAndView goHome(){
            ModelAndView model = new ModelAndView("home");
            model.addObject("pizzas", pizzaRepository.findAll());
            model.addObject("categorias", CategoriaPizza.values());
            model.addObject("ingredientes", ingredienteRepository.findAll());
            return model;
        }



    }