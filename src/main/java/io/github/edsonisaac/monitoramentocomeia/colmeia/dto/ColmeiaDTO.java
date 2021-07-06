package io.github.edsonisaac.monitoramentocomeia.colmeia.dto;

import io.github.edsonisaac.monitoramentocomeia.colmeia.model.Colmeia;
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
    private String telefone;
    private LocalDate dataCadastro;
    private Set<MedicaoDTO> medicoes;

    public static ColmeiaDTO toDTO (Colmeia colmeia) {

        return new ColmeiaDTO(
            colmeia.getId(),
            colmeia.getCodigo(),
            colmeia.getTelefone(),
            colmeia.getDataCadastro(),
            colmeia.getMedicoes().stream().map(x -> MedicaoDTO.toDTO(x)).collect(Collectors.toSet())
        );
    }
}