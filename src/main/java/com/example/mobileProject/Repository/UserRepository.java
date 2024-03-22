package com.example.mobileProject.Repository;


import com.example.mobileProject.Model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {

    @Query(nativeQuery = true, value="SELECT email FROM users where email=:email")
    List<String> checkEmail(@Param("email") String Email);

    @Query(nativeQuery = true, value = "SELECT password FROM users where email=:email")
    String checkPassword(@Param("email") String email);


    @Transactional
    @Modifying
    @Query(nativeQuery = true, value="INSERT INTO USERS(first_name, last_name, email, password) VALUES(:first_name, :last_name, :email, :password)")
    int registerNewUser(@Param("first_name") String first_name,
                        @Param("last_name") String last_name,
                        @Param("email") String email,
                        @Param("password") String password);

}
