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
    FilaComandos filaComandos;
    
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
    
    @MessageRequest("/adicionar {comando:[\\S]+}")
    public String adicionar(@BotPathVariable("comando") String comando) {
        if (comando.toLowerCase().equals("para")) {
            return "para você";
        }
        
        return filaComandos.addFilaComandos(comando);
    }
    
    @MessageRequest("/executar")
    public String executar() {
        return filaComandos.executarProximoComando();
    }
    
    @MessageRequest("/piscar")
    public String piscar() {
        
    arduino.piscar();
    return "Piscou?";
    }
    
    @MessageRequest("/executar2")
    public String executar2() {
        return executar();
    }
    
    @MessageRequest("/quantidade")
    public String quantidade() {
        int qtdNaFila = filaComandos.getQtdNaFila();
        
        String retorno = qtdNaFila == 0 ? "A fila está vazia!" : "Na fila existem " + qtdNaFila + "comandos!";
        
        return retorno;
    }
    
}
