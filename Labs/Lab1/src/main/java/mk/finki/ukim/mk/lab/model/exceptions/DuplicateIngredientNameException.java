package mk.finki.ukim.mk.lab.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicateIngredientNameException extends RuntimeException {
    public DuplicateIngredientNameException(String name) {
        super("Ingredient '" + name + "' already exists!");
    }
}
