import React from 'react';

import { Link } from 'react-router-dom';

export default function ActionButtons({ ingredient }) {
  return (
    <>
      <Link
        to={`/${ingredient.name}/edit`}
        className="btn btn-sm btn-secondary mr-1">
        <span className="fa fa-edit" />
        <strong>Edit</strong>
      </Link>
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
