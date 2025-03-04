package com.codegym.hotelmanagementsystemcodegymmodule4.repository;

import com.codegym.hotelmanagementsystemcodegymmodule4.dto.ITotalPriceByMonth;
import com.codegym.hotelmanagementsystemcodegymmodule4.dto.TotalPriceByMonth;
import com.codegym.hotelmanagementsystemcodegymmodule4.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    Optional<Booking> findByBookingConfirmationCode(String confirmationCode);

    @Query(nativeQuery = true,value = "select sum(total_price) as totalPrice from bookings where  month(check_out_date) = :month_in and year(check_out_date) = 2024")
    ITotalPriceByMonth findTotalPriceByMonth(@Param("month_in") Integer month);

    @Query(nativeQuery = true, value = "select sum(total_price) as totalPrice from bookings where  month(check_out_date) = :month_in and year(check_out_date) = :year_in")
    List<ITotalPriceByMonth> getTotalPriceBYMonthAndYear(@Param("month_in") Integer month, @Param("year_in") Integer year);
}
