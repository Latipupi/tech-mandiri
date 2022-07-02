package com.mandiri.tech.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BaseResponse {

    public static ResponseEntity<Object> jsonResponse(HttpStatus status, String responseCode , boolean isSuccess, String message, Object obj) {
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("timestamp", new Date());
            map.put("status", status.value());
            map.put("responseCode", responseCode);
            map.put("success", isSuccess);
            map.put("message", message);
            map.put("data", obj);
            return new ResponseEntity<Object>(map,status);
        } catch (Exception e) {
            map.clear();
            map.put("timestamp", new Date());
            map.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            map.put("responseCode", "ER-99");
            map.put("success",false);
            map.put("message", e.getMessage());
            map.put("data", null);
            return new ResponseEntity<Object>(map,status);
        }
    }
}
