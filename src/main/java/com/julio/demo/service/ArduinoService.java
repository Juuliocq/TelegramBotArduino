package com.julio.demo.service;

import com.julio.demo.FilaComandos;
import com.julio.demo.config.Http;
import com.julio.demo.model.ArduinoIP;
import com.julio.demo.model.dto.ArduinoComandoDTO;
import com.julio.demo.model.dto.response.IPArduinoResponse;
import com.julio.demo.model.dto.response.TokenResponseDTO;
import com.julio.demo.repository.ArduinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ArduinoService {
    
    @Autowired
    ArduinoRepository arduinoRepository;
    
    @Autowired
    Http http;
    
    @Autowired
    FilaComandos filaComandos;
    
    public void salvarIp(ArduinoIP ip) throws Exception {
        
        if (ip.getIp().isEmpty()) {
            throw new Exception("IP vazio!");
        }
        
        //boolean encontrouArduino = isIpValido(ip);
        
//        if (!encontrouArduino) {
//            throw new Exception("IP inválido!");
//        }
        
        try {
            ip.setIp(ip.getIp());
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
    
    public String executarComando(String pComando) { 
        String url = getIp().getIp() + "/adicionarcomando";
        
        ArduinoComandoDTO comando = new ArduinoComandoDTO(pComando);
        http.restTemplate().postForEntity(url, comando, Void.class);
        
        return "O";
    }
    
    private ArduinoIP getIp() {
        return arduinoRepository.findAll().get(0);
    }
    
    public IPArduinoResponse getIpResponse() {
        IPArduinoResponse ipResponse = new IPArduinoResponse();
        ipResponse.setToken(getIp().toString());
        
        return ipResponse;
    }
}
