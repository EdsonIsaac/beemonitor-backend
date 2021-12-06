package io.github.edsonisaac.monitoramentocomeia.colmeia.controller;

import io.github.edsonisaac.monitoramentocomeia.colmeia.dto.ColmeiaDTO;
import io.github.edsonisaac.monitoramentocomeia.colmeia.model.Colmeia;
import io.github.edsonisaac.monitoramentocomeia.colmeia.model.Medicao;
import io.github.edsonisaac.monitoramentocomeia.infraestrutura.service.Facade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public ColmeiaDTO findById (@PathVariable Long id) {
        return ColmeiaDTO.toDTO(facade.colmeiaFindById(id));
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

    @GetMapping(value = "/update")
    @ResponseStatus(HttpStatus.OK)
    public String update (@RequestParam String codigo, @RequestParam Double temperatura, @RequestParam Double umidade, @RequestParam Double peso) {

        Colmeia colmeia = facade.colmeiaFindByCodigo(codigo);

        Medicao medicao = new Medicao();

        medicao.setTemperatura(temperatura);
        medicao.setUmidade(umidade);
        medicao.setPeso(peso);
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