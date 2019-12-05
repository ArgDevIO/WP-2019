package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.model.exceptions.InvalidIngredientsIdException;
import mk.finki.ukim.mk.lab.model.exceptions.InvalidPizzaIdException;
import mk.finki.ukim.mk.lab.model.exceptions.VeggieIngredientsConfilctException;
import mk.finki.ukim.mk.lab.repository.IngredientRepository;
import mk.finki.ukim.mk.lab.repository.PizzaRepo;
import mk.finki.ukim.mk.lab.repository.PizzaRepository;
import mk.finki.ukim.mk.lab.service.PizzaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PizzaServiceImpl implements PizzaService {

    private final PizzaRepo pizzaRepo;
    private final PizzaRepository pizzaRepository;
    private final IngredientRepository ingredientRepository;

    public PizzaServiceImpl(PizzaRepo pizzaRepo, PizzaRepository pizzaRepository, IngredientRepository ingredientRepository) {
        this.pizzaRepo = pizzaRepo;
        this.pizzaRepository = pizzaRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<Pizza> listPizzas() {
        return this.pizzaRepo.getAllPizzas();
    }

    @Override
    public Pizza createPizza(String name, String description, List<String> ingredients, boolean veggie) {
        List<Ingredient> ingredientsList = new ArrayList<>();
        ingredients.forEach(s ->
                ingredientsList.add(this.ingredientRepository
                        .findById(s)
                        .orElseThrow(InvalidIngredientsIdException::new)));

        if (veggie) {
            ingredientsList.forEach(ingredient -> {
                if (!ingredient.isVeggie())
                    throw new VeggieIngredientsConfilctException();
            });
        }

        return this.pizzaRepository.save(new Pizza(name, description, ingredientsList, veggie));
    }

    @Override
    public Pizza editPizza(String id, String name, String description, List<String> ingredients, boolean veggie) {
        Pizza pizza = this.pizzaRepository.findById(id).orElseThrow(InvalidPizzaIdException::new);

        List<Ingredient> ingredientList = new ArrayList<>();
        ingredients.forEach(s ->
                ingredientList.add(this.ingredientRepository
                        .findById(s)
                        .orElseThrow(InvalidIngredientsIdException::new)));

        if (veggie) {
            ingredientList.forEach(ingredient -> {
                if (!ingredient.isVeggie())
                    throw new VeggieIngredientsConfilctException();
            });
        }

        pizza.setName(name);
        pizza.setDescription(description);
        pizza.setIngredients(ingredientList);
        pizza.setVeggie(veggie);

        return this.pizzaRepository.save(pizza);
    }

    @Override
    public void deletePizza(String id) {
        this.pizzaRepository.deleteById(id);
    }

    @Override
    public List<Pizza> getAllPizzas(int totalIngredients) {
        if (totalIngredients <= 0) return this.pizzaRepository.findAll();

        return this.pizzaRepository.findByIngredientsCount(totalIngredients);
    }

    @Override
    public Pizza getPizza(String id) {
        return this.pizzaRepository.findById(id).orElseThrow(InvalidPizzaIdException::new);
    }

    @Override
    public List<Pizza> getAllPizzasWithIngredient(String id) {
        Ingredient ingredient = this.ingredientRepository.findById(id).orElseThrow(InvalidIngredientsIdException::new);
        return this.pizzaRepository.findPizzaByIngredientsContains(ingredient);
    }

    @Override
    public List<Ingredient> getCommonIngredients(String pizza1ID, String pizza2ID) {
        List<Ingredient> pizza1Ingredients = this.pizzaRepository.findById(pizza1ID)
                .orElseThrow(InvalidPizzaIdException::new)
                .getIngredients();

        List<Ingredient> pizza2Ingredients = this.pizzaRepository.findById(pizza2ID)
                .orElseThrow(InvalidPizzaIdException::new)
                .getIngredients();

        return pizza2Ingredients
                .stream()
                .filter(pizza1Ingredients::contains)
                .collect(Collectors.toList());
    }
}
