package com.ccsw.tutorial.prestamo.model;

import com.ccsw.tutorial.client.model.ClientDto;
import com.ccsw.tutorial.game.model.GameDto;

import java.time.LocalDate;

/**
 * @author Paula Crespo
 */
public class PrestamoDto {
    private Long id;

    private GameDto game;

    private ClientDto client;

    private LocalDate dateIni;

    private LocalDate dateFin;

    /**
     * @return id
     */
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return game
     */
    public GameDto getGame() {
        return this.game;
    }

    /**
     * @param game new value of {@link #getGame()}
     */
    public void setGame(GameDto game) {
        this.game = game;
    }

    /**
     * @return client
     */
    public ClientDto getClient() {
        return this.client;
    }

    /**
     * @param client new value of {@link #getClient()}
     */
    public void setClient(ClientDto client) {
        this.client = client;
    }

    /**
     * @return dateIni
     */
    public LocalDate getDateIni() {

        return this.dateIni;
    }

    /**
     * @param dateIni new value of {@link #getDateIni()}
     */
    public void setDateIni(LocalDate dateIni) {
        this.dateIni = dateIni;
    }

    /**
     * @return dateFin
     */
    public LocalDate getDateFin() {
        return this.dateFin;
    }

    /**
     * @param dateFin new value of {@link #getDateFin()}
     */
    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }
}
