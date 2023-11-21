package com.example.placement_backend.controllers;

import com.example.placement_backend.dtos.DashboardDTO;
import com.example.placement_backend.dtos.Response;
import com.example.placement_backend.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/admin")
public class AdminController {


    @Autowired
    StudentService sservice;
    @Autowired
    HodService hservice;
    @Autowired
    CompanyService cservice;
    @Autowired
    SettingsService settings;
    @Autowired
    JobApplyService jservice;

    @GetMapping("/dashboard")
    public ResponseEntity<?> dashboardinfo() {
        DashboardDTO result=new DashboardDTO();
        result.setStudents(sservice.findAll().size());
        result.setCompany(cservice.listAll().size());
        result.setHods(hservice.listAll().size());
        result.setSelected(jservice.listSelectedCandidates().size());
        return Response.success(result);
    }

    @GetMapping("/settings")
    public ResponseEntity<?> collegeInfo() {
        return Response.success(settings.findSettings());
    }

    @PostMapping("/settings")
    public ResponseEntity<?> updateInfo(@RequestBody Settings setting) {
        setting.setId(settings.findSettings().getId());
        settings.saveSettings(setting);
        return Response.success("Settings Updated");
    }
}
