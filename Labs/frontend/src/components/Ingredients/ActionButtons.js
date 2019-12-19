import React from 'react';

import { Link } from 'react-router-dom';

export default function ActionButtons({ ingredient, deleteIngredient }) {
  return (
    <>
      <Link
        to={`/${ingredient.name}/edit`}
        className="btn btn-sm btn-secondary mr-1">
        <span className="fa fa-edit" />
        <strong>Edit</strong>
      </Link>
      <button
        onClick={() => deleteIngredient(ingredient.name)}
        className="btn btn-sm btn-outline-secondary mr-1">
        <span className="fa fa-remove" />
        <strong>Remove</strong>
      </button>
      <Link
        to={`/ingredients/${ingredient.name}/details`}
        className="btn btn-sm btn-outline-dark mr-1">
        <strong>Details</strong>
      </Link>
    </>
  );
}
