import React, { useState } from 'react';
import { Switch, Route, Redirect } from 'react-router-dom';

// Components
import Ingredients from '../Ingredients/Ingredients';
import AddEditIngredient from '../AddEditIngredient/AddEditIngredient';
import IngredientDetails from '../IngredientDetails/IngredientDetails';

export default function Routes(props) {
  const [updatedIngredient, setUpdatedIngredient] = useState({});

  const onSave = ingredient => {
    const { name, spicy, amount, veggie } = ingredient;
    setUpdatedIngredient({ name, spicy, amount, veggie });
  };

  return (
    <Switch>
      <Route
        path={'/ingredients'}
        exact
        render={() => <Ingredients updatedIngredient={updatedIngredient} />}
      />
      <Route
        path={'/:ingredientId/edit'}
        exact
        component={() => <AddEditIngredient edit={true} onSave={onSave} />}
      />
      <Route
        path={'/ingredients/new'}
        exact
        component={() => <AddEditIngredient add={true} onSave={onSave} />}
      />
      <Route
        path={'/ingredients/:ingredientId/details'}
        exact
        component={() => <IngredientDetails />}
      />
      >
      <Redirect to={'/'} />
    </Switch>
  );
}
