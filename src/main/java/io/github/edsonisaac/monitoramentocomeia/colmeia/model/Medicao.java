package io.github.edsonisaac.monitoramentocomeia.colmeia.model;

import io.github.edsonisaac.monitoramentocomeia.infraestrutura.model.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Medicao extends AbstractEntity {

    @NotNull(message = "{campo.temperatura.invalido}")
    private Double temperatura;

    @NotNull(message = "{campo.umidade.invalido}")
    private Double umidade;

    @NotNull(message = "{campo.peso.invalido}")
    private Double peso;

    @Column(updatable = false)
    private LocalDateTime dataHoraCadastro;

    @PrePersist
    private void prePersist() {
        this.dataHoraCadastro = LocalDateTime.now();
    }
}