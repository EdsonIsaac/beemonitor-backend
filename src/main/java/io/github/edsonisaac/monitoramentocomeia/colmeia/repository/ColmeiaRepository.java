package io.github.edsonisaac.monitoramentocomeia.colmeia.repository;

import io.github.edsonisaac.monitoramentocomeia.colmeia.model.Colmeia;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ColmeiaRepository extends JpaRepository<Colmeia, Long> {

    @Override
    @EntityGraph(attributePaths = {"medicoes"})
    public Optional<Colmeia> findById(Long aLong);

    public Optional<Colmeia> findByCodigo (String codigo);
}