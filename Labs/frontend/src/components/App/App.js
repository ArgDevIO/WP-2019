import React from 'react';
import { BrowserRouter as Router } from 'react-router-dom';

//Components
import Header from '../Header/Header';
import Routes from '../Routes/Routes';

// Stylesheet
import './App.css';

function App() {
  return (
    <div className="App">
      <Router>
        <Header />
        <main role="main" className="mt-3">
          <div className="container">
            <Routes />
          </div>
        </main>
      </Router>
    </div>
  );
}

export default App;
