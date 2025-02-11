package mk.finki.ukim.mk.lab.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class VeggieIngredientsConfilctException extends RuntimeException {
    public VeggieIngredientsConfilctException() {
        super("All Ingredients must be Veggie on a Veggie Pizza!");
    }
}
