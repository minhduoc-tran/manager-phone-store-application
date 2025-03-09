package com.backend.services;

import com.backend.dto.response.UserResponse;

public interface IUserService {

    UserResponse findById(Long id);

    UserResponse findByEmail(String email);

    void delete(Long id);
}
