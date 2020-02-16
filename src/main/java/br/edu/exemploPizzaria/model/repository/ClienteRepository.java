package br.edu.exemploPizzaria.model.repository;

import br.edu.exemploPizzaria.model.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

public interface ClienteRepository extends MongoRepository<Cliente, BigInteger> {
}
