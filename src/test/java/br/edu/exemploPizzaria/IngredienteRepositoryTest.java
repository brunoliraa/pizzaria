package br.edu.exemploPizzaria;


import br.edu.exemploPizzaria.model.Ingrediente;
import br.edu.exemploPizzaria.repository.IngredienteRepository;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)// pra usar o postgres
public class IngredienteRepositoryTest {

    @Autowired
    private IngredienteRepository ingredienteRepository;
    @Rule
    public ExpectedException thrown;

    @Test
    public void salvarIngrediente(){
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNome("tomate");
        ingredienteRepository.save(ingrediente);
        System.out.println(ingrediente);
//      Assertions.assertThat(ingrediente.getNome()).isEqualTo("tomate");
        Assertions.assertThat(ingrediente.getId()).isNotNull();

    }
}
