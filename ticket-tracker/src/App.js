import React, { useState } from "react";
import "./App.css";
import { signUp } from "./api";

function App() {
    const [email, setEmail] = useState("");
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState(null);

    const handleSignUp = async () => {
        if (!email) {
            setError("Email is required");
            return;
        }

        setIsLoading(true);
        setError(null); // Clear any previous error

        try {
            const response = await signUp({ email });
            alert(response.data);
        } catch (error) {
            setError(error.response ? error.response.data : "An unexpected error occurred");
        } finally {
            setIsLoading(false);
        }
    };

    return (
        <div className="App">
            <header className="App-header">
                <h1>Bug Tracking Done Right</h1>
                <p>Report, track, and prioritize bugs</p>

                <input
                    type="email"
                    placeholder="Work email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                />
                <button onClick={handleSignUp} disabled={isLoading}>
                    {isLoading ? "Signing Up..." : "Sign Up"}
                </button>

                {error && <div className="error">{error}</div>}

                <hr />
                <p>Or continue with</p>
                <div className="sso-buttons">
                    <button>Google</button>
                    <button>Microsoft</button>
                </div>
            </header>
        </div>
    );
}

export default App;
