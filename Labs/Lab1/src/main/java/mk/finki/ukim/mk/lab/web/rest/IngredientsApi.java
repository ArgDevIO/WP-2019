package mk.finki.ukim.mk.lab.web.rest;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.service.IngredientService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PatchMapping("/{id}")
    public Ingredient editIngredient(@PathVariable String id,
                                     @RequestParam String name,
                                     @RequestParam boolean spicy,
                                     @RequestParam float amount,
                                     @RequestParam boolean veggie) {
        return this.ingredientService.editIngredient(id, name, spicy, amount, veggie);
    }

    @DeleteMapping("/{id}")
    public void deleteSlot(@PathVariable String id) {
        this.ingredientService.deleteIngredient(id);
    }

    @GetMapping
    public Page<Ingredient> getIngredients(@RequestHeader(name = "page", defaultValue = "0", required = false) int page,
                                           @RequestHeader(name = "page-size", defaultValue = "10", required = false) int size) {
        return this.ingredientService.getIngredients(page, size > 10 ? 10 : size);
    }

    @GetMapping("/{id}")
    public Ingredient getIngredient(@PathVariable String id) {
        return this.ingredientService.getIngredient(id);
    }

    @GetMapping(params = "spicy")
    public List<Ingredient> getBySpicy(@RequestParam boolean spicy) {
        return this.ingredientService.getBySpicy(spicy);
    }

}
