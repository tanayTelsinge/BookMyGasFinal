import api from './config';

export const createConnection = async (connectionRequest) => {
    try {
        const response = await api.post(`/connections`, connectionRequest);
        return response;
    } catch (error) {
        console.error("Error fetching agencies:", error);
    }
};

export const fetchConnections = async (userId) => {
    try {
        const response = await api.get('/connections?userId=' + userId);
        return response.data;
    } catch (error) {
        console.error("Error fetching connections:", error);
    }
};

export const deleteConnection = async (requestId) => {
    try {
        const response = await api.delete(`/connections/${requestId}`);
        console.log(response);
        return response.data;
    } catch (error) {
        console.error("Error fetching connections:", error);
    }
};