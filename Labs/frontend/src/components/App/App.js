import React from 'react';
import { BrowserRouter as Router, Redirect, Route } from 'react-router-dom';

//Components
import Header from '../Header/Header';
import Ingredients from '../Ingredients/Ingredients';

// Stylesheet
import './App.css';

function App() {
	return (
		<div className="App">
			<Router>
				<Header />
				<main role="main" className="mt-3">
					<div className="container">
						<Route path={'/ingredients'} exact render={() => <Ingredients />} />
						<Redirect to={'/'} />
					</div>
				</main>
			</Router>
		</div>
	);
}

export default App;
