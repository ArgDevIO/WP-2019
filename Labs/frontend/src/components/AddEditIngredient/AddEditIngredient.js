import React, { useState, useEffect } from 'react';
import { useParams, useHistory } from 'react-router-dom';
import IngredientService from '../repository/axiosIngredientsRepository';

function AddEditIngredient(props) {
  const [name, setName] = useState('');
  const [amount, setAmount] = useState('');
  const [spicy, setSpicy] = useState(false);
  const [veggie, setVeggie] = useState(false);

  const [errorMsg, setErrorMsg] = useState('');

  const { ingredientId } = useParams();
  const history = useHistory();

  useEffect(() => {
    let mounted = true;

    if (props.edit) {
      IngredientService.fetchIngredientById(ingredientId).then(res => {
        if (mounted) {
          setName(res.data.name);
          setAmount(res.data.amount);
          setSpicy(res.data.spicy);
          setVeggie(res.data.veggie);
        }
      });
    }
    return () => (mounted = false);
  }, [ingredientId, props.edit]);

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
    if (props.edit) {
      IngredientService.saveIngredientById(
        ingredientId,
        name,
        amount,
        veggie,
        spicy
      ).then(resp => {
        const { newName, newAmount, newVeggie, newSpicy } = resp.data;
        props.onSave({ ingredientId, newName, newAmount, newVeggie, newSpicy });
        history.push('/ingredients');
      });
    } else {
      IngredientService.addIngredient(name, spicy, veggie, amount)
        .then(res => {
          //const { newName, newAmount, newVeggie, newSpicy } = res.data;
          props.onSave({ ingredientId, name, amount, veggie, spicy });
          history.push('/ingredients');
        })
        .catch(error => {
          setErrorMsg(error.response.data.message);
        });
    }
  };

  const resetForm = e => {
    e.preventDefault();
    setName('');
    setAmount(0);
    setSpicy(false);
    setVeggie(false);
    setErrorMsg('');
  };

  const errorView = (
    <div className="alert alert-warning" role="alert">
      {errorMsg}
    </div>
  );

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
              disabled={
                name === '' ||
                name.length > 50 ||
                amount === '' ||
                amount.length > 50
              }
              type="submit"
              className="btn btn-primary text-upper">
              Save
            </button>
          </div>
          <div className="offset-sm-1 col-sm-3  text-center">
            <button onClick={resetForm} className="btn btn-warning text-upper">
              Reset
            </button>
          </div>
          <div className="offset-sm-1 col-sm-3  text-center">
            <button
              onClick={e => history.push('/ingredients')}
              className="btn btn-danger text-upper">
              Cancel
            </button>
          </div>
        </div>
        {errorMsg ? errorView : null}
      </form>
    </div>
  );
}

export default AddEditIngredient;
