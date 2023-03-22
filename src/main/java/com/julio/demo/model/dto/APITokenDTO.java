package com.julio.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class APITokenDTO {
    
    boolean ok;
    String field_name;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    @JsonProperty("result")
    public void setField_name(Map<String, String> result) {
        field_name = result.get("first_name");
    }

    public String getField_name() {
        return field_name;
    }  
    
    
}
