package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.exceptions.InvalidIngredientsIdException;
import mk.finki.ukim.mk.lab.repository.IngredientRepository;
import mk.finki.ukim.mk.lab.service.IngredientService;
import org.springframework.stereotype.Service;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }


    @Override
    public Ingredient createIngredient(String name, boolean spicy, float amount, boolean veggie) {
        return this.ingredientRepository.save(new Ingredient(name, spicy, amount, veggie));
    }

    @Override
    public Ingredient editIngredient(String ingredientId, String name, boolean spicy, float amount, boolean veggie) {
        Ingredient ingredient = this.ingredientRepository.findById(ingredientId).orElseThrow(InvalidIngredientsIdException::new);

        ingredient.setName(name);
        ingredient.setSpicy(spicy);
        ingredient.setAmount(amount);
        ingredient.setVeggie(veggie);

        return this.ingredientRepository.save(ingredient);
    }
}
