import api from './config';

export const processPayment = async (paymentRequest) => {
    try {
        const response = await api.post(`/payments`, paymentRequest);
        return response;
    } catch (error) {
        console.error("Error fetching payments:", error);
    }
};

export const getPaymentByOrder = async (orderId) => {
    try {
        const response = await api.get(`/payments/order/` + orderId);
        return response;
    } catch (error) {
        console.error("Error fetching payments:", error);
    }
};