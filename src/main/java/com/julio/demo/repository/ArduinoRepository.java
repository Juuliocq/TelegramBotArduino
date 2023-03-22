package com.julio.demo.repository;

import com.julio.demo.model.ArduinoIP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArduinoRepository extends JpaRepository<ArduinoIP, String> {
    
}
