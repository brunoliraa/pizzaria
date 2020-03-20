package br.edu.exemploPizzaria.controller;

import br.edu.exemploPizzaria.model.*;
import br.edu.exemploPizzaria.model.enumerators.CategoriaPizza;
import br.edu.exemploPizzaria.model.repository.*;
import br.edu.exemploPizzaria.propertyeditors.PizzaPropertyEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;

@Controller
public class PedidoController {
    private Pedido pedido;
    private Compra compra;
    private BigDecimal total = new BigDecimal(0);
    private List<Pizza> lista = new ArrayList<>();
    private Cliente cliente;
    @Autowired
    PizzaRepository pizzaRepository;
    @Autowired
    IngredienteRepository ingredienteRepository;
    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    PizzaPropertyEditor pizzaPropertyEditor;

    @Autowired
    CompraRepository compraRepository;
    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping("/adicionarpizza/{id}")
    public String adicionarPizza(@PathVariable Long id, Model model) {
        //System.out.println(id);
        Pizza p = pizzaRepository.findPizzaById(id);
        buscarUsuario();

        int quantidade =0;
        quantidade ++;
        int controle =0;
        for (Pizza pizza : lista){
            if(pizza.getId() == p.getId()){
                pizza.setQuantidade(pizza.getQuantidade()+1);
                total = total.add(p.getPreco());
                controle =1;
                break;
            }
        }
        if(controle ==0) {
            p.setQuantidade(p.getQuantidade()+1);
            total = total.add(p.getPreco());

            lista.add(p);

        }
        model.addAttribute("pizzas", pizzaRepository.findAll());
        model.addAttribute("categorias", CategoriaPizza.values());
        model.addAttribute("ingredientes", ingredienteRepository.findAll());
        model.addAttribute("listaPizzas", lista);
        model.addAttribute("total", total);
        model.addAttribute("cliente",cliente);
        System.out.println(cliente.getNome());
        return "ok";
    }

//    @PostMapping("/adicionarpizza")
//        public String adicionar(Pizza pizza, Model model){
//            Pizza p = pizzaRepository.findPizzaById(pizza.getId());
//        lista.add(p);
//        model.addAttribute("pizzas", pizzaRepository.findAll());
//        model.addAttribute("categorias", CategoriaPizza.values());
//        model.addAttribute("ingredientes", ingredienteRepository.findAll());
//        model.addAttribute("listaPizzas", lista);
//
//
//            return "home";
//        }

    @PostMapping("/finalizarpedido")
    public String finalizarPedido(String recebedor, Model model) {

        try {
//            pedido = new Pedido();
//            pedido.setPizzas(lista);
//            pedido.setRecebedor(recebedor);
//            pedidoRepository.save(pedido);
//            lista.clear();
            compra = new Compra();
            compra.setDataCompra(LocalDateTime.now());
            compra.setListaPizza(lista);
            compra.setValorTotal(total);
            compraRepository.save(compra);
            model.addAttribute("listaPizzas", lista);




            return "pedidoFinalizado";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "ok";
        }
    }

    @GetMapping("/carrinho")
    public ModelAndView exibirHome(){
        buscarUsuario();
        ModelAndView modelAndView = new ModelAndView("ok");
        modelAndView.addObject("listapizzas",lista);
        modelAndView.addObject("cliente", cliente);


        return modelAndView;
    }

    @GetMapping("/continuar")
    public String voltar(Model model){
        model.addAttribute("pizzas", pizzaRepository.findAll());
        model.addAttribute("categorias", CategoriaPizza.values());
        model.addAttribute("ingredientes", ingredienteRepository.findAll());
        return "home";
    }

    @GetMapping("/alterarQuantidade/{id}/{acao}")
    public ModelAndView alterarQuantidade(@PathVariable Long id, @PathVariable Integer acao, Model model) {
        ModelAndView modelAndView = new ModelAndView("ok");
        //Pizza pizza = pizzaRepository.findPizzaById(id);
        for (Pizza p : lista) {
            if (p.getId().equals(id)) {
                if (acao == 1) {
                    Pizza pizza = pizzaRepository.findPizzaById(id);
                    p.setQuantidade(p.getQuantidade() + 1);
                    total = total.add(pizza.getPreco());
                } else
                    p.setQuantidade(p.getQuantidade() - 1);
                if (total.compareTo(BigDecimal.ZERO) > 0) {
                    total = total.subtract(p.getPreco());
                } else
                    total = total.add(new BigDecimal(0));

                break;
            }
        }

            model.addAttribute("pizzas", pizzaRepository.findAll());
            model.addAttribute("categorias", CategoriaPizza.values());
            model.addAttribute("ingredientes", ingredienteRepository.findAll());
            model.addAttribute("listaPizzas", lista);
            model.addAttribute("total", total);
            return exibirHome();
            //return modelAndView;
        }


    
    @GetMapping("/removerPizza/{id}")
    public ModelAndView removerPizza(@PathVariable Long id, Model model){
        Pizza pizza =pizzaRepository.findPizzaById(id);
        for(Pizza p : lista){
            if(p.getId().equals(pizza.getId())){
                lista.remove(pizza);

                if(total.compareTo(BigDecimal.ZERO)>0) {
                    total = total.subtract(p.getPreco().multiply(new BigDecimal(p.getQuantidade())));
                    if (total.compareTo(BigDecimal.ZERO)==0){
                        total = new BigDecimal(0);
                    }
                }else
                total = new BigDecimal(0);

            }
            break;
                }



        return exibirHome();
    }

    //fazer isso em um service
    private void buscarUsuario(){
      //importação do Spring
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            String email = authentication.getName();
            cliente = clienteRepository.findByEmail(email);
        }
    }


    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.registerCustomEditor(Ingrediente.class, pizzaPropertyEditor);
    }
    //select pizza.nome from pizza, pedido_pizzas where pizza.id = pedido_pizzas.pizzas_id
}
