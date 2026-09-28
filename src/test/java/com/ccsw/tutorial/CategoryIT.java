package com.ccsw.tutorial;

import com.ccsw.tutorial.category.model.CategoryDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
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

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    ParameterizedTypeReference<List<CategoryDto>> responseType = new ParameterizedTypeReference<List<CategoryDto>>() {
    };

    @Test
    public void findAllShouldReturnAllCategories() {

        ResponseEntity<List<CategoryDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.GET, null, responseType);

        assertNotNull(response);
        assertEquals(3, response.getBody().size());
    }
}
