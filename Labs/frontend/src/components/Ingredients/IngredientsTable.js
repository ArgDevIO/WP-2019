import React from 'react';

function IngredientsTable(props) {
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
          <tbody>{props.children}</tbody>
        </table>
      </span>
    </div>
  );
}

export default IngredientsTable;
