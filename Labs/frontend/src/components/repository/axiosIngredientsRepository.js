import axios from '../../custom-axios/axios';
import qs from 'qs';

const IngredientsService = {
  fetchIngredients: () => {
    return axios.get('/ingredients');
  },
  fetchIngredientById: id => {
    return axios.get(`/ingredients/${id}`);
  },
  saveIngredientById: (id, name, amount, veggie, spicy) => {
    return axios.patch(
      `/ingredients/${id}`,
      qs.stringify({
        amount,
        veggie,
        spicy
      }),
      {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      }
    );
  },
  deleteIngredientById: id => {
    axios.delete(`/ingredients/${id}`);
  },
  addIngredient: (name, spicy, veggie, amount) => {
    return axios.post(
      '/ingredients',
      qs.stringify({
        name,
        amount,
        veggie,
        spicy
      }),
      {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      }
    );
  },
  fetchAllPizzaContaining: id => {
    return axios.get(`/ingredients/${id}/pizzas`);
  }
};

export default IngredientsService;
