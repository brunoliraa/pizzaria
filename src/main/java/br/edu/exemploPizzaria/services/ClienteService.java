package br.edu.exemploPizzaria.services;

import br.edu.exemploPizzaria.model.Cliente;
import br.edu.exemploPizzaria.model.Mensagem;
import br.edu.exemploPizzaria.model.enumerators.CategoriaPizza;
import br.edu.exemploPizzaria.repository.ClienteRepository;
import br.edu.exemploPizzaria.repository.IngredienteRepository;
import br.edu.exemploPizzaria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigInteger;
import java.util.*;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private PizzaRepository pizzaRepository;
    @Autowired
    private IngredienteRepository ingredienteRepository;

    public ModelAndView salvarCliente(@Valid Cliente cliente, HttpSession session,
                                      BindingResult result, RedirectAttributes attributes){


        if(result.hasErrors()){

            attributes.addFlashAttribute("mensagem","Erro ao cadastrar");
            ModelAndView modelAndView = new ModelAndView("index");
            return modelAndView;

        }
        cliente.setSenha(new BCryptPasswordEncoder().encode(cliente.getSenha()));
        clienteRepository.save(cliente);
        session.setAttribute("cliente", cliente.getId());
//        Jedis jedis =  new Jedis("127.0.0.1", 6379);
//        jedis.set(cliente.getEmail(),session.getId(), SetParams.setParams());

        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("pizzas", pizzaRepository.findAll());
        modelAndView.addObject("categorias", CategoriaPizza.values());
        modelAndView.addObject("ingredientes", ingredienteRepository.findAll());

        return modelAndView;
    }

    public ModelAndView buscarTodos(){
        ModelAndView modelAndView = new ModelAndView("clientes");
          List<Cliente> clientes = clienteRepository.findAll();
         modelAndView.addObject("clientes",clientes);
         return modelAndView;
    }

    public ModelAndView buscarClientePorId(BigInteger id){
        ModelAndView modelAndView = new ModelAndView();
        Cliente cliente = clienteRepository.findById(id).orElseThrow(
                ()-> new UsernameNotFoundException("cliente com o id "+ id +" nao encontrado"));
        modelAndView.addObject("cliente", cliente);
        return modelAndView;
    }




    /* ---------teste email -----------*/
//    Map<String, Object> map = new HashMap<>();
//    Mensagem mensagem = new Mensagem();
//        mensagem.setRemetente("devteste1963@gmail.com");
//        mensagem.setDestinatarios(Arrays.asList("devteste1963@outlook.com"));
//        mensagem.setAssunto("teste");
////        map.put("Name", request.getName());
//        map.put("location", "br");
//        emailService.sendEmail(mensagem, map);
    /* ---------teste email -----------*/
}
