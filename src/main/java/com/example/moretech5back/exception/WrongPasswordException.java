package com.example.moretech5back.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class WrongPasswordException extends LogInException {
    public WrongPasswordException(String message) {
        super(message);
    }
}