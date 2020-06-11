package br.edu.exemploPizzaria.model;

import br.edu.exemploPizzaria.model.enumerators.CategoriaIngrediente;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Ingrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @NotEmpty
    private String nome;
    @NotNull
    @Enumerated(EnumType.STRING)
    private CategoriaIngrediente categoria;

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

    public CategoriaIngrediente getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaIngrediente categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ingrediente)) return false;
        Ingrediente that = (Ingrediente) o;
        return Objects.equals(getNome(), that.getNome()) &&
                getCategoria() == that.getCategoria();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome(), getCategoria());
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
