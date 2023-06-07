package jaktia.huntingapp.service;

import jaktia.huntingapp.dto.AppUserDto;
import jaktia.huntingapp.security.AuthenticationRequest;

import java.util.Map;


public interface AppUserService {

    AppUserDto register(AppUserDto dto);
    Map<String, Object> findByUsername(String username);
    /** using AppUserDto findByUsername (String username); is not safe because this will return password to the client as well.
     It works but it is not logical. It is also possible to create a custom AppUserDto without password in it. **/

    void disableUserByUsername (String username);
}
