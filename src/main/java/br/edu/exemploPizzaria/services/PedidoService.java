package br.edu.exemploPizzaria.services;

import br.edu.exemploPizzaria.model.*;
import br.edu.exemploPizzaria.model.enumerators.CategoriaPizza;
import br.edu.exemploPizzaria.propertyeditors.PizzaPropertyEditor;
import br.edu.exemploPizzaria.repository.*;
import br.edu.exemploPizzaria.soapclient.CalcPrecoPrazo;
import br.edu.exemploPizzaria.soapclient.CalcPrecoPrazoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
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
import java.util.List;

@Service
public class PedidoService {

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
    @Autowired
    private CorreiosClient correiosClient;

    private Pedido pedido;
    private Compra compra;
    private BigDecimal total = new BigDecimal(0);
    private List<Pizza> lista = new ArrayList<>();
    private Cliente cliente;

    public String adicionarPizza(@PathVariable Long id) {
        Pizza p = pizzaRepository.findPizzaById(id);
        buscarUsuarioLogado();
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
        return "redirect:/home";
    }


    @PostMapping("/finalizarpedido")
    public ModelAndView finalizarPedido(Pedido pedido) {
            ModelAndView modelAndView = new ModelAndView("pedidoFinalizado");

//            pedidoRepository.save(pedido);

            return modelAndView;

    }

    public ModelAndView exibirCarrinho(){
        buscarUsuarioLogado();
        ModelAndView modelAndView = new ModelAndView("carrinho");
        System.out.println(lista);
        modelAndView.addObject("listaPizzas",lista);
        modelAndView.addObject("cliente", cliente);
        modelAndView.addObject("total",total);

        return modelAndView;
    }

    public ModelAndView voltarHome(){
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("pizzas", pizzaRepository.findAll());
        modelAndView.addObject("categorias", CategoriaPizza.values());
        modelAndView.addObject("ingredientes", ingredienteRepository.findAll());
        return modelAndView;
    }

    public String alterarQuantidade(@PathVariable Long id, @PathVariable Integer acao) {
        for (Pizza p : lista) {
            if (p.getId().equals(id)) {
                if (acao == 1) {
                    p.setQuantidade(p.getQuantidade() + 1);
                    total = total.add(p.getPreco());
                    break;
                } else
                    p.setQuantidade(p.getQuantidade() - 1);
                total = total.subtract(p.getPreco());
                if(p.getQuantidade()==0){
                    lista.remove(p);
                }
                if(total.compareTo(BigDecimal.ZERO) ==0){
                    total = new BigDecimal(0);
                }
                if(total.compareTo(BigDecimal.ZERO)<0){
                    total= new BigDecimal(0);
                }


                break;
            }
        }

        return "redirect:/carrinho";
    }

    public String removerPizza(@PathVariable Long id) {
        Pizza pizza = pizzaRepository.findPizzaById(id);
        int quantidade=1;
        for(Pizza p : lista){
            if(p.getId() ==id){
                quantidade= p.getQuantidade();
                System.out.println(p.getQuantidade());}
        }
        BigDecimal valorRemovido = pizza.getPreco();
        valorRemovido =valorRemovido.multiply(new BigDecimal(quantidade));
        total = total.subtract(valorRemovido);
        System.out.println(total);
        pizza.setQuantidade(0);
        lista.remove(pizza);

        if (total.compareTo(BigDecimal.ZERO) < 0) {

            total = new BigDecimal(0);
        }
        if(lista.isEmpty()){
            total = new BigDecimal(0);
        }

        return "redirect:/carrinho";
    }

    private void buscarUsuarioLogado(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            String email = authentication.getName();
            cliente = clienteRepository.findByEmail(email);
        }
    }

    public String frete(CalcPrecoPrazo precoPrazo){
        System.out.println(precoPrazo.toString());
        CalcPrecoPrazoResponse c = correiosClient.calcPrecoPrazo(precoPrazo);
        System.out.println(c.toString());
        return "carrinho";
    }


    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.registerCustomEditor(Ingrediente.class, pizzaPropertyEditor);
    }
}
