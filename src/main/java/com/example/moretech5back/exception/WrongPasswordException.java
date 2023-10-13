package online.mdfactory.backend.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class WrongPasswordException extends LogInException {
    public WrongPasswordException(String message) {
        super(message);
    }
}