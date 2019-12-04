package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Pizza;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PizzaRepo {

    private List<Pizza> pizzas = new ArrayList<Pizza>() {{
        add(new Pizza("Margherita", "(tomato sauce, mozzarella)", null, false));
        add(new Pizza("Carbonara", "(fresh cream, mozzarella, bacon)", null, false));
        add(new Pizza("Vegetariana", "(tomato sauce, mushrooms)", null, false));
        add(new Pizza("Calzone", "(Pizza dough, ricotta, pepperoni, pizza sauce, olive oil)", null, false));
        add(new Pizza("Cheddar", "(cheddar, tomato sauce)", null, false));
        add(new Pizza("Capricciosa", "(tomato sauce, cheese, ham)", null, false));
        add(new Pizza("Burger Classic", "(barbecue sauce, beef, mozzarella, onions)", null, false));
        add(new Pizza("Boston Barbecue", "(ham, chicken meat, onions)", null, false));
        add(new Pizza("Vegie Vulcano", "(tomato sauce, mozzarella, sausage)", null, false));
        add(new Pizza("Boston Barbecue", "(Taleggio, Mascarpone, Gorgonzola, Parmesan)", null, false));
    }};

    public List<Pizza> getAllPizzas() {
        return pizzas;
    }
}
