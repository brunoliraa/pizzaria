package br.edu.exemploPizzaria.model;

import br.edu.exemploPizzaria.model.enumerators.CategoriaPizza;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull //funciona pra tudo que for objeto
    @NotEmpty // só funciona pra String
    private String nome;
    @NotNull
    private BigDecimal preco;
    @NotNull
    @Enumerated(EnumType.STRING)
    private CategoriaPizza categoria;
    @ManyToMany(fetch = FetchType.EAGER) // é a pior opção porque sempre vai entregar os ingredientes
    private Set<Ingrediente> ingredientes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public CategoriaPizza getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaPizza categoria) {
        this.categoria = categoria;
    }

    public Set<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(Set<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pizza)) return false;
        Pizza pizza = (Pizza) o;
        return Objects.equals(getNome(), pizza.getNome()) &&
                Objects.equals(getPreco(), pizza.getPreco());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome(), getPreco());
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                ", categoria=" + categoria +
                ", ingredientes=" + ingredientes +
                '}';
    }
}
