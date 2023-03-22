package com.julio.demo.service;

import com.julio.demo.config.Http;
import com.julio.demo.model.ArduinoIP;
import com.julio.demo.repository.ArduinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ArduinoService {
    
    @Autowired
    ArduinoRepository arduinoRepository;
    
    @Autowired
    Http http;
    
    public void salvarIp(ArduinoIP ip) throws Exception {
        
        if (ip.getIp().isEmpty()) {
            throw new Exception("IP vazio!");
        }
        
        boolean encontrouArduino = isIpValido(ip);
        
        if (!encontrouArduino) {
            throw new Exception("IP inválido!");
        }
        
        try {
            arduinoRepository.deleteAll();
            arduinoRepository.save(ip);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            throw (Exception) ex;
        }
    }
    
    public boolean isIpValido(ArduinoIP ip) { 
        String url = "http://{ip}/valid";

        try {
            http.restTemplate().getForEntity(url, Void.class, ip.getIp());
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return false;
        }
        
        return true;
    }
    
    public void piscar() { 
        String url = "http://192.168.15.21:80/piscar";

        try {
            http.restTemplate().getForEntity(url, Void.class);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        
    }
}
