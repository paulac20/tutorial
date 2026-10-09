package com.ccsw.tutorial.prestamo.model;

import com.ccsw.tutorial.common.pagination.PageableRequest;

import java.util.Date;

/**
 * @author Paula Crespo
 */
public class PrestamoSearchDto {

    private PageableRequest pageable;
    private Long idGame;
    private Long idClient;
    private Date date;

    public PageableRequest getPageable() {
        return pageable;
    }

    private void setPageable(PageableRequest pageable) {
        this.pageable = pageable;
    }

    public Long getIdGame() {
        return this.idGame;
    }

    public void setIdGame(Long idGame) {
        this.idGame = idGame;
    }

    public Long getIdClient() {
        return this.idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
