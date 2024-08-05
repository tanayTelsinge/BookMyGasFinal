import api from './config';

export const createOrder = async (orderRequest) => {
    try {
        const response = await api.post(`/orders`, orderRequest);
        return response;
    } catch (error) {
        console.error("Error fetching agencies:", error);
    }
};

export const getOrdersForUser = async (userId) => {
    try {
        const response = await api.get(`/orders?userId=` + userId);
        return response;
    } catch (error) {
        console.error("Error fetching agencies:", error);
    }
};