package io.github.edsonisaac.monitoramentocomeia.infraestrutura.service;

import io.github.edsonisaac.monitoramentocomeia.colmeia.model.Colmeia;
import io.github.edsonisaac.monitoramentocomeia.colmeia.service.ColmeiaService;
import io.github.edsonisaac.monitoramentocomeia.usuario.model.Usuario;
import io.github.edsonisaac.monitoramentocomeia.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Facade {

    private final ColmeiaService colmeiaService;
    private final UsuarioService usuarioService;

    @Autowired
    public Facade(ColmeiaService colmeiaService, UsuarioService usuarioService) {
        this.colmeiaService = colmeiaService;
        this.usuarioService = usuarioService;
    }

    /************************************************** COLMEIA **************************************************/

    public List<Colmeia> colmeiaFindAll() {
        return colmeiaService.findAll();
    }

    public Colmeia colmeiaFindByCodigo (String codigo) {
        return colmeiaService.findByCodigo(codigo);
    }

    public Colmeia colmeiaFindById (Long id) {
        return colmeiaService.findById(id);
    }

    public void colmeiaDelete(Colmeia colmeia) {
        colmeiaService.delete(colmeia);
    }

    public Colmeia colmeiaSave (Colmeia colmeia) {
        return colmeiaService.save(colmeia);
    }

    public Colmeia colmeiaUpdate (Colmeia colmeia) {
        return colmeiaService.update(colmeia);
    }

    /************************************************** USUÁRIO **************************************************/

    public Usuario usuarioFindByUsuario (String usuario) {
        return usuarioService.findByUsuario(usuario);
    }

    public Usuario usuarioSave (Usuario usuario) {
        return usuarioService.save(usuario);
    }
}