package com.ccsw.tutorial.session;

import com.ccsw.tutorial.session.model.LoginDto;
import com.ccsw.tutorial.session.model.RegisterDto;
import com.ccsw.tutorial.session.model.Usser;

public interface SessionService {

    void login(LoginDto dto);

    void register(RegisterDto dto);

    void validateUsser(RegisterDto dto);

    void validateUsserAndPassword(Usser usser, LoginDto dto);
}
