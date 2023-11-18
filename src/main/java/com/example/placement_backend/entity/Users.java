package com.example.placement_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Users {
    @Id
    private String userid;
    private String uname;
    private String pwd;
    private int cid;
    private String role;
    private boolean active;
    private LocalDateTime createdon;

    public Users(String userid, String uname, String pwd, int cid, String role, boolean active, LocalDateTime createdon) {
        this.userid = userid;
        this.uname = uname;
        this.pwd = pwd;
        this.cid = cid;
        this.role = role;
        this.active = active;
        this.createdon = createdon;
    }

    public Users() {

    }
}
