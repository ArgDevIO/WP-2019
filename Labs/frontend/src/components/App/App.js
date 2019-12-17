import React from 'react';
import { BrowserRouter as Router, Redirect, Route } from 'react-router-dom';

//Components
import Header from '../Header/Header';

// Stylesheet
import './App.css';

function App() {
	return (
		<div className="App">
			<Router>
				<Header />
			</Router>
		</div>
	);
}

export default App;
