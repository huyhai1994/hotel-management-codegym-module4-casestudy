package com.codegym.hotelmanagementsystemcodegymmodule4.service.interfac;

import com.codegym.hotelmanagementsystemcodegymmodule4.dto.ITotalPriceByMonth;
import com.codegym.hotelmanagementsystemcodegymmodule4.dto.Response;
import com.codegym.hotelmanagementsystemcodegymmodule4.dto.TotalPriceByMonth;
import com.codegym.hotelmanagementsystemcodegymmodule4.entity.Booking;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface IBookingService {

    Response saveBooking(Long roomId, Long userId, Booking bookingRequest);

    Response findBookingByConfirmationCode(String confirmationCode);

    Response getAllBookings();

    Response cancelBooking(Long bookingId);

    List<ITotalPriceByMonth> findTotalPriceByMonth(Integer month);
    List<ITotalPriceByMonth> getTotalPriceBYMonthAndYear(Integer month,  Integer year);

}
