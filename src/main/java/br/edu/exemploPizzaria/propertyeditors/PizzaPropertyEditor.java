package br.edu.exemploPizzaria.propertyeditors;

import br.edu.exemploPizzaria.model.Pizza;
import br.edu.exemploPizzaria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class PizzaPropertyEditor extends PropertyEditorSupport {

    @Autowired
    PizzaRepository pizzaRepository;

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Long idPizza = Long.parseLong(text);
       Pizza pizza= pizzaRepository.getOne(idPizza);
        setValue(pizza);
    }


}
