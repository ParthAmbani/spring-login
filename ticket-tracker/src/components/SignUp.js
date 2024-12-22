import React, { useState } from 'react';
import { signUp } from '../api/auth'; // Import the signUp function from API

const SignUp = ({ setError, setIsLoading, setIsSignUp }) => {
    const [email, setEmail] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');
    const [password, setPassword] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');

    const handleSignUp = async () => {
        if (!email || !password || !firstName || !lastName || !phoneNumber) {
            setError("All fields are required");
            return;
        }

        setIsLoading(true);
        setError(null);

        try {
            const response = await signUp({ email, phoneNumber, password, firstName, lastName });
            alert(response.data);
        } catch (error) {
            setError(error.response ? error.response.data : "An unexpected error occurred");
        } finally {
            setIsLoading(false);
        }
    };

    return (
        <div className="signup-form">
            <input
                type="email"
                placeholder="Email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
            />
            <input
                type="text"
                placeholder="Phone Number"
                value={phoneNumber}
                onChange={(e) => setPhoneNumber(e.target.value)}
            />
            <input
                type="password"
                placeholder="Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
            />
            <input
                type="text"
                placeholder="First Name"
                value={firstName}
                onChange={(e) => setFirstName(e.target.value)}
            />
            <input
                type="text"
                placeholder="Last Name"
                value={lastName}
                onChange={(e) => setLastName(e.target.value)}
            />
            <button onClick={handleSignUp}>Sign Up</button>
            <p>
                Already have an account?{' '}
                <button onClick={() => setIsSignUp(false)}>Log In</button>
            </p>
        </div>
    );
};

export default SignUp;
