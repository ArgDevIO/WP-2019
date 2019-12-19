import React, { useState, useEffect } from 'react';
import { useParams, useHistory } from 'react-router-dom';
import IngredientService from '../repository/axiosIngredientsRepository';

function EditIngredient(props) {
  const [name, setName] = useState(null);
  const [amount, setAmount] = useState(null);
  const [spicy, setSpicy] = useState(null);
  const [veggie, setVeggie] = useState(null);

  const { ingredientId } = useParams();
  const history = useHistory();

  useEffect(() => {
    IngredientService.fetchIngredientById(ingredientId).then(res => {
      setName(res.data.name);
      setAmount(res.data.amount);
      setSpicy(res.data.spicy);
      setVeggie(res.data.veggie);
    });
  }, [ingredientId]);

  const handleChange = e => {
    const { name, value } = e.target;

    switch (name) {
      case 'name': {
        setName(value);
        break;
      }
      case 'amount': {
        setAmount(value);
        break;
      }
      case 'spicy': {
        setSpicy(!spicy);
        break;
      }
      case 'veggie': {
        setVeggie(!veggie);
        break;
      }
      default:
        break;
    }
  };

  const saveIngredient = e => {
    e.preventDefault();
    IngredientService.saveIngredientById(
      ingredientId,
      name,
      amount,
      veggie,
      spicy
    ).then(res => console.log(res));

    history.push('/ingredients');
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
              name="name"
              value={name}
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
              value={amount}
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
              name="veggie"
              checked={veggie}
              onChange={handleChange}
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
              name="spicy"
              checked={spicy}
              onChange={handleChange}
              className="form-control"
              id="spicy"
            />
          </div>
        </div>

        <div className="form-group row">
          <div className="offset-sm-1 col-sm-3  text-center">
            <button
              onClick={saveIngredient}
              type="submit"
              className="btn btn-primary text-upper">
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
