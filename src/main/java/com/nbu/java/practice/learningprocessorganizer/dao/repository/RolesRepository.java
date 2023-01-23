package com.nbu.java.practice.learningprocessorganizer.dao.repository;

import com.nbu.java.practice.learningprocessorganizer.dao.entity.users.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Role, Long> {

    Role getByAuthority(String authority);

}
