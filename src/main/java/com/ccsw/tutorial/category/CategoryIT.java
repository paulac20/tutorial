package com.ccsw.tutorial.category;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

/* Sirve para inicializar el contexto de Spring cada vez que se inician los tests de jUnit */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
/* Indica que cuando termine la ejecución de cada test, se hará un rearranque parcial
 * del contexto y dejará la BBDD como estaba inicialmente */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CategoryIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

}
