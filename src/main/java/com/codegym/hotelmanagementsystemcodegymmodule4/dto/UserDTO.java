package com.codegym.hotelmanagementsystemcodegymmodule4.dto;

import com.codegym.hotelmanagementsystemcodegymmodule4.entity.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


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
    private String avatar;
    private LocalDate birthday;
    private String roles;
    private String password;
    private String role;
    private LocalDate birthday;
    private String avatar;
    private Set<Role> role;

    private List<BookingDTO> bookings = new ArrayList<>();

}
