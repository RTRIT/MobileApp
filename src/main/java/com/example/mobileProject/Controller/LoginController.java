package com.example.mobileProject.Controller;

import com.example.mobileProject.Model.LoginForm;
import com.example.mobileProject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class LoginController {
    @Autowired
    UserService userService;
    @PostMapping("/user/login")
    //Gửi request có body theo LoginForm
    public ResponseEntity login(@RequestBody LoginForm form) {
        // Check if the request body is properly formatted
        if (form == null || form.getEmail() == null || form.getPassword() == null) {
            return new ResponseEntity<>("Request body is missing required fields", HttpStatus.BAD_REQUEST);
        }
        // Get User Email:
        List<String> user_Email = userService.checkUserEmail(form.getEmail());
        // Check if user email exists
        if (user_Email.isEmpty()) {
            return new ResponseEntity<>("Email user is not registered yet!!", HttpStatus.BAD_REQUEST);
        }
        //Get hashed password
        String hashedPassword = userService.checkUserPasswordByEmail(form.getEmail());
        //Check input password from user and hashed password
        if(!BCrypt.checkpw(form.getPassword(),hashedPassword)){
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Login Success", HttpStatus.OK);

    }
}
