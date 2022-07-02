package com.mandiri.tech.controller;

import com.mandiri.tech.dto.UserDTO;
import com.mandiri.tech.model.User;
import com.mandiri.tech.repository.UserRepository;
import com.mandiri.tech.util.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/create-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createUser(@RequestBody UserDTO user) {

        log.info("Create user .." + user);

        try {

            User newUser = new User();

            newUser.setName(user.getName());
            newUser.setAge(user.getAge());

            userRepository.save(newUser);

        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.jsonResponse(HttpStatus.BAD_REQUEST, "11", true, "Failed save User", null);
        }

        return BaseResponse.jsonResponse(HttpStatus.OK, "00", true, "Created User Sucessfully", null);
    }

    @GetMapping(value = "/get-all-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllUser() {

        log.info("Get All user..");

        List<User> user = userRepository.findAll();

        List<User> dataUserActive = user.stream()
                        .map(s -> s)
                        .filter(value -> value.getActive() == true)
                        .collect(Collectors.toList());

        return BaseResponse.jsonResponse(HttpStatus.OK, "00", true, "Get User Succesfully", dataUserActive);
    }
}
