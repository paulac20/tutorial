package com.ccsw.tutorial.category;

import com.ccsw.tutorial.category.model.Category;
import org.springframework.data.repository.CrudRepository;

/**
 * @author ccsw
 * Spring Boot ya tiene la implementación de CrudRepository, nosotros sólo hemos crado
 * la interfaz.
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {

}
