package br.edu.exemploPizzaria;

import br.edu.exemploPizzaria.services.MongoUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity//(debug = true)
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) //nao sei
//@EnableConfigurationProperties
//@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private DataSource dataSource;

    @Autowired
    private MongoUserDetailsService mongoUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.inMemoryAuthentication().withUser("admin@admin.com").password("admin").roles("cliente");
        auth.userDetailsService(mongoUserDetailsService).passwordEncoder(bCryptPasswordEncoder());






    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.authorizeRequests().antMatchers("/login", "/cliente").hasRole("cliente").
        http.csrf().disable()
                .authorizeRequests() //pra começar a configurar as requisições
                .antMatchers("/ingrediente").hasAuthority("cliente")
                .antMatchers("/pizza").hasAuthority("admin")
                .anyRequest().permitAll()
                .and().formLogin()
                .loginPage("/pizzaria").loginProcessingUrl("/autenticar").defaultSuccessUrl("/home").failureUrl("/pizzaria?erro")
                .usernameParameter("email").passwordParameter("senha")
                .and().logout()
                .logoutUrl("/logout").logoutSuccessUrl("/pizzaria");

//
//        http.csrf().disable()
//                .authorizeRequests().antMatchers("/ingrediente").hasRole("admin")
//                .anyRequest().permitAll()
//                .and()
//                .formLogin().loginPage("/pizzaria")
//                .loginProcessingUrl("/autenticar").defaultSuccessUrl("/home").failureUrl("/pizza")
//                .usernameParameter("email").passwordParameter("senha")
//                .and()
//                .logout()
//                .logoutSuccessUrl("/pizza");

//        http.csrf().disable().authorizeRequests()
//                .antMatchers("/ingrediente").hasRole("admin")
//                .antMatchers("/pizza").hasRole("cliente")
//                .and()
//                .formLogin().failureUrl("/pizzaria")
//                .loginProcessingUrl("/autenticar").defaultSuccessUrl("/home");
//                //.usernameParameter("email").passwordParameter("senha");

    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
