package com.codegym.hotelmanagementsystemcodegymmodule4.service.interfac;

import com.codegym.hotelmanagementsystemcodegymmodule4.dto.LoginRequest;
import com.codegym.hotelmanagementsystemcodegymmodule4.dto.Response;
import com.codegym.hotelmanagementsystemcodegymmodule4.entity.User;

public interface IUserService {


    Response getAllUsers();

    Response getUserBookingHistory(String userId);

    Response deleteUser(String userId);

    Response getUserById(String userId);

    Response getMyInfo(String email);
}
