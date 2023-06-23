package com.example.InventoryManagement.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ShoeUtils {
    //Utility methods used in any service classes

    private ShoeUtils(){

    }

    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus){
        return new ResponseEntity<String>("{\"message\":\""+responseMessage+"\"}", httpStatus);
    }
}
