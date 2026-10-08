package com.ccsw.tutorial.prestamo;

import com.ccsw.tutorial.client.ClientService;
import com.ccsw.tutorial.game.GameService;
import com.ccsw.tutorial.prestamo.model.Prestamo;
import com.ccsw.tutorial.prestamo.model.PrestamoDto;
import com.ccsw.tutorial.prestamo.model.PrestamoSearchDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Paula Crespo
 */
@Service
@Transactional
public class PrestamoServiceImpl implements PrestamoService {

    @Autowired
    PrestamoRepository prestamoRepository;

    @Autowired
    private GameService gameService;

    @Autowired
    private ClientService clientService;

    @Override
    public Prestamo get(Long id) {
        return this.prestamoRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Prestamo> findPage(PrestamoSearchDto dto) {
        return this.prestamoRepository.findAll(dto.getPageable().getPageable());
    }

    @Override
    public void save(Long id, PrestamoDto dto) {
        Prestamo prestamo;

        prestamo = new Prestamo();

        BeanUtils.copyProperties(dto, prestamo, "id", "game", "client");

        prestamo.setGame(gameService.get(dto.getGame().getId()));
        prestamo.setClient(clientService.get(dto.getClient().getId()));

        this.prestamoRepository.save(prestamo);
    }

    @Override
    public void delete(Long id) throws Exception {
        if (this.get(id) == null) {
            throw new Exception("Not exists");
        }

        this.prestamoRepository.deleteById(id);
    }

    @Override
    public List<Prestamo> findAll() {
        return (List<Prestamo>) this.prestamoRepository.findAll();
    }
}
