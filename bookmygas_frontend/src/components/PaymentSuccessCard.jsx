// src/components/PaymentSuccessCard.js
import React from "react";
import { FaCheckCircle } from "react-icons/fa";
import { useNavigate } from "react-router-dom";

const PaymentSuccessCard = ({ amount, transactionId, paymentDate }) => {
  const navigate = useNavigate();

  return (
    <div className="card mt-5">
      <div className="card-body text-center">
        <FaCheckCircle size={80} color="green" />
        <h2 className="card-title mt-3">Payment Successful</h2>
        <p className="card-text">
          <strong>Amount:</strong> Rs. {amount}
        </p>
        <p className="card-text">
          <strong>Transaction ID:</strong> {transactionId}
        </p>
        <p className="card-text">
          <strong>Payment Date:</strong>{" "}
          {new Date(paymentDate).toLocaleString()}
        </p>
        <button
          className="btn btn-primary mt-3"
          onClick={() => navigate("/order-history")}
        >
          Go to Order History
        </button>
      </div>
    </div>
  );
};

export default PaymentSuccessCard;
