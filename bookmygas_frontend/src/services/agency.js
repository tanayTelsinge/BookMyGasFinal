import api from './config';

export const fetchAgencies = async (vendorId) => {
    try {
        const response = await api.get(
            `/agencies?vendorId=${vendorId}`
        ); // Update this URL to your agencies endpoint
        return response.data;
    } catch (error) {
        console.error("Error fetching agencies:", error);
    }
};