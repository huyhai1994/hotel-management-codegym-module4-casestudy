package com.codegym.hotelmanagementsystemcodegymmodule4.service.impl;

import com.codegym.hotelmanagementsystemcodegymmodule4.dto.Response;
import com.codegym.hotelmanagementsystemcodegymmodule4.dto.UpdateUserDTO;
import com.codegym.hotelmanagementsystemcodegymmodule4.dto.UserDTO;
import com.codegym.hotelmanagementsystemcodegymmodule4.entity.User;
import com.codegym.hotelmanagementsystemcodegymmodule4.exception.OurException;
import com.codegym.hotelmanagementsystemcodegymmodule4.repository.UserRepository;
import com.codegym.hotelmanagementsystemcodegymmodule4.service.interfac.IUserService;
import com.codegym.hotelmanagementsystemcodegymmodule4.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Response getAllUsers() {

        Response response = new Response();
        try {
            List<User> userList = userRepository.findAll();
            List<UserDTO> userDTOList = Utils.mapUserListEntityToUserListDTO(userList);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setUserList(userDTOList);

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error getting all users " + e.getMessage());
        }
        return response;
    }

    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public Response getUserBookingHistory(String userId) {

        Response response = new Response();


        try {
            User user = userRepository.findById(Long.valueOf(userId)).orElseThrow(() -> new OurException("User Not Found"));
            UserDTO userDTO = Utils.mapUserEntityToUserDTOPlusUserBookingsAndRoom(user);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setUser(userDTO);

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {

            response.setStatusCode(500);
            response.setMessage("Error getting all users " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response deleteUser(String userId) {

        Response response = new Response();

        try {
            userRepository.findById(Long.valueOf(userId)).orElseThrow(() -> new OurException("User Not Found"));
            userRepository.deleteById(Long.valueOf(userId));
            response.setStatusCode(200);
            response.setMessage("successful");

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {

            response.setStatusCode(500);
            response.setMessage("Error getting all users " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response getUserById(String userId) {

        Response response = new Response();

        try {
            User user = userRepository.findById(Long.valueOf(userId)).orElseThrow(() -> new OurException("User Not Found"));
            UserDTO userDTO = Utils.mapUserEntityToUserDTO(user);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setUser(userDTO);

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {

            response.setStatusCode(500);
            response.setMessage("Error getting all users " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response getMyInfo(String email) {

        Response response = new Response();

        try {
            User user = userRepository.findByEmail(email);
            UserDTO userDTO = Utils.mapUserEntityToUserDTO(user);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setUser(userDTO);

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {

            response.setStatusCode(500);
            response.setMessage("Error getting all users " + e.getMessage());
        }
        return response;
    }


    @Override
    public UserDTO updateUserInfo(Long userId, UpdateUserDTO updateUserDTO) {
        Optional<User> userOptional = userRepository.findById(userId);

        User user = userOptional.get();
        user.setName(updateUserDTO.getName());
        user.setBirthday(updateUserDTO.getBirthday());
        user.setPhoneNumber(updateUserDTO.getPhoneNumber());
        user.setAvatar(updateUserDTO.getAvatar());

        User updatedUser = userRepository.save(user);

        return convertToDTO(updatedUser);
    }

    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setBirthday(user.getBirthday());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setAvatar(user.getAvatar());
        userDTO.setRole(user.getRole());
        return userDTO;
    }
}
