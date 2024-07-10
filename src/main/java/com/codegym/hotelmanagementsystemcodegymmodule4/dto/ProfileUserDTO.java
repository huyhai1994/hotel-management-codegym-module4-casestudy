package com.codegym.hotelmanagementsystemcodegymmodule4.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileUserDTO {
    private String name;
    private LocalDate birthday;
    private String phoneNumber;
    private MultipartFile avatar;
}
