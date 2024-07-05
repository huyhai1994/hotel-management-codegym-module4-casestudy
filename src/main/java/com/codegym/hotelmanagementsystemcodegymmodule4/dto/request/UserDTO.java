package com.codegym.hotelmanagementsystemcodegymmodule4.dto.request;

import com.codegym.hotelmanagementsystemcodegymmodule4.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String address;
    private MultipartFile avatar;
    private Boolean status;
    private Set<Role> roles;

}
