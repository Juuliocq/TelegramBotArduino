package com.julio.demo;

import com.julio.demo.model.dto.ArduinoComandoDTO;
import java.util.LinkedList;
import java.util.Queue;
import org.springframework.stereotype.Component;

@Component
public class FilaComandos {
    
    Queue<ArduinoComandoDTO> fila = new LinkedList<>();

    public String addFilaComandos(ArduinoComandoDTO comando) {
        fila.add(comando);
        
        return "Comando adicionado a fila!";
    }
    
//    public String executarProximoComando() {
//        if (fila.isEmpty()) {
//            return "A fila está vazia!";
//        }
//        
//        return fila.poll();
//    }
    
    public int getQtdNaFila() {        
        return fila.size();
    }
}
