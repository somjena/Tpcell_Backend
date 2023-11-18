package com.example.placement_backend.Repositories;

import com.example.placement_backend.entity.Company;
import com.example.placement_backend.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job,Integer> {
    @Override
    List<Job>findByCompanyOrderById(Company company);

}
