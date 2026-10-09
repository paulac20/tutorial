package com.ccsw.tutorial.prestamo.model;

import com.ccsw.tutorial.common.pagination.PageableRequest;

import java.time.LocalDate;

/**
 * @author Paula Crespo
 */
public class PrestamoSearchDto {

    private PageableRequest pageable;
    private Long gameId;
    private Long clientId;
    private LocalDate date;

    public PageableRequest getPageable() {
        return pageable;
    }

    private void setPageable(PageableRequest pageable) {
        this.pageable = pageable;
    }

    public Long getGameId() {
        return this.gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Long getClientId() {
        return this.clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
