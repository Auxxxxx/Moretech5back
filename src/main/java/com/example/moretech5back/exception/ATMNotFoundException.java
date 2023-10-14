package com.example.moretech5back.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ATMNotFoundException extends Exception {
    public ATMNotFoundException(String message) {
        super(message);
    }
}