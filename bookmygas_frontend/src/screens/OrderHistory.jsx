import React, { useState, useEffect } from "react";
import axios from "axios";
import { Modal, Button } from "react-bootstrap";
import Navbar from "../components/navbar";
import { getOrdersForUser } from "../services/order";
import { getPaymentByOrder } from "../services/payment";

const OrderHistory = () => {
  const user = JSON.parse(sessionStorage.user);
  const [orders, setOrders] = useState([]);
  const [selectedOrder, setSelectedOrder] = useState(null);
  const [payment, setPayment] = useState({});
  const [showModal, setShowModal] = useState(false);

  useEffect(() => {
    if (user && user.userId) {
      fetchOrders(user.userId);
    }
  }, []);

  const fetchOrders = async (userId) => {
    try {
      const response = await getOrdersForUser(userId);
      setOrders(response.data);
    } catch (error) {
      console.error("Error fetching orders:", error);
    }
  };

  const showDetails = async (order) => {
    const response = await getPaymentByOrder(order.orderId);
    setSelectedOrder(order);
    if (response != null && response.data.length > 0) {
      setPayment(response.data[0]);
    }
    setShowModal(true);
  };

  const handleClose = () => setShowModal(false);

  return (
    <>
      <Navbar />
      <div className="container mt-5">
        <h2 className="display-4 text-center mb-4">Order History</h2>
        <div className="row">
          {orders.map((order) => (
            <div className="col-md-4" key={order.orderId}>
              <div
                className="card mb-3 shadow-sm"
                onClick={() => showDetails(order)}
              >
                <div className="card-body">
                  <h5 className="card-title">Order #{order.orderId}</h5>
                  <p className="card-text">
                    <strong>Date:</strong>{" "}
                    {new Date(order.orderDate).toLocaleDateString()}
                  </p>
                  <p className="card-text">
                    <strong>Status:</strong> {order.status}
                  </p>
                  <p className="card-text">
                    <strong>Total Price:</strong> Rs. {order.totalPrice}
                  </p>
                </div>
              </div>
            </div>
          ))}
        </div>
        {selectedOrder && (
          <Modal show={showModal} onHide={handleClose}>
            <Modal.Header closeButton>
              <Modal.Title>Order Details</Modal.Title>
            </Modal.Header>
            <Modal.Body>
              <p>
                <strong>Order ID:</strong> {selectedOrder.orderId}
              </p>
              <p>
                <strong>Date:</strong>{" "}
                {new Date(selectedOrder.orderDate).toLocaleDateString()}
              </p>
              <p>
                <strong>Delivery Date:</strong>{" "}
                {new Date(selectedOrder.deliveryDate).toLocaleDateString()}
              </p>
              <p>
                <strong>Status:</strong> {selectedOrder.status}
              </p>
              <p>
                <strong>Total Price:</strong> Rs. {selectedOrder.totalPrice}
              </p>
              <p>
                <strong>Payment Status:</strong> {payment.status}
              </p>
              <p>
                <strong>Payment Method:</strong> {payment.paymentMethod}
              </p>
              <p>
                <strong>Transaction ID:</strong> {payment.transactionId}
              </p>
            </Modal.Body>
            <Modal.Footer>
              <Button variant="secondary" onClick={handleClose}>
                Close
              </Button>
            </Modal.Footer>
          </Modal>
        )}
      </div>
    </>
  );
};

export default OrderHistory;
