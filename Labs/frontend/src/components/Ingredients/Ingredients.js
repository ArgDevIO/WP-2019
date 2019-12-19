import React, { useState, useEffect } from 'react';

//Components
import IngredientsTable from './IngredientsTable';
import IngredientRow from './IngredientRow';

//Services
import ingredientsService from '../repository/axiosIngredientsRepository';
import { Link } from 'react-router-dom';
import SearchIngredients from '../SearchIngredients/SearchIngredients';

function Ingredients(props) {
  const [ingredients, setIngredients] = useState([]);
  const [tempIngredients, setTempIngredients] = useState([]);

  useEffect(() => {
    let mounted = true;
    ingredientsService.fetchIngredients().then(resp => {
      if (mounted) {
        if (Object.entries(props.updatedIngredient).length === 0) {
          setTempIngredients(resp.data.content);
          setIngredients(resp.data.content);
        } else {
          updateIngredients(props.updatedIngredient, resp.data.content);
        }
      }
    });
    return () => (mounted = false);
  }, [props.updatedIngredient]);

  const updateIngredients = (ingredient, fetchedIngredients) => {
    if (!fetchedIngredients) return;

    const newIngr = fetchedIngredients.map(item => {
      if (item.name === ingredient.name) {
        item = ingredient;
      }
      return item;
    });
    setIngredients(newIngr);
    setTempIngredients(newIngr);
  };

  const deleteIngredient = name => {
    const newIngredients = ingredients.filter(item => item.name !== name);

    ingredientsService.deleteIngredientById(name);

    console.log(newIngredients);
    setIngredients(newIngredients);
  };

  const filterIngredients = text => {
    const filteredIngredients = tempIngredients.filter(ingr =>
      ingr.name.toLowerCase().startsWith(text.toLowerCase())
    );
    setIngredients(filteredIngredients);
  };

  return (
    <span className="row">
      <h4 className="text-upper text-left col-lg-12">Ingredients</h4>
      <SearchIngredients filterIngredients={filterIngredients} />
      <IngredientsTable ingredients={ingredients}>
        {ingredients.map(ingredient => {
          return (
            <IngredientRow
              deleteIngredient={deleteIngredient}
              key={ingredient.name}
              ingredient={ingredient}
            />
          );
        })}
      </IngredientsTable>
      <Link to={'/ingredients/new'} className="btn btn-outline-secondary">
        <strong>Add new ingredient</strong>
      </Link>
    </span>
  );
}

export default Ingredients;
