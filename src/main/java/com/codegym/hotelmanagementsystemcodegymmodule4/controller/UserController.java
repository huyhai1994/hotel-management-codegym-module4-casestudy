package com.codegym.hotelmanagementsystemcodegymmodule4.controller;


import com.codegym.hotelmanagementsystemcodegymmodule4.dto.Response;
import com.codegym.hotelmanagementsystemcodegymmodule4.dto.UpdateUserDTO;
import com.codegym.hotelmanagementsystemcodegymmodule4.dto.UserDTO;
import com.codegym.hotelmanagementsystemcodegymmodule4.service.impl.UserService;
import com.codegym.hotelmanagementsystemcodegymmodule4.service.interfac.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
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


    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUserInfo(
            @PathVariable Long id,
            @ModelAttribute UpdateUserDTO updateUserDTO) {
        UserDTO updatedUser = userService.updateUserInfo(id, updateUserDTO);
        return ResponseEntity.ok(updatedUser);
    }
}
