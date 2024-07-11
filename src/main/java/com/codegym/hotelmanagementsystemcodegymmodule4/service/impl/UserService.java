package com.codegym.hotelmanagementsystemcodegymmodule4.service.impl;

import com.codegym.hotelmanagementsystemcodegymmodule4.dto.PasswordDTO;
import com.codegym.hotelmanagementsystemcodegymmodule4.dto.Response;
import com.codegym.hotelmanagementsystemcodegymmodule4.dto.ProfileUserDTO;
import com.codegym.hotelmanagementsystemcodegymmodule4.dto.UserDTO;

import com.codegym.hotelmanagementsystemcodegymmodule4.entity.Role;

import com.codegym.hotelmanagementsystemcodegymmodule4.entity.User;
import com.codegym.hotelmanagementsystemcodegymmodule4.exception.OurException;
import com.codegym.hotelmanagementsystemcodegymmodule4.repository.UserRepository;
import com.codegym.hotelmanagementsystemcodegymmodule4.service.interfac.IUserService;
import com.codegym.hotelmanagementsystemcodegymmodule4.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import java.util.Set;


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
    public UserDTO profileUserInfo(Long userId, ProfileUserDTO profileUserDTO) {
        Optional<User> userOptional = userRepository.findById(userId);

        User user = userOptional.get();
        user.setName(profileUserDTO.getName());
        user.setBirthday(profileUserDTO.getBirthday());
        user.setPhoneNumber(profileUserDTO.getPhoneNumber());
        user.setAvatar(String.valueOf(profileUserDTO.getAvatar()));

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

        Set<Role> roles = new HashSet<>();
        for (Role role : user.getRoles()) {
            roles.add(role);
        }
        userDTO.setRole(roles);

        return userDTO;
    }



    @Override
    public void updatePassword(String userEmail, PasswordDTO passwordDTO) {
        String newPassword = passwordDTO.getNewPassword();
        String confirmPassword = passwordDTO.getConfirmPassword();
        if (!newPassword.equals(confirmPassword)) {
            throw new IllegalArgumentException("The new password does not match.");
        }
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByEmail(userEmail));
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
