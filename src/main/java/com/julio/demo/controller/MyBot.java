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
    
    //Cômodos inferiores
    @MessageRequest("/acender_garagem")
    public String ligarLampada() {
        arduino.executarComando("/acender_garagem");
        
        return "Lâmpada da garagem acesa!";
    }
    
    @MessageRequest("/apagar_garagem")
    public String desligarLampada() {
        arduino.executarComando("/apagar_garagem");
        
        return "Lâmpada da garagem apagada!";
    }
    
    @MessageRequest("/acender_banheiro_1")
    public String ligarLampada2() {
        arduino.executarComando("/acender_banheiro_1");
        
        return "Lâmpada banheiro 1 acesa!";
    }
    
    @MessageRequest("/apagar_banheiro_2")
    public String desligarLampada2() {
        arduino.executarComando("/apagar_banheiro_1");
        
        return "Lâmpada banheiro 1 apagada!";
    }
    @MessageRequest("/acender_sala")
    public String ligarLampada3() {
        arduino.executarComando("/acender_sala");
        
        return "Lâmpada sala acesa!";
    }
    
    @MessageRequest("/acender_sala")
    public String desligarLampada3() {
        arduino.executarComando("/apagar_sala");
        
        return "Lâmpada sala apagada!";
    }
    
    //Cômodos superiores
    @MessageRequest("/acender_quarto_1")
    public String ligarLampada4() {
        arduino.executarComando("/acender_quarto_1");
        
        return "Lâmpada quarto 1 acesa!";
    }
    
    @MessageRequest("/apagar_quarto_1")
    public String desligarLampada4() {
        arduino.executarComando("/apagar_quarto_1");
        
        return "Lâmpada quarto 1 apagada!";
    }
    
    @MessageRequest("/acender_quarto_2")
    public String ligarLampada5() {
        arduino.executarComando("/acender_quarto_2");
        
        return "Lâmpada quarto 2 acesa!";
    }
    
    @MessageRequest("/apagar_quarto_2")
    public String desligarLampada5() {
        arduino.executarComando("/apagar_quarto_2");
        
        return "Lâmpada quarto 2 apagada!";
    }  
    @MessageRequest("/acender_banheiro_2")
    public String ligarLampada6() {
        arduino.executarComando("/acender_banheiro_2");
        
        return "Lâmpada banheiro 2 acesa!";
    }
    
    @MessageRequest("/apagar_banheiro_2")
    public String desligarLampada6() {
        arduino.executarComando("/apagar_banheiro_2");
        
        return "Lâmpada banheiro 2 apagada!";
    }  
}
