// src/components/PaymentMethodSelection.js
import React from "react";

const PaymentMethodSelection = ({ paymentMethod, setPaymentMethod }) => {
  return (
    <div className="mb-3">
      <label className="form-label">Select Payment Method:</label>
      <div className="d-flex justify-content-center">
        <div className="btn-group" role="group" aria-label="Payment Method">
          <input
            type="radio"
            className="btn-check"
            name="paymentMethod"
            id="upi"
            value="UPI"
            checked={paymentMethod === "UPI"}
            onChange={(e) => setPaymentMethod(e.target.value)}
            autoComplete="off"
          />
          <label className="btn btn-outline-primary btn-lg" htmlFor="upi">
            UPI
          </label>

          <input
            type="radio"
            className="btn-check"
            name="paymentMethod"
            id="debit"
            value="DEBIT"
            checked={paymentMethod === "DEBIT"}
            onChange={(e) => setPaymentMethod(e.target.value)}
            autoComplete="off"
          />
          <label className="btn btn-outline-primary btn-lg" htmlFor="debit">
            Debit Card
          </label>

          <input
            type="radio"
            className="btn-check"
            name="paymentMethod"
            id="credit"
            value="CREDIT"
            checked={paymentMethod === "CREDIT"}
            onChange={(e) => setPaymentMethod(e.target.value)}
            autoComplete="off"
          />
          <label className="btn btn-outline-primary btn-lg" htmlFor="credit">
            Credit Card
          </label>
        </div>
      </div>
    </div>
  );
};

export default PaymentMethodSelection;
