package com.codegym.hotelmanagementsystemcodegymmodule4.service.interfac;

import com.codegym.hotelmanagementsystemcodegymmodule4.dto.PasswordDTO;
import com.codegym.hotelmanagementsystemcodegymmodule4.dto.ProfileUserDTO;
import com.codegym.hotelmanagementsystemcodegymmodule4.dto.Response;
import com.codegym.hotelmanagementsystemcodegymmodule4.dto.UserDTO;
import com.codegym.hotelmanagementsystemcodegymmodule4.entity.User;

public interface IUserService {

    Response getAllUsers();

    Response getUserBookingHistory(String userId);

    Response deleteUser(String userId);

    Response getUserById(String userId);

    Response getMyInfo(String email);

    UserDTO profileUserInfo(Long userId, ProfileUserDTO profileUserDTO);

    void save(User user);

    void updatePassword(String newPassword, PasswordDTO confirmPassword);
}