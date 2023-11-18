package com.example.placement_backend.Repositories;

import com.example.placement_backend.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CompanyRepository extends JpaRepository<Company , Integer> {
}
