package com.ccsw.tutorial.category;

import com.ccsw.tutorial.category.model.CategoryDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/* Sirve para inicializar el contexto de Spring cada vez que se inician los tests de jUnit */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
/* Indica que cuando termine la ejecución de cada test, se hará un rearranque parcial
 * del contexto y dejará la BBDD como estaba inicialmente */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CategoryIT {

    public static final String LOCALHOST = "http://localhost:";
    public static final String SERVICE_PATH = "/category";
    public static final Long NEW_CATEGORY_ID = 4L;
    public static final String NEW_CATEGORY_NAME = "CAT4";
    public static final Long MODIFY_CATEGORY_ID = 3L;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    ParameterizedTypeReference<List<CategoryDto>> responseType = new ParameterizedTypeReference<List<CategoryDto>>() {
    };

    @Test
    public void findAllShouldReturnAllCategories() {

        // Comprobamos el método GET en el endpoint categories para comprobar si devuelve los 3 resultados que hay actualmente en la base de datos
        ResponseEntity<List<CategoryDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.GET, null, responseType);

        assertNotNull(response);
        assertEquals(3, response.getBody().size());
    }

    @Test
    public void saveWithoutIdShouldCreateNewCategory() {

        // Construimos un objeto CategoryDto para darle nombre a la categoría
        CategoryDto dto = new CategoryDto();
        dto.setName(NEW_CATEGORY_NAME);

        // Invocamos al método PUT sin añadir el ID en la ruta para comprobar que se añaden
        // nuevas categorías correctamente
        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, new HttpEntity<>(dto), Void.class);

        // Recuperamos la lista de categorías y comprobamos que devuelve 4 resultados
        ResponseEntity<List<CategoryDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.GET, null, responseType);
        assertNotNull(response);
        assertEquals(4, response.getBody().size());

        // Hacemos una búsqueda con el ID 4 (el nuevo) para comprobar que recupera los
        // datos bien
        CategoryDto categorySearch = response.getBody().stream().filter(item -> item.getId().equals(NEW_CATEGORY_ID)).findFirst().orElse(null);
        assertNotNull(categorySearch);
        assertEquals(NEW_CATEGORY_NAME, categorySearch.getName());
    }

    @Test
    public void modifyWithExistIdShouldModifyCategory() {

        CategoryDto dto = new CategoryDto();
        dto.setName(NEW_CATEGORY_NAME);

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "/" + MODIFY_CATEGORY_ID, HttpMethod.PUT, new HttpEntity<>(dto), Void.class);

        ResponseEntity<List<CategoryDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.GET, null, responseType);
        assertNotNull(response);
        assertEquals(3, response.getBody().size());

        CategoryDto categorySearch = response.getBody().stream().filter(item -> item.getId().equals(MODIFY_CATEGORY_ID)).findFirst().orElse(null);
        assertNotNull(categorySearch);
        assertEquals(NEW_CATEGORY_NAME, categorySearch.getName());
    }

    @Test
    public void modifyWithNotExistIdShouldInternalError() {

        CategoryDto dto = new CategoryDto();
        dto.setName(NEW_CATEGORY_NAME);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "/" + NEW_CATEGORY_ID, HttpMethod.PUT, new HttpEntity<>(dto), Void.class);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    public static final Long DELETE_CATEGORY_ID = 2L;

    @Test
    public void deleteWithExistsIdShouldDeleteCategory() {

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "/" + DELETE_CATEGORY_ID, HttpMethod.DELETE, null, Void.class);

        ResponseEntity<List<CategoryDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.GET, null, responseType);
        assertNotNull(response);
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void deleteWithNotExistsIdShouldInternalError() {

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "/" + NEW_CATEGORY_ID, HttpMethod.DELETE, null, Void.class);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

}
