import React from 'react';

//Components
import ActionButtons from './ActionButtons';

export default function Ingredient({ name, amount, spicy, veggie }) {
	return (
		<tr>
			<td>{name}</td>
			<td>{amount}</td>
			<td>{spicy.toString()}</td>
			<td>{veggie.toString()}</td>
			<td>
				<ActionButtons />
			</td>
		</tr>
	);
}
