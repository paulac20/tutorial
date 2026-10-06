package com.ccsw.tutorial.client;

import com.ccsw.tutorial.client.model.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Paula Crespo
 */
public interface ClientRepository extends CrudRepository<Client, Long> {

    boolean existsByName(String name);

    List<Client> findAllByOrderByIdAsc();
}
