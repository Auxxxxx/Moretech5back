package online.mdfactory.backend.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LogInException extends Exception {
    public LogInException(String message) {
        super(message);
    }
}