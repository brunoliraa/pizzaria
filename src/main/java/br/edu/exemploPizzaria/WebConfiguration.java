//package br.edu.exemploPizzaria;
//
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.context.MessageSource;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.support.ReloadableResourceBundleMessageSource;
//import org.springframework.web.servlet.LocaleResolver;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
//import org.springframework.web.servlet.i18n.SessionLocaleResolver;
//
//import java.util.Locale;
//
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan(basePackages ="org.springframework.web.servlet.config.annotation" )
//public class WebConfiguration {
//
//    @Bean
//
//    public void addInterceptors(InterceptorRegistry interceptorRegistry){
//        LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
//        localeInterceptor.setParamName("lang");
//
//        interceptorRegistry.addInterceptor(localeInterceptor);
//    }
//
//    @Bean
//    public LocaleResolver localeResolver(){
//        SessionLocaleResolver resolver = new SessionLocaleResolver();
//        resolver.setDefaultLocale(new Locale("en_US"));
//        return resolver;
//    }
//
//    @Bean
//    public MessageSource messageSource(){
//        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//        messageSource.setBasename("src/main/resources/i18n/messages");
//        messageSource.setDefaultEncoding("UTF-8");
//        return messageSource;
//    }
//
//}
