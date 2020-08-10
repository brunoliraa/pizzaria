package br.edu.exemploPizzaria.services;

import br.edu.exemploPizzaria.model.Cliente;
import br.edu.exemploPizzaria.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class MongoUserDetailsService implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente cliente = clienteRepository.findByEmail(email);
        if(cliente ==null){
            throw new UsernameNotFoundException("cliente não encontrado");
        }
        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList("cliente");

        return new User(cliente.getEmail(), cliente.getSenha(),authorityList);
    }
}
