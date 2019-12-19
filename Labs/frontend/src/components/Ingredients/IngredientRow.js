import React from 'react';

//Components
import ActionButtons from './ActionButtons';

export default function Ingredient({ ingredient }) {
  return (
    <tr>
      <td>{ingredient.name}</td>
      <td>{ingredient.amount}</td>
      <td>{ingredient.spicy.toString()}</td>
      <td>{ingredient.veggie.toString()}</td>
      <td>
        <ActionButtons ingredient={ingredient} />
      </td>
    </tr>
  );
}
