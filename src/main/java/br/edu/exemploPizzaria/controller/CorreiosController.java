package br.edu.exemploPizzaria.controller;

import br.edu.exemploPizzaria.CorreiosClient;
import br.edu.exemploPizzaria.soapclient.CalcPrecoPrazo;
import br.edu.exemploPizzaria.soapclient.CalcPrecoPrazoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CorreiosController {
    @Autowired
    private CorreiosClient correiosClient;

    @PostMapping("adicionarpizza/frete")
    @ResponseBody
    public CalcPrecoPrazoResponse frete(CalcPrecoPrazo precoPrazo){
        System.out.println(precoPrazo.toString());
        CalcPrecoPrazoResponse c = correiosClient.calcPrecoPrazo(precoPrazo);
        System.out.println(c);
        return c;
    }
}
