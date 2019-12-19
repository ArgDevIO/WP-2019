import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';

import IngredientService from '../repository/axiosIngredientsRepository';

function IngredientDetails() {
  const { ingredientId } = useParams();

  const [name, setName] = useState('');
  const [amount, setAmount] = useState(0);
  const [spicy, setSpicy] = useState(false);
  const [veggie, setVeggie] = useState(false);

  const [pizzas, setPizzas] = useState([]);

  useEffect(() => {
    let mounted = true;

    IngredientService.fetchIngredientById(ingredientId).then(res => {
      if (mounted) {
        setName(res.data.name);
        setAmount(res.data.amount);
        setSpicy(res.data.spicy);
        setVeggie(res.data.veggie);
      }
    });

    IngredientService.fetchAllPizzaContaining(ingredientId).then(res => {
      setPizzas(res.data);
    });

    return () => (mounted = false);
  }, [ingredientId]);

  return (
    <div>
      <div>
        <h3>{name}</h3>
        <p>{amount}</p>
        <p>{spicy.toString()}</p>
        <p>{veggie.toString()}</p>
      </div>
      {pizzas.map(pizza => {
        return (
          <div>
            <h4>{pizza.name}</h4>
            <p>{pizza.description}</p>
            {pizza.ingredients.map(ingr => {
              return (
                <span>
                  <h3>{ingr.name}</h3>
                  <p>{ingr.amount}</p>
                  <p>{ingr.spicy.toString()}</p>
                  <p>{ingr.veggie.toString()}</p>
                </span>
              );
            })}
          </div>
        );
      })}
    </div>
  );
}

export default IngredientDetails;
