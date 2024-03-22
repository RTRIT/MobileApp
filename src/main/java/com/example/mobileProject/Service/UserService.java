package com.example.mobileProject.Service;

import com.example.mobileProject.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // được đánh dấu là một module và được thêm voà IoC container khi IoC quét
public class UserService {
    @Autowired
    UserRepository userRepository;
    public int registerNewUserServiceMethod(String fname, String lname, String email, String password){
        return userRepository.registerNewUser(fname,lname,email,password);
    }

    public List<String> checkUserEmail(String Email){
        return userRepository.checkEmail(Email);
    }

    public String checkUserPasswordByEmail(String Email){
        return userRepository.checkPassword(Email);
    }

}
