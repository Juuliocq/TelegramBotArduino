package com.julio.demo.controller;

import com.github.kshashov.telegram.api.MessageType;
import com.github.kshashov.telegram.api.TelegramMvcController;
import com.github.kshashov.telegram.api.bind.annotation.BotController;
import com.github.kshashov.telegram.api.bind.annotation.BotPathVariable;
import com.github.kshashov.telegram.api.bind.annotation.BotRequest;
import com.github.kshashov.telegram.api.bind.annotation.request.MessageRequest;
import com.julio.demo.FilaComandos;
import com.julio.demo.service.ArduinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;

@BotController
@DependsOn("applicationConfigurations")
public class MyBot implements TelegramMvcController {

    @Value("${bot.token}")
    private String token;
    
    @Autowired
    ArduinoService arduino;

    @Override
    public String getToken() {
        return token;
    }
    
    @BotRequest(type = {MessageType.ANY})
    public String padrao() {
        return "Digite um comando válido, por favor.";
    }
    
    @MessageRequest("/ligarlampada")
    public String ligarLampada() {
        arduino.ligarLampada();
        
        return "Lâmpada ligada!";
    }
    
    @MessageRequest("/abrircortina")
    public String abrirCortina() {
        arduino.abrirCortina();
        
        return "cortina aberta!";
    }  
    
}
