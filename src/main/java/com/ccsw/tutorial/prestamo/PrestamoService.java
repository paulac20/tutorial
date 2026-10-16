package com.ccsw.tutorial.prestamo;

import com.ccsw.tutorial.prestamo.model.Prestamo;
import com.ccsw.tutorial.prestamo.model.PrestamoDto;
import com.ccsw.tutorial.prestamo.model.PrestamoSearchDto;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author Paula Crespo
 */
public interface PrestamoService {

    Prestamo get(Long id);

    Page<Prestamo> findPage(PrestamoSearchDto dto);

    void save(Long id, PrestamoDto dto);

    void delete(Long id) throws Exception;

    List<Prestamo> findAll();

    void validateDates(PrestamoDto dto);

    void validateModifyPrestamo(PrestamoDto dto);

    void validateNewPrestamo(PrestamoDto dto);
}
