package br.edu.exemploPizzaria.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Pizza> pizzas;

    private String recebedor;

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

    public String getRecebedor() {
        return recebedor;
    }

    public void setRecebedor(String recebedor) {
        this.recebedor = recebedor;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", pizzas=" + pizzas +
                ", recebedor='" + recebedor + '\'' +
                '}';
    }
}
