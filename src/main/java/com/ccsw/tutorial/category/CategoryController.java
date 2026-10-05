package com.ccsw.tutorial.category;

import com.ccsw.tutorial.category.model.Category;
import com.ccsw.tutorial.category.model.CategoryDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ccsw
 *
 */
/* Indica que los endpoints de este controlador pertenecen al grupo Category
 * (nombre y decripción del grupo)*/
@Tag(name = "Category", description = "API of Category")
/* Ruta desde el controlador. Encima de la clase establece una ruta común para todos
 * sus endpoints definidos dentro de la clase*/
@RequestMapping(value = "/category")
/* Indica que la clase es un controlador REST y que sus métodos responderán a
 * peticiones HTTP*/
@RestController
/* Indica que otras aplicaciones desde cualquier origen pueden realizar peticiones
 * a este controlador*/
@CrossOrigin(origins = "*")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    // Permite convertir una lista o un objeto único a un tipo concreto
    @Autowired
    ModelMapper mapper;

    /**
     * Método para recuperar todas las categorias
     *
     * @return {@link List} de {@link CategoryDto}
     */
    /* Documenta una operación contreta de la API REST, indicando un título (summary)
     * y una descripción de la operación */
    @Operation(summary = "Find", description = "Method that return a list of Categories")
    /* Indica que cuando llegue una petición HTTP GET, se ejecute este método (prueba()) */
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<CategoryDto> findAll() {
        List<Category> categories = this.categoryService.findAll();
        // Convertimos un objeto Category a un objeto CategoryDto
        return categories.stream().map(e -> mapper.map(e, CategoryDto.class)).collect(Collectors.toList());
    }

    /**
     * Método para crear o actualizar una categoria.
     * El id llega a través de la URL (@PathVariable), es opcional (required=false)
     * y se guarda en una variable id. Los datos que llegan en la petición se recogen en
     * una variable tipo CategoryDto (lo indica @RequestBody).
     *
     * @param id PK de la entidad
     * @param dto datos de la entidad
     */
    @Operation(summary = "Save or Update", description = "Method that saves or updates a Category")
    @RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody CategoryDto dto) {
        this.categoryService.save(id, dto);
    }

    /**
     * Método para borrar una categoria
     *
     * @param id PK de la entidad
     * @throws Exception cuando no existe la categoría
     */
    @Operation(summary = "Delete", description = "Method that deletes a Category")
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) throws Exception {
        //Se elimina la categoría con el id del mapa que tenemos de categorías
        this.categoryService.delete(id);
    }

}
