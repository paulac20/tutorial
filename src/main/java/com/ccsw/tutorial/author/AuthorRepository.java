package com.ccsw.tutorial.author;

import com.ccsw.tutorial.author.model.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 * @author ccsw
 *
 */
public interface AuthorRepository extends CrudRepository<Author, Long> {

    /**
     * Método para recuperar un listado paginado de {@link Author}
     * Este método nos devuelve una Page. Spring ya entiende que el método findAll() significa
     * que debe recuperar todos los datos de la tabla Author
     *
     * @param pageable pageable
     * @return {@link Page} de {@link Author}
     */
    Page<Author> findAll(Pageable pageable);

}
