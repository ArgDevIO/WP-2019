package mk.finki.ukim.mk.lab.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidPizzaIdException extends RuntimeException {
    public InvalidPizzaIdException() {
        super("Pizza doesn't exist!");
    }
}
