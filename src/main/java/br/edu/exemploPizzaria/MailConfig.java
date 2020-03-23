package br.edu.exemploPizzaria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
//@PropertySource("classpath:env/mail.properties")
public class MailConfig {

    @Autowired
    private Environment env; //recebe os valores do properties

    //JavaMailSender facilita o envio de email
    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl(); //implementação da JavaMailSender

        mailSender.setHost(env.getProperty("mail.smtp.host"));
        mailSender.setPort(env.getProperty("mail.smtp.port", Integer.class)); //converte a porta pra integer
        mailSender.setUsername(env.getProperty("mail.smtp.username"));
        mailSender.setPassword(env.getProperty("mail.smtp.password"));

        //configurações relacionadas ao envio de email,a conexão no servidor smtp do gmail, para o envio de email
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", true); //autentificação ativada
        props.put("mail.smtp.starttls.enable", true); //transport layer security ativada
        props.put("mail.smtp.connectiontimeout", 10000); //10 segundos

        mailSender.setJavaMailProperties(props);

        return mailSender;
    }
}
