package io.github.edsonisaac.monitoramentocomeia.colmeia.service;

import io.github.edsonisaac.monitoramentocomeia.colmeia.model.Colmeia;
import io.github.edsonisaac.monitoramentocomeia.colmeia.repository.ColmeiaRepository;
import io.github.edsonisaac.monitoramentocomeia.infraestrutura.exception.ObjectNotFoundException;
import io.github.edsonisaac.monitoramentocomeia.infraestrutura.exception.OperationFailedException;
import io.github.edsonisaac.monitoramentocomeia.infraestrutura.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColmeiaService {

    private final ColmeiaRepository colmeiaRepository;

    @Autowired
    public ColmeiaService(ColmeiaRepository colmeiaRepository) {
        this.colmeiaRepository = colmeiaRepository;
    }

    public Colmeia findById (Long id) {
        return colmeiaRepository.findById(id).orElseThrow(() -> {
           throw new ObjectNotFoundException("Colmeia não encontrada!");
        });
    }

    public Colmeia findByCodigo (String codigo) {
        return colmeiaRepository.findByCodigo(codigo).orElseThrow(() -> {
           throw new ObjectNotFoundException("Colmeia não encontrada!");
        });
    }

    public List<Colmeia> findAll () {
        return colmeiaRepository.findAll();
    }

    public Colmeia save (Colmeia colmeia) {

        if (colmeia == null) {
            throw new ValidationException("Colmeia nula!");
        }

        if (validateColmeia(colmeia)) {
            colmeiaRepository.save(colmeia);
        }

        return colmeia;
    }

    public Colmeia update (Colmeia colmeia) {

        if (colmeia == null) {
            throw new ValidationException("Colmeia nula!");
        }

        if (!colmeiaRepository.existsById(colmeia.getId())) {
            throw new ObjectNotFoundException("Colmeia não encontrada!");
        }

        if (validateColmeia(colmeia)) {
            colmeiaRepository.save(colmeia);
        }

        return colmeia;
    }

    public void delete (Colmeia colmeia) {

        if (colmeia == null) {
            throw new ValidationException("Colmeia nula!");
        }

        if (!colmeiaRepository.existsById(colmeia.getId())) {
            throw new ObjectNotFoundException("Colmeia não encontrada!");
        }

        try {
            colmeiaRepository.delete(colmeia);
        } catch (Exception ex) {
            throw new OperationFailedException("Operação não concluida!" + ex.getMessage());
        }
    }

    private boolean validateColmeia(Colmeia colmeia) {

        try {
            Colmeia colmeia_findByCodigo = findByCodigo(colmeia.getCodigo());

            if (colmeia_findByCodigo != null && !colmeia.equals(colmeia_findByCodigo)) {
                throw new ValidationException("Colmeia já cadastrada!");
            }
        } catch (ObjectNotFoundException ex) { }

        return true;
    }
}