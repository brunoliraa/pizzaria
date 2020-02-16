package br.edu.exemploPizzaria.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Pedido {
    @Id
    private Long id;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Pizza> pizzas;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

}
