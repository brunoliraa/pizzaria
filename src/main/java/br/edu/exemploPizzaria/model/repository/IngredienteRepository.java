package br.edu.exemploPizzaria.model.repository;

import br.edu.exemploPizzaria.model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {
}
