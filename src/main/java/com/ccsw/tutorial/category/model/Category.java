package com.ccsw.tutorial.category.model;

import jakarta.persistence.*;

/**
 * @author ccsw
 *
 */
/* Le indica a Spring Boot que la clase implementa una entidad de BBDD. Sin esta
 * notación es imposible hacer queries */
@Entity
/* Indica el nombre y el schema de la tabla. Si el nombre de la tabla es igual al
 * de la clase, no es necesario poner esta notación. */
@Table(name = "category")
public class Category {

    /* Indica que es la PrimaryKey y que se genera con la estrategia anotada en
     * @GeneratedValue. IDENTITY indica que es autoincremental. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /* Indica el nombre de la columna que mapea la propiedad. También indica que
     * este campo no puede ir vacío (nulltable=false) */
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    /**
     * @return id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * @param id new value of {@link #getId}.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name new value of {@link #getName}.
     */
    public void setName(String name) {
        this.name = name;
    }

}
