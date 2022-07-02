package com.mandiri.tech.controller;

import com.mandiri.tech.dto.DetailUser;
import com.mandiri.tech.dto.UserContactDTO;
import com.mandiri.tech.model.User;
import com.mandiri.tech.model.UserContact;
import com.mandiri.tech.repository.UserContactRepository;
import com.mandiri.tech.repository.UserRepository;
import com.mandiri.tech.util.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api")
public class UserContactController {

    @Autowired
    private UserContactRepository userContactRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/create-user-contact", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createUserContact(@RequestBody UserContactDTO userContact) {

        log.info("Create user contact.." + "");

        try {

            User usrObj = userRepository.findByIdUsr(userContact.getUserId());

            UserContact userCt = new UserContact();

            userCt.setAddress(userContact.getAddress());
            userCt.setUser(usrObj);

            userContactRepository.save(userCt);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return BaseResponse.jsonResponse(HttpStatus.OK, "00", true, "Created User Contact Sucessfully", null);
    }
    @GetMapping(value = "/get-all-usercontact", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllUserContact() {

        log.info("Get All user-contact..");

        List<DetailUser> user = userContactRepository.findAllUserContact();

        return BaseResponse.jsonResponse(HttpStatus.OK, "00", true, "Get User Contact Succesfully", user);
    }

}
