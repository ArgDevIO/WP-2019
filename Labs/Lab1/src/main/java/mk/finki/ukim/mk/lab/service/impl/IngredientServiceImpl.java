package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.exceptions.DuplicateIngredientNameException;
import mk.finki.ukim.mk.lab.model.exceptions.InvalidAmountOfSpicyIngredientsException;
import mk.finki.ukim.mk.lab.model.exceptions.InvalidIngredientsIdException;
import mk.finki.ukim.mk.lab.repository.IngredientRepository;
import mk.finki.ukim.mk.lab.service.IngredientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
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
    public Ingredient editIngredient(String ingredientId, String name, boolean spicy, float amount, boolean veggie) {
        Ingredient ingredient = this.ingredientRepository.findById(ingredientId).orElseThrow(InvalidIngredientsIdException::new);

        ingredient.setName(name);
        ingredient.setSpicy(spicy);
        ingredient.setAmount(amount);
        ingredient.setVeggie(veggie);

        return this.ingredientRepository.save(ingredient);
    }

    @Override
    public void deleteIngredient(String ingredientId) {
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
