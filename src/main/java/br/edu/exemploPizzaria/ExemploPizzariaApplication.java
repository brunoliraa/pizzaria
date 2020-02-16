package br.edu.exemploPizzaria;

import br.edu.exemploPizzaria.model.Cliente;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.math.BigInteger;

@SpringBootApplication
public class ExemploPizzariaApplication {


	public static void main(String[] args) {
		SpringApplication.run(ExemploPizzariaApplication.class, args);
	}

}
