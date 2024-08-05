package com.app.bookmygas.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.bookmygas.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByName(String name);
    // Custom query methods can be added here if needed

	User findByEmail(String email);
}
