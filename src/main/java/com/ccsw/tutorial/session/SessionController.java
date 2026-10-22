package com.ccsw.tutorial.session;

import com.ccsw.tutorial.session.model.LoginDto;
import com.ccsw.tutorial.session.model.RegisterDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * @author Paula Crespo
 */
@Tag(name = "Usser", description = "API of Ussers")
@RequestMapping(value = "/session")
@RestController
@CrossOrigin(origins = "*")
public class SessionController {
    @Autowired
    private SessionService sessionService;

    @Operation(summary = "Login", description = "Method that login a usser")
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public void login(@RequestBody LoginDto dto) {
        this.sessionService.login(dto);
    }

    @Operation(summary = "Register", description = "Method that registers a usser")
    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public void register(@RequestBody RegisterDto dto) {
        this.sessionService.register(dto);
    }

    @Operation(summary = "isAuthenticated", description = "Method thar returns if a usser is login or no")
    @RequestMapping(path = "/isAuthenticated", method = RequestMethod.GET)
    public boolean isAuthenticated(Authentication authentication) {
        if (authentication != null) {
            return authentication.isAuthenticated();
        }
        return false;
    }
}
