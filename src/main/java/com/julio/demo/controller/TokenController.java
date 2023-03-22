package com.julio.demo.controller;

import com.julio.demo.model.Token;
import com.julio.demo.model.dto.APITokenDTO;
import com.julio.demo.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {
    
    @Autowired
    TokenService tokenService;
    
    @PatchMapping(value = "/token")
    @ResponseBody
    public ResponseEntity<APITokenDTO> patch(@RequestBody Token token) {
        try {
            
            APITokenDTO apiToken = tokenService.salvarToken(token);
                    
            return new ResponseEntity<APITokenDTO>(apiToken, HttpStatus.OK);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ResponseEntity("Token inválido!", HttpStatus.BAD_REQUEST);
        } 
    }  
    
    @GetMapping(value = "/macaco")
    public void macaco() {
        System.out.println("macaco");
    } 
}
