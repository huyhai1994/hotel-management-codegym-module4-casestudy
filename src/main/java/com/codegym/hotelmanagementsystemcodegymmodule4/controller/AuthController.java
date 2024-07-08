package com.codegym.hotelmanagementsystemcodegymmodule4.controller;

import com.codegym.hotelmanagementsystemcodegymmodule4.config.UserServices;
import com.codegym.hotelmanagementsystemcodegymmodule4.config.service.JwtResponse;
import com.codegym.hotelmanagementsystemcodegymmodule4.config.service.JwtService;
import com.codegym.hotelmanagementsystemcodegymmodule4.entity.Role;
import com.codegym.hotelmanagementsystemcodegymmodule4.entity.RoleName;
import com.codegym.hotelmanagementsystemcodegymmodule4.entity.User;
import com.codegym.hotelmanagementsystemcodegymmodule4.service.interfac.IRoleService;
import com.codegym.hotelmanagementsystemcodegymmodule4.service.interfac.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

/*TODO: s-step 8 Create AuthController*/
@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserServices userServices;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        /*TODO: @RequestBody is a Spring MVC annotation used to bind the
           incoming HTTP request body to a method parameter.
            It is commonly used in RESTful APIs to receive
            JSON or XML payloads and convert them into Java objects.
                In the provided code snippet, the @RequestBody annotation
                 is used in the login method to bind the incoming JSON payload
                 containing the user's username and password to the User object.
                  This allows the method to access the username and
                   password values directly from the request body.*/
        /*TODO: Step 1 khi dang nhap phai vao day de check Security Context*/
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userServices.findUserByEmail(user.getEmail());
        /*TODO: Tra ve Jwt Response sau khi dang nhap thanh cong*/
        return ResponseEntity.ok(new JwtResponse(currentUser.getId(), jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        String pw = passwordEncoder.encode(user.getPassword());
        user.setPassword(pw);

        // Save the Role object before associating it with the User object
        Set<Role> roles = new HashSet<>();
        Role role = roleService.findByName(RoleName.ROLE_USER.toString());
        roles.add(role);
        user.setRoles(roles);

        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
