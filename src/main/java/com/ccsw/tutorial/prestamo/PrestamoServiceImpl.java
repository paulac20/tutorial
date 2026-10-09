package com.ccsw.tutorial.prestamo;

import com.ccsw.tutorial.client.ClientService;
import com.ccsw.tutorial.common.criteria.SearchCriteria;
import com.ccsw.tutorial.game.GameService;
import com.ccsw.tutorial.prestamo.model.Prestamo;
import com.ccsw.tutorial.prestamo.model.PrestamoDto;
import com.ccsw.tutorial.prestamo.model.PrestamoSearchDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
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
        PrestamoSpecification gameSpec = new PrestamoSpecification(new SearchCriteria("game.id", ":", dto.getGameId()));
        PrestamoSpecification clientSpec = new PrestamoSpecification(new SearchCriteria("client.id", ":", dto.getClientId()));
        PrestamoSpecification iniDateSpec = new PrestamoSpecification(new SearchCriteria("date_ini", "<=", dto.getDate()));
        PrestamoSpecification finDateSpec = new PrestamoSpecification(new SearchCriteria("date_fin", ">=", dto.getDate()));

        Specification<Prestamo> spec = Specification.where(gameSpec).and(clientSpec).and(iniDateSpec).and(finDateSpec);

        return this.prestamoRepository.findAll(spec, dto.getPageable().getPageable());
    }

    @Override
    public void save(Long id, PrestamoDto dto) {
        Prestamo prestamo;

        if (id == null) {
            prestamo = new Prestamo();
        } else {
            prestamo = this.get(id);
        }

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
