package com.julio.demo;

import java.util.LinkedList;
import java.util.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class FilaComandos {
    
    Queue<String> fila = new LinkedList<>();

    public String addFilaComandos(String comando) {
        fila.add(comando);
        
        return "Comando adicionado a fila!";
    }
    
    public String executarProximoComando() {
        if (fila.isEmpty()) {
            return "A fila está vazia!";
        }
        
        return fila.poll();
    }
    
    public int getQtdNaFila() {        
        return fila.size();
    }
}
