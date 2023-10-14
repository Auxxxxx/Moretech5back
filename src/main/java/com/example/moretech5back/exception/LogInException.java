package com.example.moretech5back.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LogInException extends Exception {
    public LogInException(String message) {
        super(message);
    }
}