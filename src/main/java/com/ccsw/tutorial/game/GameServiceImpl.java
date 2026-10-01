package com.ccsw.tutorial.game;

import com.ccsw.tutorial.game.model.Game;
import com.ccsw.tutorial.game.model.GameDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ccsw
 *
 */
@Service
@Transactional
public class GameServiceImpl implements GameService {

    @Autowired
    GameRepository gameRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Game> find(String title, Long idCategory) {

        // Será una consulta que devolverá un listado con filtros
        return (List<Game>) this.gameRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Long id, GameDto dto) {

        Game game;

        if (id == null) {
            game = new Game();
        } else {
            game = this.gameRepository.findById(id).orElse(null);
        }

        // Copia automáticamente las propiedades del dto al objeto game, excepto las propiedades que indicamos al final
        // Ignoramos los autores y las categorías porque un repositorio sólo puede ser llamado desde un servicio
        // Para recuperarlos tendremos que llamar a los métodos expuestos de AuthorService y CategoryService
        BeanUtils.copyProperties(dto, game, "id", "author", "category");

        this.gameRepository.save(game);
    }

}
