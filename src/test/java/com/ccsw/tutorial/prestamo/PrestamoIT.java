package com.ccsw.tutorial.prestamo;

import com.ccsw.tutorial.client.model.ClientDto;
import com.ccsw.tutorial.common.pagination.PageableRequest;
import com.ccsw.tutorial.config.ResponsePage;
import com.ccsw.tutorial.game.model.GameDto;
import com.ccsw.tutorial.prestamo.model.PrestamoDto;
import com.ccsw.tutorial.prestamo.model.PrestamoSearchDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PrestamoIT {

    public static final String LOCALHOST = "http://localhost:";
    public static final String SERVICE_PATH = "/prestamo";

    public static final Long EXISTS_PRESTAMO_ID = 1L;
    public static final Long NOT_EXISTS_PRESTAMO_ID = 0L;
    private static final Long NOT_EXISTS_CLIENT = 9L;
    private static final Long EXISTS_CLIENT = 2L;
    private static final Long NOT_EXISTS_GAME = 9L;
    private static final Long EXISTS_GAME = 2L;
    private static final Long MODIFY_PRESTAMO_ID = 5L;
    private static final Long NEW_PRESTAMO_CLIENT_NOT_PRESTAMO = 1L;
    private static final Long NEW_PRESTAMO_GAME_NOT_PRESTAMO = 1L;
    private static final LocalDate DATE_INI = LocalDate.of(2026, 10, 8);
    private static final LocalDate DATE_FIN_BEFORE_INI = LocalDate.of(2026, 10, 1);
    private static final LocalDate DATE_FIN_AFTER_TWENTY_DAYS = LocalDate.of(2026, 10, 28);
    private static final LocalDate DATE_FIN = LocalDate.of(2026, 10, 14);
    private static final LocalDate SEARCH_DATE = LocalDate.of(2026, 1, 20);

    private static final String GAME_ID_PARAM = "idGame";
    private static final String CLIENT_ID_PARAM = "idClient";
    private static final String DATE_PARAM = "date";
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

    private String getUrlWithParams() {
        return UriComponentsBuilder.fromHttpUrl(LOCALHOST + port + SERVICE_PATH).queryParam(GAME_ID_PARAM, "{" + GAME_ID_PARAM + "}").queryParam(CLIENT_ID_PARAM, "{" + CLIENT_ID_PARAM + "}").queryParam(DATE_PARAM, "{" + DATE_PARAM + "}")
                .encode().toUriString();
    }

    @Test
    public void findAllShouldReturnAllPrestamo() {
        ResponseEntity<List<PrestamoDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.GET, null, responseTypeList);

        assertNotNull(response);
        assertEquals(TOTAL_PRESTAMOS, response.getBody().size());
    }

    @Test
    public void findFirstPageWithFiveSizeShouldReturnFirstFiveResults() {
        PrestamoSearchDto searchDto = new PrestamoSearchDto();
        searchDto.setPageable(new PageableRequest(0, PAGE_SIZE));

        ResponseEntity<ResponsePage<PrestamoDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);
        assertNotNull(response);
        assertEquals(TOTAL_PRESTAMOS, response.getBody().getTotalElements());
        assertEquals(PAGE_SIZE, response.getBody().getContent().size());
    }

    @Test
    public void findWithoutFiltersShouldReturnAllPrestamosInDB() {
        int PAGE_SIZE_TEN = 10;
        int PRESTAMOS_WITH_FILTER = 6;
        PrestamoSearchDto searchDto = new PrestamoSearchDto();
        searchDto.setPageable(new PageableRequest(0, PAGE_SIZE_TEN));

        ResponseEntity<ResponsePage<PrestamoDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().getContent().size());
        assertEquals(PAGE_SIZE_TEN, response.getBody().getPageable().getPageSize());
    }

    @Test
    public void findExistsGameShouldReturnPrestamos() {
        int PRESTAMOS_WITH_FILTER = 1;
        PrestamoSearchDto searchDto = new PrestamoSearchDto();
        searchDto.setPageable(new PageableRequest(0, PAGE_SIZE));
        searchDto.setGameId(EXISTS_GAME);

        ResponseEntity<ResponsePage<PrestamoDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().getContent().size());
    }

    @Test
    public void findExistsClientShouldReturnPrestamos() {
        int PRESTAMOS_WITH_FILTER = 1;
        PrestamoSearchDto searchDto = new PrestamoSearchDto();
        searchDto.setPageable(new PageableRequest(0, PAGE_SIZE));
        searchDto.setClientId(EXISTS_CLIENT);

        ResponseEntity<ResponsePage<PrestamoDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().getContent().size());
    }

    @Test
    public void findExistsDateShouldReturnPrestamos() {
        int PRESTAMOS_WITH_FILTER = 2;
        PrestamoSearchDto searchDto = new PrestamoSearchDto();
        searchDto.setPageable(new PageableRequest(0, PAGE_SIZE));
        searchDto.setDate(SEARCH_DATE);

        ResponseEntity<ResponsePage<PrestamoDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().getContent().size());
    }

    @Test
    public void findExistsGameAndClientShouldReturnPrestamos() {
        int PRESTAMOS_WITH_FILTER = 1;
        PrestamoSearchDto searchDto = new PrestamoSearchDto();
        searchDto.setPageable(new PageableRequest(0, PAGE_SIZE));

        searchDto.setGameId(EXISTS_GAME);
        searchDto.setClientId(EXISTS_CLIENT);

        ResponseEntity<ResponsePage<PrestamoDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().getContent().size());
    }

    @Test
    public void findExistsGameAndDateShouldReturnPrestamos() {
        int PRESTAMOS_WITH_FILTER = 1;
        PrestamoSearchDto searchDto = new PrestamoSearchDto();
        searchDto.setPageable(new PageableRequest(0, PAGE_SIZE));

        searchDto.setGameId(EXISTS_GAME);
        searchDto.setDate(SEARCH_DATE);

        ResponseEntity<ResponsePage<PrestamoDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().getContent().size());
    }

    @Test
    public void findExistsClientAndDateShouldReturnPrestamos() {
        int PRESTAMOS_WITH_FILTER = 1;
        PrestamoSearchDto searchDto = new PrestamoSearchDto();
        searchDto.setPageable(new PageableRequest(0, PAGE_SIZE));

        searchDto.setClientId(EXISTS_CLIENT);
        searchDto.setDate(SEARCH_DATE);

        ResponseEntity<ResponsePage<PrestamoDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().getContent().size());
    }

    @Test
    public void findExistsGameAndClientAndDateShouldReturnPrestamos() {
        int PRESTAMOS_WITH_FILTER = 1;
        PrestamoSearchDto searchDto = new PrestamoSearchDto();
        searchDto.setPageable(new PageableRequest(0, PAGE_SIZE));

        searchDto.setGameId(EXISTS_GAME);
        searchDto.setClientId(EXISTS_CLIENT);
        searchDto.setDate(SEARCH_DATE);

        ResponseEntity<ResponsePage<PrestamoDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().getContent().size());
    }

    @Test
    public void findNotExistsGameShouldReturnEmpty() {
        int PRESTAMOS_WITH_FILTER = 0;
        PrestamoSearchDto searchDto = new PrestamoSearchDto();
        searchDto.setPageable(new PageableRequest(0, PAGE_SIZE));
        searchDto.setGameId(NOT_EXISTS_GAME);

        ResponseEntity<ResponsePage<PrestamoDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().getContent().size());
    }

    @Test
    public void findNotExistsClientShouldReturnEmpty() {
        int PRESTAMOS_WITH_FILTER = 0;
        PrestamoSearchDto searchDto = new PrestamoSearchDto();
        searchDto.setPageable(new PageableRequest(0, PAGE_SIZE));
        searchDto.setClientId(NOT_EXISTS_CLIENT);

        ResponseEntity<ResponsePage<PrestamoDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().getContent().size());
    }

    @Test
    public void findNotExistsDateShouldReturnEmpty() {
        int PRESTAMOS_WITH_FILTER = 0;
        PrestamoSearchDto searchDto = new PrestamoSearchDto();
        searchDto.setPageable(new PageableRequest(0, PAGE_SIZE));
        searchDto.setDate(DATE_FIN_AFTER_TWENTY_DAYS);

        ResponseEntity<ResponsePage<PrestamoDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().getContent().size());
    }

    @Test
    public void findNotExistsGameOrClientShouldReturnEmpty() {
        int PRESTAMOS_WITH_FILTER = 0;
        PrestamoSearchDto searchDto = new PrestamoSearchDto();
        searchDto.setPageable(new PageableRequest(0, PAGE_SIZE));

        searchDto.setGameId(NOT_EXISTS_GAME);
        searchDto.setClientId(NOT_EXISTS_CLIENT);
        ResponseEntity<ResponsePage<PrestamoDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);
        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().getContent().size());

        searchDto.setGameId(NOT_EXISTS_GAME);
        searchDto.setClientId(EXISTS_CLIENT);
        response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);
        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().getContent().size());

        searchDto.setGameId(EXISTS_GAME);
        searchDto.setClientId(NOT_EXISTS_CLIENT);
        response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);
        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().getContent().size());
    }

    @Test
    public void findNotExistsGameOrDateShouldReturnEmpty() {
        int PRESTAMOS_WITH_FILTER = 0;
        PrestamoSearchDto searchDto = new PrestamoSearchDto();
        searchDto.setPageable(new PageableRequest(0, PAGE_SIZE));

        searchDto.setGameId(NOT_EXISTS_GAME);
        searchDto.setDate(DATE_FIN_AFTER_TWENTY_DAYS);
        ResponseEntity<ResponsePage<PrestamoDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);
        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().getContent().size());

        searchDto.setGameId(EXISTS_GAME);
        searchDto.setDate(DATE_FIN_AFTER_TWENTY_DAYS);
        response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);
        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().getContent().size());

        searchDto.setGameId(NOT_EXISTS_GAME);
        searchDto.setDate(SEARCH_DATE);
        response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);
        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().getContent().size());
    }

    @Test
    public void findNotExistsClientOrDateShouldReturnEmpty() {
        int PRESTAMOS_WITH_FILTER = 0;
        PrestamoSearchDto searchDto = new PrestamoSearchDto();
        searchDto.setPageable(new PageableRequest(0, PAGE_SIZE));

        searchDto.setClientId(NOT_EXISTS_CLIENT);
        searchDto.setDate(DATE_FIN_AFTER_TWENTY_DAYS);
        ResponseEntity<ResponsePage<PrestamoDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);
        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().getContent().size());

        searchDto.setClientId(EXISTS_CLIENT);
        searchDto.setDate(DATE_FIN_AFTER_TWENTY_DAYS);
        response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);
        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().getContent().size());

        searchDto.setClientId(NOT_EXISTS_CLIENT);
        searchDto.setDate(SEARCH_DATE);
        response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);
        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().getContent().size());
    }

    @Test
    public void findNotExistsGameOrClientOrDateShouldReturnEmpty() {
        int PRESTAMOS_WITH_FILTER = 0;
        PrestamoSearchDto searchDto = new PrestamoSearchDto();
        searchDto.setPageable(new PageableRequest(0, PAGE_SIZE));

        searchDto.setGameId(NOT_EXISTS_GAME);
        searchDto.setClientId(NOT_EXISTS_CLIENT);
        searchDto.setDate(DATE_FIN_AFTER_TWENTY_DAYS);
        ResponseEntity<ResponsePage<PrestamoDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);
        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().getContent().size());

        searchDto.setGameId(EXISTS_GAME);
        searchDto.setClientId(NOT_EXISTS_CLIENT);
        searchDto.setDate(DATE_FIN_AFTER_TWENTY_DAYS);
        response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);
        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().getContent().size());

        searchDto.setGameId(NOT_EXISTS_GAME);
        searchDto.setClientId(EXISTS_CLIENT);
        searchDto.setDate(DATE_FIN_AFTER_TWENTY_DAYS);
        response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);
        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().getContent().size());

        searchDto.setGameId(NOT_EXISTS_GAME);
        searchDto.setClientId(NOT_EXISTS_CLIENT);
        searchDto.setDate(SEARCH_DATE);
        response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);
        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().getContent().size());

        searchDto.setGameId(EXISTS_GAME);
        searchDto.setClientId(EXISTS_CLIENT);
        searchDto.setDate(DATE_FIN_AFTER_TWENTY_DAYS);
        response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);
        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().getContent().size());

        searchDto.setGameId(EXISTS_GAME);
        searchDto.setClientId(NOT_EXISTS_CLIENT);
        searchDto.setDate(SEARCH_DATE);
        response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);
        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().getContent().size());

        searchDto.setGameId(NOT_EXISTS_GAME);
        searchDto.setClientId(EXISTS_CLIENT);
        searchDto.setDate(SEARCH_DATE);
        response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);
        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().getContent().size());
    }

    @Test
    public void saveWithoutIdShouldCreateNewPrestamo() {
        int newPrestamoSize = 7;
        PrestamoDto dto = new PrestamoDto();
        GameDto gamedto = new GameDto();
        ClientDto clientdto = new ClientDto();
        gamedto.setId(1L);
        clientdto.setId(1L);

        dto.setClient(clientdto);
        dto.setGame(gamedto);
        dto.setDateIni(DATE_INI);
        dto.setDateFin(DATE_FIN);

        PrestamoSearchDto searchDto = new PrestamoSearchDto();
        searchDto.setPageable(new PageableRequest(0, newPrestamoSize));
        searchDto.setClientId(1L);
        searchDto.setGameId(1L);
        searchDto.setDate(DATE_INI);

        ResponseEntity<ResponsePage<PrestamoDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(0, response.getBody().getTotalElements());

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, new HttpEntity<>(dto), Void.class);

        response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(1, response.getBody().getTotalElements());
    }

    @Test
    public void saveWithClientWithPrestamoInTheSameDateShouldConflict() {

    }

    @Test
    public void saveWithGamePrestadoInTheSameDateShouldConflict() {

    }

    @Test
    public void saveWithEndDateBeforeStartDateShouldReturnNotAcceptable() {

    }

    @Test
    public void saveWithMoreThanFourteenDaysOfPrestamoShouldReturnNotAcceptable() {

    }

    @Test
    public void modifyWithExistsShouldModifyPrestamo() {
        int newPrestamoSize = 7;
        PrestamoDto dto = new PrestamoDto();
        GameDto gamedto = new GameDto();
        ClientDto clientdto = new ClientDto();
        gamedto.setId(1L);
        clientdto.setId(1L);

        dto.setClient(clientdto);
        dto.setGame(gamedto);
        dto.setDateIni(DATE_INI);
        dto.setDateFin(DATE_FIN);

        PrestamoSearchDto searchDto = new PrestamoSearchDto();
        searchDto.setPageable(new PageableRequest(0, newPrestamoSize));
        searchDto.setClientId(1L);
        searchDto.setGameId(1L);
        searchDto.setDate(DATE_INI);

        ResponseEntity<ResponsePage<PrestamoDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(0, response.getBody().getTotalElements());

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "/" + MODIFY_PRESTAMO_ID, HttpMethod.PUT, new HttpEntity<>(dto), Void.class);

        response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(1, response.getBody().getContent().size());
        assertEquals(MODIFY_PRESTAMO_ID, response.getBody().getContent().get(0).getId());
    }

    @Test
    public void modifyWithNotExistIdShouldThrowException() {

    }

    @Test
    public void deleteWithExistsIdShouldDeletePrestamo() {

    }

    @Test
    public void deleteWithNotExistsIdShouldInternalError() {

    }
}
