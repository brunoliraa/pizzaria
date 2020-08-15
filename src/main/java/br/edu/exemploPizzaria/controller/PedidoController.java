package br.edu.exemploPizzaria.controller;
import br.edu.exemploPizzaria.model.Pedido;
import br.edu.exemploPizzaria.services.PedidoService;
import br.edu.exemploPizzaria.soapclient.CalcPrecoPrazo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class PedidoController {
   @Autowired
   private PedidoService pedidoService;

    @GetMapping("/adicionarpizza/{id}")
    public String adicionarPizza(@PathVariable Long id, Model model) {
       return pedidoService.adicionarPizza(id);
    }

    @PostMapping("/finalizarpedido")
    public ModelAndView finalizarPedido(Pedido pedido) {
        return pedidoService.finalizarPedido(pedido);
    }

    @GetMapping("/carrinho")
    public ModelAndView exibirCarrinho(){
        return pedidoService.exibirCarrinho();
    }

    @GetMapping("/continuar")
    public ModelAndView voltar(){
        return pedidoService.voltarHome();
    }

    @GetMapping("/alterarQuantidade/{id}/{acao}")
    public String alterarQuantidade(@PathVariable Long id, @PathVariable Integer acao) {
            return pedidoService.alterarQuantidade(id, acao);
        }

    @GetMapping("/removerPizza/{id}")
    public String removerPizza(@PathVariable Long id) {
        return pedidoService.removerPizza(id);
    }

    @PostMapping("/frete")

    public String frete(CalcPrecoPrazo precoPrazo){
        return pedidoService.frete(precoPrazo);
    }

}
