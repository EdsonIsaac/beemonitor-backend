package io.github.edsonisaac.monitoramentocomeia.usuario.dto;

import io.github.edsonisaac.monitoramentocomeia.usuario.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioDTO {

    private Long id;
    private String nome;
    private String usuario;

    public static UsuarioDTO toDto (Usuario usuario) {

        return new UsuarioDTO(
            usuario.getId(),
            usuario.getNome(),
            usuario.getUsuario()
        );
    }
}