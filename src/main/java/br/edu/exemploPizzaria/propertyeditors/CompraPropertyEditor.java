package br.edu.exemploPizzaria.propertyeditors;

import br.edu.exemploPizzaria.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.beans.PropertyEditorSupport;

public class CompraPropertyEditor extends PropertyEditorSupport {
    @Autowired
    CompraRepository compraRepository;

    @Override
    public void setAsText(String text) throws IllegalArgumentException{
        Long id;
    }
}
