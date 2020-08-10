package br.edu.exemploPizzaria.repository;

import br.edu.exemploPizzaria.model.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ClienteRepository extends MongoRepository<Cliente, BigInteger> {
    Cliente findByEmail(String email);
}
