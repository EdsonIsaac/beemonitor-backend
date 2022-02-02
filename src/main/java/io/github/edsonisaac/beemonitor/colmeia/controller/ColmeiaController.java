package io.github.edsonisaac.beemonitor.colmeia.controller;

import io.github.edsonisaac.beemonitor.colmeia.dto.ColmeiaDTO;
import io.github.edsonisaac.beemonitor.colmeia.model.Colmeia;
import io.github.edsonisaac.beemonitor.colmeia.model.Medicao;
import io.github.edsonisaac.beemonitor.infraestrutura.service.Facade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(value = "/colmeias")
public class ColmeiaController {

    private final Facade facade;

    @Autowired
    public ColmeiaController(Facade facade) {
        this.facade = facade;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ColmeiaDTO> findAll (@RequestParam(required = false) boolean oneMedicao) {

        if (oneMedicao) {
            return facade.colmeiaFindAll()
                .stream()
                .map(colmeia -> {

                    if (colmeia.getMedicoes().size() > 0) {
                        colmeia.setMedicoes(Stream.of(colmeia.getMedicoes().stream().sorted((a, b) -> a.getDataHoraCadastro().compareTo(b.getDataHoraCadastro()) * -1).findFirst().orElse(null)).collect(Collectors.toSet()));
                    }

                    return ColmeiaDTO.toDTO(colmeia);
                }).collect(Collectors.toList())
            ;
        }

        return facade.colmeiaFindAll().stream().map(x -> ColmeiaDTO.toDTO(x)).collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ColmeiaDTO findById (@PathVariable Long id, @RequestParam(name = "collections", required = false) Boolean isCollections, @RequestParam(name = "date", required = false) String date) {

        if (isCollections) {

            if (date == null) {
                return ColmeiaDTO.toDTO(facade.colmeiaFindById(id));
            }

            else {
                Colmeia colmeia = facade.colmeiaFindById(id);
                LocalDate filter = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                colmeia.setMedicoes(colmeia.getMedicoes().stream().filter(medicao -> medicao.getDataHoraCadastro().toLocalDate().isEqual(filter)).collect(Collectors.toSet()));
                return ColmeiaDTO.toDTO(colmeia);
            }
        }

        else {
            Colmeia colmeia = facade.colmeiaFindById(id);
            colmeia.setMedicoes(null);
            return ColmeiaDTO.toDTO(colmeia);
        }
    }

    @GetMapping(value = "/search")
    @ResponseStatus(HttpStatus.OK)
    public ColmeiaDTO search (@RequestParam String codigo) {

        return ColmeiaDTO.toDTO(
            facade.colmeiaFindById(
                facade.colmeiaFindByCodigo(codigo).getId()
            )
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ColmeiaDTO save (@RequestBody Colmeia colmeia) {
        return ColmeiaDTO.toDTO(facade.colmeiaSave(colmeia));
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ColmeiaDTO update (@PathVariable Long id, @RequestBody Colmeia colmeiaUpdated) {

        Colmeia colmeia = facade.colmeiaFindById(id);
        colmeia.setCodigo(colmeiaUpdated.getCodigo());
        facade.colmeiaUpdate(colmeia);

        return ColmeiaDTO.toDTO(colmeia);
    }

    @PostMapping("/{codigo}/medicoes")
    @ResponseStatus(HttpStatus.OK)
    public String saveMedicao (@PathVariable String codigo, @RequestBody Medicao medicao) {

        Colmeia colmeia = facade.colmeiaFindByCodigo(codigo);
        colmeia.getMedicoes().add(medicao);

        facade.colmeiaUpdate(colmeia);
        return "Dados salvos com sucesso!";
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete (@PathVariable Long id) {
        facade.colmeiaDelete(facade.colmeiaFindById(id));
    }
}