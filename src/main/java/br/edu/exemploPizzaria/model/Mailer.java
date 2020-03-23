package br.edu.exemploPizzaria.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


//classe que realiza o envio do email
@Component
public class Mailer {

    @Autowired
    private JavaMailSender javaMailSender;

    public void enviar(Mensagem mensagem) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom(mensagem.getRemetente());
        simpleMailMessage.setTo(mensagem.getDestinatarios()
                .toArray(new String[mensagem.getDestinatarios().size()])); //se nao viraria um array de object e nao de string
        simpleMailMessage.setSubject(mensagem.getAssunto());
        simpleMailMessage.setText(mensagem.getCorpo());

        javaMailSender.send(simpleMailMessage);
    }

        //No main
//    Mailer mailer = applicationContext.getBean(Mailer.class);
//		mailer.enviar(new Mensagem("Alexandre Teste <alexandre.algaworks@gmail.com>",
//                                   Arrays.asList("Testes AlgaWorks <testes.algaworks@gmail.com>")
//				, "Aula Spring E-mail", "Olá! \n\n O envio de e-mail deu certo!"));
}
