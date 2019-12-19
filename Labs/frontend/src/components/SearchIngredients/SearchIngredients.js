import React, { useState } from 'react';

export default function SearchIngredients(props) {
  const [searchText, setSearchText] = useState('');

  const handleChange = e => {
    setSearchText(e.target.value);

    props.filterIngredients(e.target.value);
  };

  return (
    <div className="row">
      <div className="card-body row no-gutters">
        <div className="col-lg-12">
          <input
            className="form-control form-control-lg form-control-borderless"
            type="text"
            onChange={handleChange}
            value={searchText}
            placeholder="Search ingredients..."
          />
        </div>
      </div>
    </div>
  );
}
