package com.example.placement_backend.services;

import com.example.placement_backend.Repositories.SettingsRepository;
import com.example.placement_backend.entity.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingService {


    @Autowired
    SettingsRepository repo;

    public void saveSettings(Settings ss) {
        repo.save(ss);
    }

    public Settings findSettings() {
        return repo.getById(1);
    }

}
