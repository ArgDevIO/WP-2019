package mk.finki.ukim.mk.lab.web.rest;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.service.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/ingredients", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class IngredientsApi {

    private final IngredientService ingredientService;

    public IngredientsApi(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ingredient testing(@RequestParam String name,
                              @RequestParam boolean spicy,
                              @RequestParam float amount,
                              @RequestParam boolean veggie) {
        return this.ingredientService.createIngredient(name, spicy, amount, veggie);
    }

    @PatchMapping("{id}")
    public Ingredient editIngredient(@PathVariable String id,
                                     @RequestParam String name,
                                     @RequestParam boolean spicy,
                                     @RequestParam float amount,
                                     @RequestParam boolean veggie) {
        return ingredientService.editIngredient(id, name, spicy, amount, veggie);
    }
}
