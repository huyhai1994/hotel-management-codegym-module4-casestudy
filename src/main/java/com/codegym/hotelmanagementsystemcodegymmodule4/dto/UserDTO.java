package com.codegym.hotelmanagementsystemcodegymmodule4.dto;

import com.codegym.hotelmanagementsystemcodegymmodule4.entity.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private Long id;
    private String email;
    private String name;
    private String phoneNumber;
    private String password;
    private LocalDate birthday;
    private String avatar;
    private Set<Role> role;

    private List<BookingDTO> bookings = new ArrayList<>();

}
