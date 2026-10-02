package com.ccsw.tutorial.game;

import com.ccsw.tutorial.common.criteria.SearchCriteria;
import com.ccsw.tutorial.game.model.Game;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

public class GameSpecification implements Specification<Game> {

    private static final long serialVersionUID = 1L;

    // Cuando se crea una Specification esta debe generar un predicado, para ello necesita
    // criterios de filtrado.
    private final SearchCriteria criteria;

    public GameSpecification(SearchCriteria criteria) {

        // Pasamos los criterios de filtrado que luego usaremos
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Game> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        // Se selecciona el tipo de operación, nosotros sólo usaremos las de comparación (:)
        if (criteria.getOperation().equalsIgnoreCase(":") && criteria.getValue() != null) {
            Path<String> path = getPath(root);
            // Se construye un "Predicate" por ser de tipo comparación
            if (path.getJavaType() == String.class) {
                // like para comparar texto
                return builder.like(path, "%" + criteria.getValue() + "%");
            } else {
                // equal para fechas o números (cuando no es texto)
                return builder.equal(path, criteria.getValue());
            }
        }
        return null;
    }

    // Para explorar las sub-entidades para realizar consultas sonbre los atributos de estas
    private Path<String> getPath(Root<Game> root) {
        String key = criteria.getKey();
        String[] split = key.split("[.]", 0);

        Path<String> expression = root.get(split[0]);
        for (int i = 1; i < split.length; i++) {
            expression = expression.get(split[i]);
        }

        return expression;
    }

}
