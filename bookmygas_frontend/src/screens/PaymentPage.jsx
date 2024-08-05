import React, { useState } from "react";
import { useLocation } from "react-router-dom";
import axios from "axios";
import { toast } from "react-toastify";
import Navbar from "../components/navbar";
import PaymentMethodSelection from "../components/PaymentMethodSelection";
import PaymentSuccessCard from "../components/PaymentSuccessCard";
import { processPayment } from "../services/payment"; // This service should handle the payment gateway interaction

const Payment = () => {
  const location = useLocation();
  const { order } = location.state;

  const [paymentMethod, setPaymentMethod] = useState("");
  const [paymentSuccess, setPaymentSuccess] = useState(false);
  const [transactionId, setTransactionId] = useState("");
  const [paymentDate, setPaymentDate] = useState("");
  const [paymentDetails, setPaymentDetails] = useState({
    cardNumber: "",
    expiryDate: "",
    cvv: ""
  });

  const handlePayment = async (event) => {
    event.preventDefault();

    if (!paymentMethod) {
      toast.error("Please select a payment method.");
      return;
    }

    if (paymentMethod === "creditCard" && !validateCardDetails(paymentDetails)) {
      toast.error("Please provide valid payment details.");
      return;
    }

    const currentDate = new Date().toISOString();
    const transactionId = `TXN${Date.now()}`; // Generate a simple transaction ID

    const paymentRequest = {
      order: order,
      amount: order.totalPrice,
      paymentDate: currentDate,
      paymentMethod,
      transactionId,
      status: "PAID", // Initial status
      ...paymentDetails
    };

    try {
      const response = await processPayment(paymentRequest);
      if (response.status === 201) {
        const data = response.data;
        toast.success("Payment successful");
        setTransactionId(data.transactionId);
        setPaymentDate(data.paymentDate);
        setPaymentSuccess(true);
      } else {
        toast.error("Payment failed");
      }
    } catch (error) {
      toast.error("An error occurred while processing payment");
    }
  };

  const validateCardDetails = (details) => {
    const { cardNumber, expiryDate, cvv } = details;
    return (
      cardNumber.length === 16 &&
      expiryDate.length === 5 &&
      cvv.length === 3
    );
  };

  return (
    <>
      <Navbar />
      <div className="container mt-5">
        {!paymentSuccess ? (
          <>
            <div className="text-center mb-4">
              <h2 className="display-4">Payment</h2>
              <p className="lead">
                Complete your payment for Order #{order.orderId}
              </p>
            </div>
            <form onSubmit={handlePayment}>
              <PaymentMethodSelection
                paymentMethod={paymentMethod}
                setPaymentMethod={setPaymentMethod}
              />
              {paymentMethod === "creditCard" && (
                <div className="mb-3">
                  <label htmlFor="cardNumber" className="form-label">Card Number</label>
                  <input
                    type="text"
                    id="cardNumber"
                    className="form-control"
                    value={paymentDetails.cardNumber}
                    onChange={(e) => setPaymentDetails({ ...paymentDetails, cardNumber: e.target.value })}
                    placeholder="1234 5678 9012 3456"
                    maxLength="16"
                    required
                  />
                  <label htmlFor="expiryDate" className="form-label">Expiry Date</label>
                  <input
                    type="text"
                    id="expiryDate"
                    className="form-control"
                    value={paymentDetails.expiryDate}
                    onChange={(e) => setPaymentDetails({ ...paymentDetails, expiryDate: e.target.value })}
                    placeholder="MM/YY"
                    maxLength="5"
                    required
                  />
                  <label htmlFor="cvv" className="form-label">CVV</label>
                  <input
                    type="text"
                    id="cvv"
                    className="form-control"
                    value={paymentDetails.cvv}
                    onChange={(e) => setPaymentDetails({ ...paymentDetails, cvv: e.target.value })}
                    placeholder="123"
                    maxLength="3"
                    required
                  />
                </div>
              )}
              <div className="text-center">
                <button type="submit" className="btn btn-success btn-lg">
                  Pay Now
                </button>
              </div>
            </form>
          </>
        ) : (
          <PaymentSuccessCard
            amount={order.totalPrice}
            transactionId={transactionId}
            paymentDate={paymentDate}
          />
        )}
      </div>
    </>
  );
};

export default Payment;
