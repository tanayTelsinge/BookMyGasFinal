import axios from 'axios';

// Create an Axios instance
const api = axios.create({
  baseURL: 'http://localhost:8080', // Your API base URL
});

// Interceptor to add token to every request except for signin and signup
api.interceptors.request.use(
  (config) => {
    // Check if the request is for signin or signup
    if (!config.url.includes('/signin') && !config.url.includes('/signup')) {
      const token = sessionStorage.getItem('token'); // Retrieve token from sessionStorage
      if (token) {
        config.headers['Authorization'] = `Bearer ${token}`; // Add token to headers
      }
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export default api;
