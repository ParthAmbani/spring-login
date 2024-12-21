import axios from "axios";

// Function to generate the Authorization header
const getAuthHeaders = () => {
    const username = process.env.REACT_APP_API_USER;
    const password = process.env.REACT_APP_API_PASSWORD;

    // Combine the username and password
    const credentials = `${username}:${password}`;
    const base64Credentials = btoa(credentials); // Encoding the "admin:admin123" credentials to base64
    return {
        headers: {
            Authorization: `Basic ${base64Credentials}`, // Adding the Authorization header
        },
    };
};

// Use environment variables for the base URL, for flexibility between environments
const API = axios.create({
    baseURL: process.env.REACT_APP_API_URL,
});

// Optional: Add request/response interceptors for global error handling or logging
API.interceptors.response.use(
    (response) => response,
    (error) => {
        // You can customize this to show global error alerts or logging
        if (error.response) {
            // Server responded with an error
            console.error("API Error:", error.response.data);
        } else if (error.request) {
            // Request was made but no response was received
            console.error("API Error: No response received");
        } else {
            // Something happened in setting up the request
            console.error("API Error:", error.message);
        }
        return Promise.reject(error);
    }
);

// Define the signUp function with the Authorization header
export const signUp = (data) => {
    return API.post("/signup", data, getAuthHeaders()); // Add auth header to the POST request
};
