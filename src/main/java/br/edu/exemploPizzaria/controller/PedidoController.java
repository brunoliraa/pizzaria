package br.edu.exemploPizzaria.controller;

import br.edu.exemploPizzaria.model.Ingrediente;
import br.edu.exemploPizzaria.model.Pedido;
import br.edu.exemploPizzaria.model.Pizza;
import br.edu.exemploPizzaria.model.enumerators.CategoriaPizza;
import br.edu.exemploPizzaria.model.repository.IngredienteRepository;
import br.edu.exemploPizzaria.model.repository.PedidoRepository;
import br.edu.exemploPizzaria.model.repository.PizzaRepository;
import br.edu.exemploPizzaria.propertyeditors.PizzaPropertyEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class PedidoController {
    Pedido pedido;
    List<Pizza> lista = new ArrayList<>();
    @Autowired
    PizzaRepository pizzaRepository;
    @Autowired
    IngredienteRepository ingredienteRepository;
    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    PizzaPropertyEditor pizzaPropertyEditor;

    @GetMapping("/adicionarpizza/{id}")
    public String adicionarPizza(@PathVariable Long id, Model model) {
        System.out.println(id);
        Pizza p = pizzaRepository.findPizzaById(id);
        lista.add(p);
        model.addAttribute("pizzas", pizzaRepository.findAll());
        model.addAttribute("categorias", CategoriaPizza.values());
        model.addAttribute("ingredientes", ingredienteRepository.findAll());
        model.addAttribute("listaPizzas", lista);

        return "home";
    }

    @PostMapping("/adicionarpizza")
        public String adicionar(Pizza pizza, Model model){
            Pizza p = pizzaRepository.findPizzaById(pizza.getId());
        lista.add(p);
        model.addAttribute("pizzas", pizzaRepository.findAll());
        model.addAttribute("categorias", CategoriaPizza.values());
        model.addAttribute("ingredientes", ingredienteRepository.findAll());
        model.addAttribute("listaPizzas", lista);

            return "home";
        }

    @PostMapping("/finalizarpedido")
    public String finalizarPedido(String recebedor) {

        try {
            pedido = new Pedido();
            pedido.setPizzas(lista);
            pedido.setRecebedor(recebedor);
            pedidoRepository.save(pedido);
            lista.clear();
            return "pedidoFinalizado";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "ok";
        }
    }

    @GetMapping("/carrinho")
    public ModelAndView exibirHome(){
        ModelAndView modelAndView = new ModelAndView("ok");
        modelAndView.addObject("listapizzas",lista);

        return modelAndView;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.registerCustomEditor(Ingrediente.class, pizzaPropertyEditor);
    }
    //select pizza.nome from pizza, pedido_pizzas where pizza.id = pedido_pizzas.pizzas_id
}
