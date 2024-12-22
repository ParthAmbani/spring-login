import React, { useState } from 'react';
import { login } from '../api/auth'; // Import the login function from the API

const Login = ({ setError, setIsLoading, setIsSignUp }) => {
    const [emailOrPhoneNumber, setEmailOrPhoneNumber] = useState('');
    const [password, setPassword] = useState('');

    const handleLogin = async () => {
        if (!emailOrPhoneNumber || !password) {
            setError("Email and password are required");
            return;
        }

        setIsLoading(true);
        setError(null);

        try {
            const response = await login({ emailOrPhoneNumber, password });
            alert(response.data); // On successful login, show the response data (or handle accordingly)
        } catch (error) {
            setError(error.response ? error.response.data : "An unexpected error occurred");
        } finally {
            setIsLoading(false);
        }
    };

    return (
        <div className="login-form">
            <h2>Login</h2>
            <input
                type="email"
                placeholder="Work email"
                value={emailOrPhoneNumber}
                onChange={(e) => setEmailOrPhoneNumber(e.target.value)}
            />
            <input
                type="password"
                placeholder="Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
            />
            <button onClick={handleLogin}>Log In</button>
            <hr />
            <p>Or continue with</p>
            <div className="sso-buttons">
                <button>Google</button>
                <button>Microsoft</button>
            </div>
            <p>
                Don't have an account?{' '}
                <button onClick={() => setIsSignUp(true)}>Sign Up</button>
            </p>
        </div>
    );
};

export default Login;
