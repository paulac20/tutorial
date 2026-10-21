package com.ccsw.tutorial.session;

import com.ccsw.tutorial.session.model.LoginDto;
import com.ccsw.tutorial.session.model.RegisterDto;
import com.ccsw.tutorial.session.model.Usser;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class SessionServiceImpl implements SessionService {

    @Autowired
    SessionRepository sessionRepository;

    @Override
    public void login(LoginDto dto) {
        Usser usser;
        usser = this.sessionRepository.findByUsser(dto.getUsser());
        validateUsserAndPassword(usser, dto);
    }

    @Override
    public void register(RegisterDto dto) {
        Usser usser = new Usser();
        validateUsser(dto);
        BeanUtils.copyProperties(dto, usser, "id");
        this.sessionRepository.save(usser);
    }

    @Override
    public void validateUsser(RegisterDto dto) {
        if (sessionRepository.existsByUsser(dto.getUsser())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "EXISTS_USSER");
        }
    }

    @Override
    public void validateUsserAndPassword(Usser usser, LoginDto dto) {
        if (usser == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "INCORRECT_LOGIN");
        }
        if (!usser.getPassword().equals(dto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "INCORRECT_LOGIN");
        }
    }
}
