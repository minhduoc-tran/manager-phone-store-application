package com.backend.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T>{

    private int status;
    private String message;
    private T data;

    public static <T> Response<T> success(String msg,T data) {
        return new Response<>(200, msg, data);
    }

    public static <T> Response<T> error(int status, String message) {
        return new Response<>(status, message, null);
    }
}
