package com.julio.demo.service;

import com.julio.demo.config.Http;
import com.julio.demo.model.Token;
import com.julio.demo.model.dto.APITokenDTO;
import com.julio.demo.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    
    @Autowired
    TokenRepository tokenRepository;
    
    @Autowired
    Http http;
    
    public APITokenDTO salvarToken(Token token) throws Exception {
        
        if (token.getToken().isEmpty()) {
            throw new Exception("Token vazio!");
        }
        
        APITokenDTO tokenDto = isTokenValido(token);
        
        if (tokenDto == null) {
            throw new Exception("Token inválido!");
        }
        
        try {
            tokenRepository.deleteAll();
            tokenRepository.save(token);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            throw (Exception) ex;
        }
        
        return tokenDto;
    }
    
    public Token getToken() {
        return tokenRepository.findAll().get(0);
    }
    
    public APITokenDTO isTokenValido(Token token) { 
        String url = "https://api.telegram.org/bot{id}/getMe";

        ResponseEntity<APITokenDTO> retorno = http.restTemplate().getForEntity(url, APITokenDTO.class, token.getToken());
        
        if (retorno.getStatusCode() == HttpStatus.UNAUTHORIZED
                || retorno.getStatusCode() == HttpStatus.FORBIDDEN) {
            return null;
        }
        
        return retorno.getBody();
    }
    
}
