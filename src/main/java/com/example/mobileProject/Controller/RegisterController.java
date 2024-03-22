package com.example.mobileProject.Controller;

import com.example.mobileProject.Model.User;
import com.example.mobileProject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class RegisterController {
    //Tìm module tương ứng trong IoC Container và Inject voà
    @Autowired
    UserService userService;
    @PostMapping("/user/register")
    public ResponseEntity registerNewUser(@RequestParam("first_name") String first_name,
                                          @RequestParam("last_name") String last_name,
                                          @RequestParam("email") String email,
                                          @RequestParam("password") String password
                                         ){
        // Kiểm tra xem field có rỗng không
        if(first_name.isEmpty() || last_name.isEmpty() || email.isEmpty() || password.isEmpty()){
            return new ResponseEntity<>("Please complete all fields", HttpStatus.BAD_REQUEST);
        }
        String hased_password = BCrypt.hashpw(password,BCrypt.gensalt());


        int result = userService.registerNewUserServiceMethod(first_name, last_name, email, hased_password);

        if(result != 1){
            return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }



}
