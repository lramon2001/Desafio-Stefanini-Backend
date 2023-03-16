package com.stefanini.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.stefanini.login.Login;





@ApplicationScoped
public class LoginService {


    @Inject
    JogadorService jogadorService;
    
    public Boolean authenticate(Login login) throws Exception{
        return jogadorService.authenticate(login);
    }
}
