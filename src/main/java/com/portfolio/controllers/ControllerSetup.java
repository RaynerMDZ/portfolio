package com.portfolio.controllers;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;

@ControllerAdvice
public class ControllerSetup {
	
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(String.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if (text == null) {
                    return;
                }
    	        
                setValue(text.trim());
            }
    
    	    
            @Override
            public String getAsText() {
                Object value = getValue();
                return (value != null ? value.toString().trim() : "");		        
            }
        });
    }
}
