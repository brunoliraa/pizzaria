package br.edu.exemploPizzaria.model.repository;

import br.edu.exemploPizzaria.model.Imagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagemRepository extends JpaRepository<Imagem, Long> {

}
