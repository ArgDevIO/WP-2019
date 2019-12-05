package mk.finki.ukim.mk.lab.web.rest;

import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.service.PizzaService;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/pizzas", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class PizzasApi {

    private final PizzaService pizzaService;

    public PizzasApi(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pizza createPizza(@RequestParam String name,
                             @RequestParam String description,
                             @RequestParam List<String> ingredients,
                             @RequestParam(defaultValue = "false") boolean veggie) {
        return this.pizzaService.createPizza(name, description, ingredients, veggie);
    }

    @PutMapping("/{id}")
    public Pizza editPizza(@PathVariable String id,
                           @RequestParam String name,
                           @RequestParam String description,
                           @RequestParam List<String> ingredients,
                           @RequestParam boolean veggie) {
        return this.pizzaService.editPizza(id, name, description, ingredients, veggie);
    }

    @DeleteMapping("/{id}")
    public void deletePizza(@PathVariable String id) {
        this.pizzaService.deletePizza(id);
    }

    @GetMapping
    public List<Pizza> getAllPizzas(@RequestParam(required = false, defaultValue = "0") int totalIngredients) {
        return this.pizzaService.getAllPizzas(totalIngredients);
    }

    @GetMapping("/{id}")
    public Pizza getPizza(@PathVariable String id) {
        return this.pizzaService.getPizza(id);
    }

}
