package br.edu.exemploPizzaria.config;

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



@Configuration
@EnableWebSecurity//(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private MongoUserDetailsService mongoUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.inMemoryAuthentication().withUser("admin@admin.com").password("admin").roles("cliente");
        auth.userDetailsService(mongoUserDetailsService).passwordEncoder(bCryptPasswordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/pizza", "/carrinho").hasAuthority("cliente")
                .antMatchers("/ingrediente").hasAuthority("admin")
                .anyRequest().permitAll()
                .and().formLogin()
                .loginPage("/pizzaria").loginProcessingUrl("/autenticar").defaultSuccessUrl("/home").failureUrl("/pizzaria?erro")
                .usernameParameter("email").passwordParameter("senha")
                .and().logout()
                .logoutUrl("/logout").logoutSuccessUrl("/pizzaria?logout=true");

    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
