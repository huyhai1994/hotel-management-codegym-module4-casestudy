package com.codegym.hotelmanagementsystemcodegymmodule4.repository;

import com.codegym.hotelmanagementsystemcodegymmodule4.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
