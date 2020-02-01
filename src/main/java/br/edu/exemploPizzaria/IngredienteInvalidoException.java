package br.edu.exemploPizzaria;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IngredienteInvalidoException extends RuntimeException {

}
