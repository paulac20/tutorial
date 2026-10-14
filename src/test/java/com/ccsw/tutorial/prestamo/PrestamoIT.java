package com.ccsw.tutorial.prestamo;

import com.ccsw.tutorial.config.ResponsePage;
import com.ccsw.tutorial.prestamo.model.PrestamoDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PrestamoIT {

    public static final String LOCALHOST = "http://localhost:";
    public static final String SERVICE_PATH = "/game";

    public static final Long EXISTS_PRESTAMO_ID = 1L;
    public static final Long NOT_EXISTS_PRESTAMO_ID = 0L;
    private static final Long NOT_EXISTS_CLIENT = 9L;
    private static final Long EXISTS_CLIENT = 2L;
    private static final Long NOT_EXISTS_GAME = 9L;
    private static final Long EXISTS_GAME = 2L;
    private static final Long NEW_PRESTAMO_ID = 7L;
    private static final Long NEW_PRESTAMO_CLIENT_CANT_PRESTAMO = 1L;
    private static final Long NEW_PRESTAMO_GAME_CANT_PRESTAMO = 1L;
    private static final LocalDate DATE_INI = LocalDate.of(2026, 10, 8);
    private static final LocalDate DATE_FIN_BEFORE_INI = LocalDate.of(2026, 10, 1);
    private static final LocalDate DATE_FIN_AFTER_TWENTY_DAYS = LocalDate.of(2026, 10, 28);
    private static final LocalDate DATE_FIN = LocalDate.of(2026, 10, 14);

    private static final int TOTAL_PRESTAMOS = 6;
    private static final int PAGE_SIZE = 5;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    ParameterizedTypeReference<ResponsePage<PrestamoDto>> responseTypePage = new ParameterizedTypeReference<ResponsePage<PrestamoDto>>() {
    };

    ParameterizedTypeReference<List<PrestamoDto>> responseTypeList = new ParameterizedTypeReference<List<PrestamoDto>>() {
    };

    @Test
    public void findAllShouldReturnAllPrestamo() {
        ResponseEntity<List<PrestamoDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, null, responseTypeList);

        assertNotNull(response);
        assertEquals(TOTAL_PRESTAMOS, response.getBody().size());
    }
}
