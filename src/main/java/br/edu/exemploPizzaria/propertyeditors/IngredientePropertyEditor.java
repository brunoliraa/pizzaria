package br.edu.exemploPizzaria.propertyeditors;

import br.edu.exemploPizzaria.model.Ingrediente;
import br.edu.exemploPizzaria.model.repository.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

//Spring data jpa já resolve esse problema de não salvar objeto dentro de outro objeto
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
