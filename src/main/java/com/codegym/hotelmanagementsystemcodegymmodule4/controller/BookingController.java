package com.codegym.hotelmanagementsystemcodegymmodule4.controller;


import com.codegym.hotelmanagementsystemcodegymmodule4.dto.ITotalPriceByMonth;
import com.codegym.hotelmanagementsystemcodegymmodule4.dto.Response;
import com.codegym.hotelmanagementsystemcodegymmodule4.dto.TotalPriceByMonth;
import com.codegym.hotelmanagementsystemcodegymmodule4.entity.Booking;
import com.codegym.hotelmanagementsystemcodegymmodule4.service.interfac.IBookingService;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin("*")
public class BookingController {

    @Autowired
    private IBookingService bookingService;

    @PostMapping("/book-room/{roomId}/{userId}")
    public ResponseEntity<Response> saveBookings(@PathVariable Long roomId,
                                                 @PathVariable Long userId,
                                                 @RequestBody Booking bookingRequest) {


        Response response = bookingService.saveBooking(roomId, userId, bookingRequest);
        return ResponseEntity.status(response.getStatusCode()).body(response);

    }

    @GetMapping("/all")
    public ResponseEntity<Response> getAllBookings() {
        Response response = bookingService.getAllBookings();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/get-by-confirmation-code/{confirmationCode}")
    public ResponseEntity<Response> getBookingByConfirmationCode(@PathVariable String confirmationCode) {
        Response response = bookingService.findBookingByConfirmationCode(confirmationCode);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/cancel/{bookingId}")

    public ResponseEntity<Response> cancelBooking(@PathVariable Long bookingId) {
        Response response = bookingService.cancelBooking(bookingId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/total-price-by-month/{month}")
    public ResponseEntity<List<ITotalPriceByMonth>> totalPriceByMonth(@PathVariable Integer month){
        return new ResponseEntity<>(bookingService.findTotalPriceByMonth(month), HttpStatus.OK);
    }
    @GetMapping("/total-price-by-month-and-year/{month}/{year}")
    public ResponseEntity<List<ITotalPriceByMonth>> totalPriceByMonthAndYear(@PathVariable Integer month,@PathVariable Integer year){
        return  new ResponseEntity<>(bookingService.getTotalPriceBYMonthAndYear(month,year), HttpStatus.OK);
    }


}
