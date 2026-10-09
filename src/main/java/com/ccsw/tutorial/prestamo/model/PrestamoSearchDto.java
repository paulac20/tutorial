package com.ccsw.tutorial.prestamo.model;

import com.ccsw.tutorial.common.pagination.PageableRequest;

import java.util.Date;

/**
 * @author Paula Crespo
 */
public class PrestamoSearchDto {

    private PageableRequest pageable;
    private Long gameId;
    private Long clientId;
    private Date date;

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

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
