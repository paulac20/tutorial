package com.ccsw.tutorial.prestamo.model;

import com.ccsw.tutorial.client.model.Client;
import com.ccsw.tutorial.game.model.Game;
import jakarta.persistence.*;

import java.util.Date;

/**
 * @author Paula Crespo
 */
@Entity
@Table(name = "prestamo")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(name = "date_ini", nullable = false)
    private Date date_ini;

    @Column(name = "date_fin", nullable = false)
    private Date date_fin;

    /**
     * @return id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * @param id new value of {@link #getId()}
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return game
     */
    public Game getGame() {
        return this.game;
    }

    /**
     * @param game new value of {@link #getGame()}
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * @return client
     */
    public Client getClient() {
        return this.client;
    }

    /**
     * @param client new value of {@link #getClient()}
     */
    public void setClient(Client client) {
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
