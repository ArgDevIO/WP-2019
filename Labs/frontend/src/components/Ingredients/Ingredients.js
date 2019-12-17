import React, { useState, useEffect } from 'react';
import ingredientsService from '../repository/axiosIngredientsRepository';

function Ingredients() {
	const [ingredients, setIngredients] = useState([]);

	useEffect(() => {
		ingredientsService.fetchIngredients().then(resp => {
			setIngredients(resp.data.content);
		});
	}, []);

	return (
		<span className="row">
			<h4 className="text-upper text-left">Ingredients</h4>
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
						{ingredients.map(item => {
							return (
								<tr key={item.name}>
									<td scope="col">{item.name}</td>
									<td scope="col">{item.amount}</td>
									<td scope="col">{item.spicy.toString()}</td>
									<td scope="col">{item.veggie.toString()}</td>
									<td scope="col">
										<button className="btn btn-sm btn-secondary">
											<span className="fa fa-edit" />
											<span>
												<strong>Edit</strong>
											</span>
										</button>
										<button className="btn btn-sm btn-outline-secondary ">
											<span className="fa fa-remove" />
											<span>
												<strong>Remove</strong>
											</span>
										</button>
										<button className="btn btn-sm btn-outline-dark">
											<span>
												<strong>Details</strong>
											</span>
										</button>
									</td>
								</tr>
							);
						})}
					</tbody>
				</table>
			</span>
			<button className="btn btn-outline-secondary">
				<span>
					<strong>Add new ingredient</strong>
				</span>
			</button>
		</span>
	);
}

export default Ingredients;
