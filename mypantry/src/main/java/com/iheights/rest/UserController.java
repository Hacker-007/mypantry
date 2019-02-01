package com.iheights.rest;

import com.iheights.Response;
import com.iheights.model.UserEntity;
import com.iheights.service.UserService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.PasswordAuthentication;
import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping(UserController.BASE_ADDRESS)
public class UserController {
    final static String BASE_ADDRESS = "/user";

    @Autowired
    UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Response addUser(@RequestBody UserEntity userEntity) {
        Response response = new Response();
        String pattern = "(\\d{10})";
        if (Pattern.matches(pattern, userEntity.getPhoneNumber())) {
            userService.addUser(userEntity.getFirstName(), userEntity.getLastName(), userEntity.getEmail(), userEntity.getPhoneNumber());
            response.setStatus("Done");
        } else {
            response.setStatus("Error");
        }

        return response;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response getUsersById(@PathVariable("id") int id) {
        Response response = new Response();
        if (!userService.exists(UserEntity.class, id)) {
            response.setStatus("Error");
        } else {
            response.setStatus("Done");
            response.setData(userService.getUserById(id));
        }

        return response;
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteUserById(@PathVariable("id") int id) {
        Response response = new Response();
        if (!userService.exists(UserEntity.class, id)) {
            response.setStatus("Error");
        } else {
            response.setStatus("Done");
            userService.deleteUserById(id);
        }

        return response;
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response changeUserById(@PathVariable("id") int id, @RequestBody UserEntity userEntity) {
        Response response = new Response();
        String pattern = "(\\d{5})";
        if (Pattern.matches(pattern, userEntity.getFirstName())) {
            response.setStatus("Done");
            userService.changeUserById(id, userEntity.getFirstName(), userEntity.getLastName(), userEntity.getEmail(), userEntity.getPhoneNumber());
        } else {
            response.setStatus("Error");
        }

        return response;
    }


}