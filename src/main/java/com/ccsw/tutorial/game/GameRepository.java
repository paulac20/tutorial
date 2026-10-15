package com.ccsw.tutorial.game;

import com.ccsw.tutorial.game.model.Game;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author ccsw
 *
 */
public interface GameRepository extends CrudRepository<Game, Long>, JpaSpecificationExecutor<Game> {

    @Override
    // Sirve para que cuando se consulte una entidad, se carguen también sus relaciones category
    // y author en la misma operación, evitando consultas adicionales posteriores (mejora el rendimiento)
    @EntityGraph(attributePaths = { "category", "author" })
    List<Game> findAll(Specification<Game> spec);

    boolean existsByAuthorId(Long authorId);

    boolean existsByCategoryId(Long categoryId);
}
