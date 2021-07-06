package io.github.edsonisaac.monitoramentocomeia.colmeia.dto;

import io.github.edsonisaac.monitoramentocomeia.colmeia.model.Medicao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MedicaoDTO {
    private Long id;
    private Double temperatura;
    private Double umidade;
    private Double peso;
    private LocalDateTime dataHoraCadastro;

    public static MedicaoDTO toDTO (Medicao medicao) {

        return new MedicaoDTO(
            medicao.getId(),
            medicao.getTemperatura(),
            medicao.getUmidade(),
            medicao.getPeso(),
            medicao.getDataHoraCadastro()
        );
    }
}