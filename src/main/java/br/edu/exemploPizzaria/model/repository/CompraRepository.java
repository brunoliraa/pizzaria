package br.edu.exemploPizzaria.model.repository;

import br.edu.exemploPizzaria.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraRepository extends JpaRepository<Compra, Long> {
}
