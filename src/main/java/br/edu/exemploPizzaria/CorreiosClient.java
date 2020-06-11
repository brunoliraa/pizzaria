package br.edu.exemploPizzaria;

import br.edu.exemploPizzaria.soapclient.CalcPrecoPrazo;
import br.edu.exemploPizzaria.soapclient.CalcPrecoPrazoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

@Service
public class CorreiosClient {

    @Autowired
    private Jaxb2Marshaller marshaller;

    private WebServiceTemplate template;

    public CalcPrecoPrazoResponse calcPrecoPrazo(CalcPrecoPrazo precoPrazo){
        template = new WebServiceTemplate(marshaller);
        CalcPrecoPrazoResponse calcPrecoPrazo = (CalcPrecoPrazoResponse)
                template.marshalSendAndReceive("http://ws.correios.com.br/calculador/CalcPrecoPrazo.asmx?wsdl",precoPrazo);
        return calcPrecoPrazo;
    }
}
