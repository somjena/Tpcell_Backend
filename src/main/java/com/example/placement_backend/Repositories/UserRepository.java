package com.example.placement_backend.Repositories;

import com.example.placement_backend.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,String> {
    Users findByCid(int cid);
}
