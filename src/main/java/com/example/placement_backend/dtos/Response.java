package com.example.placement_backend.dtos;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class Response {

    public static ResponseEntity<?> success(Object data) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", "success");
        if(data != null)
            map.put("data", data);
        return ResponseEntity.ok(map);
    }

    public static ResponseEntity<?> error(Object err) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", "error");
        if(err != null)
            map.put("error", err);
        return ResponseEntity.ok(map);
    }

    public static ResponseEntity<?> status(HttpStatus status) {
        return ResponseEntity.status(status).build();
    }
}
