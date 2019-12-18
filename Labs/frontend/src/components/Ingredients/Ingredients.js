import React, { useState, useEffect } from 'react';

//Components
import IngredientsTable from './IngredientsTable';

//Services
import ingredientsService from '../repository/axiosIngredientsRepository';

function Ingredients() {
	const [ingredients, setIngredients] = useState([]);

	useEffect(() => {
		let mounted = true;
		ingredientsService.fetchIngredients().then(resp => {
			if (mounted) setIngredients(resp.data.content);
		});
		return () => (mounted = false);
	}, []);

	return (
		<span className="row">
			<h4 className="text-upper text-left col-lg-12">Ingredients</h4>
			<IngredientsTable ingredients={ingredients} />
			<button className="btn btn-outline-secondary">
				<strong>Add new ingredient</strong>
			</button>
		</span>
	);
}

export default Ingredients;
