package mk.finki.ukim.mk.lab.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidIngredientsIdException extends RuntimeException {
    public InvalidIngredientsIdException() {
        super("Ingredient doesn't exist!");
    }
}
