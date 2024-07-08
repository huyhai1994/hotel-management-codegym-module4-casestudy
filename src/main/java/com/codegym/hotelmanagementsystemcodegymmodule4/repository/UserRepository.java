package com.codegym.hotelmanagementsystemcodegymmodule4.repository;

import com.codegym.hotelmanagementsystemcodegymmodule4.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/*TODO: s-step 3 creating user interface repository */
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
