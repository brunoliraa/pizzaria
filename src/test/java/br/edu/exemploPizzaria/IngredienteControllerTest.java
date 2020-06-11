package br.edu.exemploPizzaria;


import br.edu.exemploPizzaria.model.Ingrediente;
import br.edu.exemploPizzaria.model.repository.IngredienteRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

//@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class IngredienteControllerTest{

    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;
    @MockBean
    private IngredienteRepository ingredienteRepository;
    @Autowired
    private MockMvc mockMvc;

    @TestConfiguration
    static class Config {
        @Bean
        public RestTemplateBuilder restTemplateBuilder() {
            return new RestTemplateBuilder().basicAuthentication("admin","admin");
        }
    }
    @Test
    public void ingredientesComAutenticacaoErradaDeveRetornar401(){
        restTemplate =restTemplate.withBasicAuth("1","1");
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/ingrediente",String.class);
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(401);
    }
    @Test
    public void ingredienteComAutenticacaoCorretaDeveRetornar200(){
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNome("mussarela");
        ingrediente.setId(20L); //o L é pra dizer que o 20 é um Long
        List<Ingrediente> list = new ArrayList<>();
        list.add(ingrediente);
        //quando vc executar o método vc deve retornar uma lista de ingredientes
        BDDMockito.when(ingredienteRepository.findAll()).thenReturn(list);
        //pode trocar de <String> pra ingrediente, Ingrediente.class
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/ingrediente",String.class);
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(200);
    }
}
