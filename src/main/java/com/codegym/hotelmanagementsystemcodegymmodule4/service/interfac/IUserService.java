package com.codegym.hotelmanagementsystemcodegymmodule4.service.interfac;

<<<<<<< HEAD
import com.codegym.hotelmanagementsystemcodegymmodule4.dto.*;
=======
import com.codegym.hotelmanagementsystemcodegymmodule4.dto.Response;
import com.codegym.hotelmanagementsystemcodegymmodule4.dto.UpdateUserDTO;
import com.codegym.hotelmanagementsystemcodegymmodule4.dto.UserDTO;
>>>>>>> ca13218f4ca5ea7008259f6848ed2341b57dd364
import com.codegym.hotelmanagementsystemcodegymmodule4.entity.User;

public interface IUserService {

    Response getAllUsers();

    Response getUserBookingHistory(String userId);

    Response deleteUser(String userId);

    Response getUserById(String userId);

    Response getMyInfo(String email);

    UserDTO updateUserInfo(Long userId, UpdateUserDTO updateUserDTO);

    void save(User user);
}
