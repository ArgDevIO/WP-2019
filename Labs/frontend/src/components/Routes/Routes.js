import React from 'react';
import { Switch, Route, Redirect } from 'react-router-dom';

// Components
import Ingredients from '../Ingredients/Ingredients';
import EditIngredient from '../Action Buttons/EditIngredient';

export default function Routes() {
  return (
    <Switch>
      <Route path={'/ingredients'} exact component={Ingredients} />
      <Route path={'/:ingredientId/edit'} exact component={EditIngredient} />
      <Redirect to={'/'} />
    </Switch>
  );
}
