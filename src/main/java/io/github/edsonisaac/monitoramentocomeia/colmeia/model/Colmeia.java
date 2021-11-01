package io.github.edsonisaac.monitoramentocomeia.colmeia.model;

import io.github.edsonisaac.monitoramentocomeia.infraestrutura.model.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Colmeia extends AbstractEntity {

    @NotEmpty(message = "{campo.codigo.invalido}")
    private String codigo;

    @NotEmpty(message = "{campo.telefone.invalido}")
    private String telefone;

    @Column(updatable = false)
    private LocalDate dataCadastro;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Medicao> medicoes;

    @PrePersist
    private void prePersist() {
        this.dataCadastro = LocalDate.now(ZoneId.of("America/Sao_Paulo"));
    }
}