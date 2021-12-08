package io.github.edsonisaac.beemonitor.usuario.service;

import io.github.edsonisaac.beemonitor.infraestrutura.exception.ObjectNotFoundException;
import io.github.edsonisaac.beemonitor.infraestrutura.exception.ValidationException;
import io.github.edsonisaac.beemonitor.usuario.model.Usuario;
import io.github.edsonisaac.beemonitor.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario findByUsuario (String usuario) {
        return usuarioRepository.findByUsuario(usuario).orElseThrow(() -> {
            throw new ObjectNotFoundException("Usuário não encontrado! Usuário: " + usuario);
        });
    }

    public Usuario save (Usuario usuario) {

        if (usuario == null) {
            throw new ValidationException("Usuário nulo!");
        }

        if (validateUsuario(usuario)) {
            usuarioRepository.save(usuario);
        }

        return usuario;
    }

    private boolean validateUsuario (Usuario usuario) {

        try {
            Usuario usuario_findByUsuario = findByUsuario(usuario.getUsuario());

            if (usuario_findByUsuario != null && !usuario.equals(usuario_findByUsuario)) {
                throw new ValidationException("Usuário já cadastrado!");
            }
        } catch (ObjectNotFoundException ex) { }

        return true;
    }
}