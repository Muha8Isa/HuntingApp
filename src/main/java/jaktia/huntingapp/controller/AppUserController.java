package jaktia.huntingapp.controller;


import jaktia.huntingapp.dto.AppUserDto;
import jaktia.huntingapp.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
// @Validated // To make annotations work inside method type work. It is not used for now.
public class AppUserController {

    @Autowired
    AppUserService appUserService;


    //sign-up: request body -- POST
    @PostMapping("/")
    //@RequestMapping(path = "/", method = RequestMethod.POST) //This is an alternative for @PostMapping
    public ResponseEntity<AppUserDto> signup(@RequestBody @Valid AppUserDto dto){
        System.out.println("Username: " + dto.getUsername());
        AppUserDto serviceResult = appUserService.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceResult);
    }


    // /{username}  search: path variable -- GET
    @GetMapping("/{username}")
    public ResponseEntity<Map<String, Object>> findByUsername(@PathVariable("username") String username){
        return ResponseEntity.ok().body(appUserService.findByUsername(username));
    }

    // /{username}  disable user: path variable -- PUT
    @PutMapping("/disable")
    public ResponseEntity<Void> disableUserByUsername(@RequestParam("username") String username){
        appUserService.disableUserByUsername(username);
        return ResponseEntity.noContent().build();
    }
}
