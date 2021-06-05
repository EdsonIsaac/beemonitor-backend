package io.github.edsonisaac.monitoramentocomeia.infraestrutura.security.service;

import io.github.edsonisaac.monitoramentocomeia.infraestrutura.exception.ObjectNotFoundException;
import io.github.edsonisaac.monitoramentocomeia.infraestrutura.security.model.UserSecurity;
import io.github.edsonisaac.monitoramentocomeia.infraestrutura.service.Facade;
import io.github.edsonisaac.monitoramentocomeia.usuario.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final Facade facade;

    @Autowired
    public UserDetailsService(Facade facade) {
        this.facade = facade;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            Usuario usuario = facade.usuarioFindByUsuario(username);
            return new UserSecurity(usuario.getId(), usuario.getUsuario(), usuario.getSenha());
        } catch (ObjectNotFoundException ex) { }

        throw new UsernameNotFoundException("Usuário não encontrado!");
    }
}
