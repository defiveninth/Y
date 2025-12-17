package com.example.Midka.repositories;

import com.example.Midka.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GroupRepository extends JpaRepository<Group, Long> {
    Group findByName(String name);
}
