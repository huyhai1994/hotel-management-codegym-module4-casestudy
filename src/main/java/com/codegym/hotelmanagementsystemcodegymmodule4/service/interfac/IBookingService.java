package com.codegym.hotelmanagementsystemcodegymmodule4.service.interfac;

import com.codegym.hotelmanagementsystemcodegymmodule4.dto.Response;
import com.codegym.hotelmanagementsystemcodegymmodule4.entity.Booking;

public interface IBookingService {

    Response saveBooking(Long roomId, Long userId, Booking bookingRequest);

    Response findBookingByConfirmationCode(String confirmationCode);

    Response getAllBookings();

    Response cancelBooking(Long bookingId);

}
