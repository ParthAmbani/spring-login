import React, { useState } from 'react';
import './App.css';
import Login from './components/Login';
import SignUp from './components/SignUp';
import SSOButtons from './components/SSOButtons';

const App = () => {
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState(null);
    const [isSignUp, setIsSignUp] = useState(false);

    return (
        <div className="App">
            <header className="App-header">
                <h1>Bug Tracking Done Right</h1>
                <p>Report, track, and prioritize bugs</p>

                {isSignUp ? (
                    <SignUp
                        setError={setError}
                        setIsLoading={setIsLoading}
                        setIsSignUp={setIsSignUp}
                    />
                ) : (
                    <Login
                        setError={setError}
                        setIsLoading={setIsLoading}
                        setIsSignUp={setIsSignUp}
                    />
                )}

                {error && <div className="error">{error}</div>}
            </header>
        </div>
    );
};

export default App;
