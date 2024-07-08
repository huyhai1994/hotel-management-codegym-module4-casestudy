package com.codegym.hotelmanagementsystemcodegymmodule4.repository;

import com.codegym.hotelmanagementsystemcodegymmodule4.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    Optional<Booking> findByBookingConfirmationCode(String confirmationCode);
}
