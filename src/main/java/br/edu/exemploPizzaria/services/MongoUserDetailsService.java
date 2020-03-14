package br.edu.exemploPizzaria.services;

import br.edu.exemploPizzaria.model.Cliente;
import br.edu.exemploPizzaria.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class MongoUserDetailsService implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente cliente = clienteRepository.findByEmail(email);
        if(cliente ==null){
            throw new UsernameNotFoundException("cliente não encontrado");
        }
        //List<GrantedAuthority> authorityList = Arrays.asList(new SimpleGrantedAuthority("cliente"));
        //GrantedAuthority authority = new SimpleGrantedAuthority("cliente");
        //cliente.getAuthorities().add(new SimpleGrantedAuthority("cliente"));
        //return new Cliente(authorityList);
        //return new Cliente(cliente.getEmail(), cliente.getSenha());
        return cliente;
    }
}
