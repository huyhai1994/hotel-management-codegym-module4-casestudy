package com.codegym.hotelmanagementsystemcodegymmodule4.controller;


import com.codegym.hotelmanagementsystemcodegymmodule4.dto.PasswordDTO;
import com.codegym.hotelmanagementsystemcodegymmodule4.dto.ProfileUserDTO;
import com.codegym.hotelmanagementsystemcodegymmodule4.dto.Response;
import com.codegym.hotelmanagementsystemcodegymmodule4.dto.UserDTO;
import com.codegym.hotelmanagementsystemcodegymmodule4.service.interfac.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private IUserService userService;


    @GetMapping("/all")
    public ResponseEntity<Response> getAllUsers() {
        Response response = userService.getAllUsers();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/get-by-id/{userId}")
    public ResponseEntity<Response> getUserById(@PathVariable("userId") String userId) {
        Response response = userService.getUserById(userId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Response> deleteUSer(@PathVariable("userId") String userId) {
        Response response = userService.deleteUser(userId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }


    @GetMapping("/get-user-bookings/{userId}")
    public ResponseEntity<Response> getUserBookingHistory(@PathVariable("userId") String userId) {
        Response response = userService.getUserBookingHistory(userId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }


    @CrossOrigin("http://localhost:63343")
    @PutMapping("/profile/{id}")
    public ResponseEntity<UserDTO> profileUserInfo(
            @PathVariable Long id,
            @ModelAttribute ProfileUserDTO profileUserDTO) {
        UserDTO updatedUser = userService.profileUserInfo(id, profileUserDTO);
        return ResponseEntity.ok(updatedUser);
    }


    @PostMapping("/update-password/{id}")
    public ResponseEntity<?> updatePassword(@RequestBody Map<String, Object> request) {
        String userEmail = (String) request.get("email");
        String newPassword = (String) request.get("newPassword");
        String confirmPassword = (String) request.get("confirmPassword");
        PasswordDTO passwordDTO = new PasswordDTO();
        passwordDTO.setNewPassword(newPassword);
        passwordDTO.setConfirmPassword(confirmPassword);

        try {
            userService.updatePassword(userEmail, passwordDTO);
            return ResponseEntity.ok(Map.of("success", true, "message", "Updated password successfully!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("success", false, "message", e.getMessage()));
        }
    }
}