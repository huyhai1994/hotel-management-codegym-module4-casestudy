package com.codegym.hotelmanagementsystemcodegymmodule4.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateUserDTO {
    private String name;
    private LocalDate birthday;
    private String phoneNumber;
    private MultipartFile avatar;
}
