package com.ccsw.tutorial.prestamo;

import com.ccsw.tutorial.prestamo.model.Prestamo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

/**
 * @author Paula Crespo
 */
public interface PrestamoRepository extends CrudRepository<Prestamo, Long>, JpaSpecificationExecutor<Prestamo> {
    @Override
    @EntityGraph(attributePaths = { "game", "client" })
    Page<Prestamo> findAll(Specification<Prestamo> spec, Pageable pageable);

    @Query("SELECT COUNT(p)>0 FROM Prestamo p WHERE p.client.id = :clientId AND (p.dateIni <= :dateFin AND p.dateFin >= :dateIni)")
    boolean existsOverlappingDatesByClient(@Param("clientId") Long clientId, @Param("dateIni") LocalDate dateIni, @Param("dateFin") LocalDate dateFin);

    @Query("SELECT COUNT(p)>0 FROM Prestamo p WHERE p.game.id = :gameId AND (p.dateIni <= :dateFin AND p.dateFin >= :dateIni)")
    boolean existsOverlappingDatesByGame(@Param("gameId") Long gameId, @Param("dateIni") LocalDate dateIni, @Param("dateFin") LocalDate dateFin);

    boolean existsByClientId(Long clientId);
}
