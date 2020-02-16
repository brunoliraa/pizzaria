package br.edu.exemploPizzaria.model.repository;

import br.edu.exemploPizzaria.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
