package com.julio.demo.controller;

import com.julio.demo.model.ArduinoIP;
import com.julio.demo.service.ArduinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArduinoController {
    
    @Autowired
    ArduinoService arduinoService;
    
    @PatchMapping(value = "/ipArduino")
    @ResponseBody
    public ResponseEntity patch(@RequestBody ArduinoIP arduinoIP) {
        try {
            
            arduinoService.salvarIp(arduinoIP);
                    
            return new ResponseEntity("IP salvo com sucesso!", HttpStatus.OK);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ResponseEntity("IP do arduino inválido!", HttpStatus.BAD_REQUEST);
        } 
    }  
}
