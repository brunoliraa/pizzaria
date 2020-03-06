package br.edu.exemploPizzaria.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String recebe;

    private String formaPagamento;
    private BigDecimal valor;
    private BigDecimal frete;

    private BigDecimal valorTotal;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Pizza> listaPizza;

    private LocalDateTime dataCompra;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecebe() {
        return recebe;
    }

    public void setRecebe(String recebe) {
        this.recebe = recebe;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getFrete() {
        return frete;
    }

    public void setFrete(BigDecimal frete) {
        this.frete = frete;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<Pizza> getListaPizza() {
        return listaPizza;
    }

    public void setListaPizza(List<Pizza> listaPizza) {
        this.listaPizza = listaPizza;
    }

    public LocalDateTime getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDateTime dataCompra) {
        this.dataCompra = dataCompra;
    }
}
