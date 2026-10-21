package com.ccsw.tutorial.session;

import com.ccsw.tutorial.session.model.Usser;
import org.springframework.data.repository.CrudRepository;

public interface SessionRepository extends CrudRepository<Usser, Long> {

    boolean existsByUsser(String usser);

    Usser findByUsser(String usser);
}