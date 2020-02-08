package br.edu.exemploPizzaria.propertyeditors;

import br.edu.exemploPizzaria.model.Ingrediente;
import br.edu.exemploPizzaria.model.repository.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class IngredientePropertyEditor extends PropertyEditorSupport {

    @Autowired
    private IngredienteRepository ingredienteRepository;

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Long idIngrediente = Long.parseLong(text);
        Ingrediente ingrediente = ingredienteRepository.getOne(idIngrediente);
        setValue(ingrediente);
    }

}
