import React from 'react';

//Components
import IngredientRow from './IngredientRow';

function IngredientsTable({ ingredients }) {
	return (
		<div className="col-lg-12 m-0 p-0">
			<span className="table-responsive">
				<table className="table tr-history table-striped small">
					<thead>
						<tr>
							<th scope="col">Name</th>
							<th scope="col">Amount</th>
							<th scope="col">Spicy</th>
							<th scope="col">Veggie</th>
							<th scope="col">Actions</th>
						</tr>
					</thead>
					<tbody>
						{ingredients.map(ingredient => {
							return (
								<IngredientRow
									key={ingredient.name}
									name={ingredient.name}
									amount={ingredient.amount}
									spicy={ingredient.spicy}
									veggie={ingredient.veggie}
								/>
							);
						})}
					</tbody>
				</table>
			</span>
		</div>
	);
}

export default IngredientsTable;
