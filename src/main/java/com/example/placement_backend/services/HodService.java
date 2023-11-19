package com.example.placement_backend.services;

import com.example.placement_backend.Repositories.HodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class HodService {
    @Autowired
    HodRepository repo;

    public int saveHOD(HOD hod) {
        HOD hh=repo.save(hod);
        return hh.getId();
    }

    public List<HOD> listAll(){
        return repo.findAll(Sort.by(Direction.DESC, "id"));
    }

    public HOD getDetails(int id) {
        return repo.getById(id);
    }

    public void deleteById(int id) {
        HOD std=repo.getById(id);
        repo.delete(std);
    }
}
