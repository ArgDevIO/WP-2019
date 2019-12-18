package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.model.exceptions.DuplicateIngredientNameException;
import mk.finki.ukim.mk.lab.model.exceptions.InvalidAmountOfSpicyIngredientsException;
import mk.finki.ukim.mk.lab.model.exceptions.InvalidIngredientsIdException;
import mk.finki.ukim.mk.lab.repository.IngredientRepository;
import mk.finki.ukim.mk.lab.repository.PizzaRepository;
import mk.finki.ukim.mk.lab.service.IngredientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;
    private final PizzaRepository pizzaRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository, PizzaRepository pizzaRepository) {
        this.ingredientRepository = ingredientRepository;
        this.pizzaRepository = pizzaRepository;
    }


    @Override
    public Ingredient createIngredient(String name, boolean spicy, float amount, boolean veggie) {
        if (this.ingredientRepository.findById(name).isPresent())
            throw new DuplicateIngredientNameException(name);
        if (spicy) {
            int noOfSpicyIngredients = this.ingredientRepository.countAllBySpicyIsTrue();
            if (noOfSpicyIngredients > 2) throw new InvalidAmountOfSpicyIngredientsException();
        }
        return this.ingredientRepository.save(new Ingredient(name, spicy, amount, veggie));
    }

    @Override
    public Ingredient editIngredient(String ingredientId, String name, Boolean spicy, Float amount, Boolean veggie) {
        Ingredient ingredient = this.ingredientRepository.findById(ingredientId).orElseThrow(InvalidIngredientsIdException::new);

        if (name != null) ingredient.setName(name);
        if (spicy != null) ingredient.setSpicy(spicy);
        if (amount != null) ingredient.setAmount(amount);
        if (veggie != null) ingredient.setVeggie(veggie);

        return this.ingredientRepository.save(ingredient);
    }

    @Override
    public void deleteIngredient(String ingredientId) {

        Ingredient ingredient = this.ingredientRepository.findById(ingredientId).orElseThrow(InvalidIngredientsIdException::new);

        List<Pizza> pizzas = this.pizzaRepository.findPizzaByIngredientsContains(ingredient);

        pizzas.forEach(i -> i.getIngredients().remove(ingredient));

        this.ingredientRepository.deleteById(ingredientId);
    }

    @Override
    public Page<Ingredient> getIngredients(int page, int size) {
        return this.ingredientRepository.findAll(PageRequest.of(page, size, Sort.by("name").ascending()));
    }

    @Override
    public Ingredient getIngredient(String ingredientId) {
        return this.ingredientRepository.findById(ingredientId).orElseThrow(InvalidIngredientsIdException::new);
    }

    @Override
    public List<Ingredient> getBySpicy(boolean spicy) {
        return this.ingredientRepository.getBySpicy(spicy);
    }

}
