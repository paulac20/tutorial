package com.ccsw.tutorial.prestamo;

import com.ccsw.tutorial.prestamo.model.Prestamo;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Paula Crespo
 */
public interface PrestamoRepository extends CrudRepository<Prestamo, Long> {
}
