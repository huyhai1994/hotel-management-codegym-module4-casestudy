package com.codegym.hotelmanagementsystemcodegymmodule4.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private Long id;
    private String email;
    private String name;
    private String phoneNumber;
<<<<<<< HEAD
    private String avatar;
    private LocalDate birthday;
=======
    private String roles;
    private String password;
    private LocalDate birthday;
    private String avatar;
    private String role;
>>>>>>> ca13218f4ca5ea7008259f6848ed2341b57dd364
    private List<BookingDTO> bookings = new ArrayList<>();

}
