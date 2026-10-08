package com.ccsw.tutorial.prestamo.model;

import com.ccsw.tutorial.client.model.ClientDto;
import com.ccsw.tutorial.game.model.GameDto;

import java.util.Date;

/**
 * @author Paula Crespo
 */
public class PrestamoDto {
    private Long id;

    private GameDto game;

    private ClientDto client;

    private Date date_ini;

    private Date date_fin;

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
     * @return date_ini
     */
    public Date getDate_ini() {
        return this.date_ini;
    }

    /**
     * @param date_ini new value of {@link #getDate_ini()}
     */
    public void setDate_ini(Date date_ini) {
        this.date_ini = date_ini;
    }

    /**
     * @return date_fin
     */
    public Date getDate_fin() {
        return this.date_fin;
    }

    /**
     * @param date_fin new value of {@link #getDate_fin()}
     */
    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }
}
