import React from 'react';

export default function ActionButtons() {
	return (
		<>
			<button className="btn btn-sm btn-secondary mr-1">
				<span className="fa fa-edit" />
				<strong>Edit</strong>
			</button>
			<button className="btn btn-sm btn-outline-secondary mr-1">
				<span className="fa fa-remove" />
				<strong>Remove</strong>
			</button>
			<button className="btn btn-sm btn-outline-dark mr-1">
				<strong>Details</strong>
			</button>
		</>
	);
}
