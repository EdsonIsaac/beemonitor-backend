package io.github.edsonisaac.beemonitor.usuario.model;

import io.github.edsonisaac.beemonitor.infraestrutura.model.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Usuario extends AbstractEntity {
    private String nome;
    private String usuario;
    private String senha;
}