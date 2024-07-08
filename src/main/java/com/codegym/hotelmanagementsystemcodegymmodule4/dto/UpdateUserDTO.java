package com.codegym.hotelmanagementsystemcodegymmodule4.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateUserDTO {
    private String name;
    private LocalDate birthday;
    private String phoneNumber;
    private String avatar;
}
