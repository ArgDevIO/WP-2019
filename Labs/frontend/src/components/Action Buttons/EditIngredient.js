import React, { useState, useEffect } from 'react';
import { useParams, useHistory } from 'react-router-dom';
import axios from '../../custom-axios/axios';

function EditIngredient(props) {
  const [ingredient, setIngredient] = useState({});
  const { ingredientId } = useParams();

  useEffect(() => {
    axios.get(`/ingredients/${ingredientId}`).then(data => {
      setIngredient(data.data);
    });
  }, []);

  const handleChange = e => {
    const paramName = e.target.name;
    const paramValue = e.target.value;
    setIngredient({ paramName: paramValue });
  };

  return (
    <div className="row">
      <form className="card">
        <h4 className="text-upper text-left">Add/Edit Ingredient</h4>
        <div className="form-group row">
          <label
            htmlFor="ingredient"
            className="col-sm-4 offset-sm-1 text-left">
            Ingredient name
          </label>
          <div className="col-sm-6">
            <input
              type="text"
              className="form-control"
              id="ingredient"
              placeholder="Ingredient name"
              name="amount"
              value={ingredient.name}
              onChange={handleChange}
            />
          </div>
        </div>
        <div className="form-group row">
          <label htmlFor="amount" className="col-sm-4 offset-sm-1 text-left">
            Amount
          </label>
          <div className="col-sm-6">
            <input
              type="text"
              className="form-control"
              id="amount"
              placeholder="Amount"
              name="amount"
              value={ingredient.amount}
              onChange={handleChange}
            />
          </div>
        </div>
        <div className="form-group row">
          <label htmlFor="veggie" className="col-sm-4 offset-sm-1 text-left">
            Veggie
          </label>
          <div className="col-sm-6 col-xl-4">
            <input
              type="checkbox"
              checked={ingredient.veggie}
              className="form-control"
              id="veggie"
            />
          </div>
        </div>

        <div className="form-group row">
          <label htmlFor="spicy" className="col-sm-4 offset-sm-1 text-left">
            Spicy
          </label>
          <div className="col-sm-6 col-xl-4">
            <input
              type="checkbox"
              checked={ingredient.spicy}
              className="form-control"
              id="spicy"
            />
          </div>
        </div>

        <div className="form-group row">
          <div className="offset-sm-1 col-sm-3  text-center">
            <button type="submit" className="btn btn-primary text-upper">
              Save
            </button>
          </div>
          <div className="offset-sm-1 col-sm-3  text-center">
            <button className="btn btn-warning text-upper">Reset</button>
          </div>
          <div className="offset-sm-1 col-sm-3  text-center">
            <button className="btn btn-danger text-upper">Cancel</button>
          </div>
        </div>
      </form>
    </div>
  );
}

export default EditIngredient;
