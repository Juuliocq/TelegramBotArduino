package com.julio.demo.controller;

import com.julio.demo.model.Token;
import com.julio.demo.model.dto.APITokenDTO;
import com.julio.demo.model.dto.response.TokenResponseDTO;
import com.julio.demo.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {
    
    @Autowired
    TokenService tokenService;
    
    @CrossOrigin
    @PatchMapping(value = "/token")
    @ResponseBody
    public ResponseEntity patch(@RequestBody Token token) {
        try {
            
            tokenService.salvarToken(token);
                    
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } 
    }  
    
    @CrossOrigin
    @GetMapping(value = "/token")
    public ResponseEntity<TokenResponseDTO> get() {
        try {
            
            TokenResponseDTO tokenResponse = tokenService.getTokenResponse();
                    
            return new ResponseEntity<>(tokenResponse, HttpStatus.OK);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } 
    }
}
