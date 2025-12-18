package com.example.demo.repositories;

import com.example.demo.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GroupRepository extends JpaRepository<Group, Long> {
    Group findByName(String name);
}
