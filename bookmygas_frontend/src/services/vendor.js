import api from './config'

export const fetchVendors = async () => {
    try {
        const response = await api.get(`/vendors`); // Update this URL to your vendor endpoint
        return response.data;
    } catch (error) {
        console.error("Error fetching vendors:", error);
    }
};