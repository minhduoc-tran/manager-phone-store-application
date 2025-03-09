package com.backend.controllers;

import com.backend.dto.request.UserRequest;
import com.backend.dto.response.Response;
import com.backend.dto.response.UserResponse;
import com.backend.exceptions.InvalidDataException;
import com.backend.exceptions.ResourceNotFoundException;
import com.backend.services.IUserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j(topic = "USER-CONTROLLER")
@RequiredArgsConstructor
@Validated
public class UserController {
    private  final IUserService userService;

    @GetMapping("/{userId}")
    public Response<UserResponse> getUserDetail(@Valid @PathVariable @Min(value = 1, message = "userId must be equals or greater than 1") Long userId){
        try {
            log.info("Get user detail by ID: {}", userId);

            UserResponse user = userService.findById(userId);

            return Response.success("Get user successfully", user);

        } catch (ResourceNotFoundException ex){
            log.warn(ex.getMessage());
            return Response.error(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        }
    }

//    @PostMapping("/add")
//    public Response<UserResponse> createUser(@Valid @RequestBody UserRequest user){
//        try{
//
//        }catch (InvalidDataException ex){
//
//
//        }
//
//    }

}

