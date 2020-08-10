package br.edu.exemploPizzaria.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;


//@EnableWebMvc
//@ComponentScan(basePackages ="org.springframework.web.servlet.config.annotation" )
//@Order(2)
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

//    @Bean
//    public void addInterceptors(InterceptorRegistry interceptorRegistry){
//        LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
//        localeInterceptor.setParamName("lang");
//
//        interceptorRegistry.addInterceptor(localeInterceptor);
//    }
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        LocaleChangeInterceptor changeInterceptor =  new LocaleChangeInterceptor();
        changeInterceptor.setParamName("lang");
        return changeInterceptor;
    }

    @Bean
    public LocaleResolver localeResolver(){
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        //resolver.setDefaultLocale(new Locale("en_US"));
        resolver.setDefaultLocale(Locale.US);
        return resolver;
    }

//    @Bean
//    public MessageSource messageSource(){
//        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//        messageSource.setBasename("classpath:/I18n/messages");
//        //messageSource.setUseCodeAsDefaultMessage(true);
//        //messageSource.setCacheSeconds(3600); atualiza o cache 1 vez por hora
//        messageSource.setDefaultEncoding("UTF-8");
//        return messageSource;
//    }

}
