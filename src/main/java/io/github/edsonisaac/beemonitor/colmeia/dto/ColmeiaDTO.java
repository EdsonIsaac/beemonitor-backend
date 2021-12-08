package io.github.edsonisaac.beemonitor.colmeia.dto;

import io.github.edsonisaac.beemonitor.colmeia.model.Colmeia;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ColmeiaDTO {
    private Long id;
    private String codigo;
    private LocalDate dataCadastro;
    private Set<MedicaoDTO> medicoes;

    public static ColmeiaDTO toDTO (Colmeia colmeia) {

        return new ColmeiaDTO(
            colmeia.getId(),
            colmeia.getCodigo(),
            colmeia.getDataCadastro(),
            colmeia.getMedicoes() != null ? colmeia.getMedicoes().stream().map(x -> MedicaoDTO.toDTO(x)).collect(Collectors.toSet()) : null
        );
    }
}