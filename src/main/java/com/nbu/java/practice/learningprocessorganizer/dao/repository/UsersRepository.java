package com.nbu.java.practice.learningprocessorganizer.dao.repository;

import com.nbu.java.practice.learningprocessorganizer.dao.entity.users.UserIdentity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UserIdentity, Long> {

    UserIdentity findByUsername(String username);

}
