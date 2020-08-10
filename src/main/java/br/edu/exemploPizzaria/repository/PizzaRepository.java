package br.edu.exemploPizzaria.repository;

import br.edu.exemploPizzaria.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    Pizza findPizzaById(Long id);
}
