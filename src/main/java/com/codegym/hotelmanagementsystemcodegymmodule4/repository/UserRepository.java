package com.codegym.hotelmanagementsystemcodegymmodule4.repository;

import com.codegym.hotelmanagementsystemcodegymmodule4.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.stereotype.Repository;


=======
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/*TODO: s-step 3 creating user interface repository */
>>>>>>> ca13218f4ca5ea7008259f6848ed2341b57dd364
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
// Assuming you have a Role entity with a name attribute


   /* @Query("SELECT u from User u where u.email = ?1")*/
    User findByEmail(String email);
}
