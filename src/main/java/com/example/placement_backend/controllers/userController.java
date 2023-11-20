package com.example.placement_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping
public class userController {


    @Autowired
    UsersService uservice;

    @PostMapping("/validate")
    public ResponseEntity<?> validateUser(@RequestBody LoginDTO dto) {
        System.out.println(dto);
        Users user=uservice.validate(dto.getUserid(), dto.getPwd(),dto.getRole());
        if(user!=null)
            return Response.success(user);
        else
            return Response.status(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verifyEmail(String email) {
        Users user=uservice.verify(email);
        if(user!=null)
            return Response.error("Not Available");
        else
            return Response.success("Available");
    }

    @PostMapping("changepwd")
    public ResponseEntity<?> save(@RequestBody LoginDTO dto) {
        System.out.println(dto);
        Users user=uservice.findUser(dto.getUserid());
        if(user.getPwd().equals(dto.getOld())) {
            user.setPwd(dto.getPwd());
            uservice.updateUser(user);
            return Response.success(user);
        }else {
            return Response.error("Incorrect current password");
        }
    }
}
