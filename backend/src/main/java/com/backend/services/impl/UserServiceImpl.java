package com.backend.services.impl;

import com.backend.dto.response.UserResponse;
import com.backend.exceptions.ResourceNotFoundException;
import com.backend.models.UserEntity;
import com.backend.repositories.UserRepository;
import com.backend.services.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j(topic = "USER-SERVICE")
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    @Override
    public UserResponse findById(Long id) {
        log.info("find user by userId: {}", id);
         UserEntity user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found!!"));

         log.info("find user by id successfully");

         return  UserResponse.builder()
                 .id(user.getUserId())
                 .address(user.getAddress())
                 .userName(user.getUserName())
                 .status(user.getStatus())
                 .gender(user.getGender())
                 .birthDay(user.getBirthDay())
                 .imageAvatarUrl(user.getImageAvatarUrl())
                 .build();
    }

    @Override
    public UserResponse findByEmail(String email) {

        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
