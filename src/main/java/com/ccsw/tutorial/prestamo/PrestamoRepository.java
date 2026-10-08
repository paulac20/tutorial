package com.ccsw.tutorial.prestamo;

import com.ccsw.tutorial.prestamo.model.Prestamo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Paula Crespo
 */
public interface PrestamoRepository extends CrudRepository<Prestamo, Long> {
    Page<Prestamo> findAll(Pageable pageable);
}
