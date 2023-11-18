package com.example.placement_backend.Repositories;

import com.example.placement_backend.entity.Company;
import com.example.placement_backend.entity.JobApplication;
import com.example.placement_backend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication,Integer> {

    @Override
    List<JobApplication>findByStudent(Student student);

    @Override
    List<JobApplication>findByCompanyAndStatus(Company company,String status);

    @Override
    List<JobApplication> findByStatus(String status);
}
