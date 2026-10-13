package com.ccsw.tutorial.prestamo.model;

import com.ccsw.tutorial.client.model.Client;
import com.ccsw.tutorial.game.model.Game;
import jakarta.persistence.*;

import java.time.LocalDate;

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
    private LocalDate dateIni;

    @Column(name = "date_fin", nullable = false)
    private LocalDate dateFin;

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
     * @return dateIni
     */
    public LocalDate getDateIni() {
        return this.dateIni;
    }

    /**
     * @param date_ini new value of {@link #getDateIni()}
     */
    public void setDateIni(LocalDate date_ini) {
        this.dateIni = date_ini;
    }

    /**
     * @return dateFin
     */
    public LocalDate getDateFin() {
        return this.dateFin;
    }

    /**
     * @param date_fin new value of {@link #getDateFin()}
     */
    public void setDateFin(LocalDate date_fin) {
        this.dateFin = date_fin;
    }
}
