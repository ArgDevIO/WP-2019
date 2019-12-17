import axios from '../../custom-axios/axios';
//import qs from 'qs';

const IngredientsService = {
	fetchIngredients: () => {
		return axios.get('/ingredients');
	}
};

export default IngredientsService;
