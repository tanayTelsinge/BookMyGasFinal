// src/screens/BookGas.js
import React, { useState, useEffect } from "react";
import axios, { HttpStatusCode } from "axios";
import { toast } from "react-toastify";
import { fetchConnections } from "../services/connection"; // Assuming you have a service to fetch connections
import { createOrder } from "../services/order"; // Assuming you have a service to handle order creation
import Navbar from "../components/navbar";
import { useNavigate } from "react-router-dom";

const BookGas = () => {
  const user = JSON.parse(sessionStorage.user);
  const [connectionType, setConnectionType] = useState("DOMESTIC");
  const [connection, setConnection] = useState(null);
  const [connections, setConnections] = useState([]);
  const navigate = useNavigate();

  const prices = {
    DOMESTIC: 802,
    COMMERCIAL: 1605,
  };

  const weights = {
    DOMESTIC: "14.2 kg",
    COMMERCIAL: "19 kg",
  };

  useEffect(() => {
    if (user && user.userId) {
      getConnection(user.userId);
    }
  }, [connectionType]);

  const getConnection = async (userId) => {
    // Fetch the connection based on userId
    const connections = await fetchConnections(userId);
    setConnections(connections);

    if (connections && connections.length > 0) {
      const filteredConnection = connections.find(
        (con) => con.type === connectionType
      );
      setConnection(filteredConnection || null);
    } else {
      setConnection(null);
    }
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    if (!connection) {
      toast.error("No connection available for the selected type");
      return;
    }

    const deliveryDate = new Date();
    deliveryDate.setDate(deliveryDate.getDate() + 2);
    const orderRequest = {
      userId: user.userId,
      agencyId: connection.gasAgency.agencyId,
      orderDate: new Date().toISOString(),
      status: "PENDING",
      totalPrice: prices[connectionType],
      deliveryDate: deliveryDate.toISOString(), // Set delivery date later
    };

    const response = await createOrder(orderRequest);
    if (response.status === HttpStatusCode.Created) {
      toast.success("Order created successfully");
      navigate("/payment", {
        state: {
          order: response.data,
        },
      });
    } else {
      toast.error("Failed to create order");
    }
  };

  const setConnectionBasedOnType = (type) => {
    setConnectionType(type);
    if (connections && connections.length > 0) {
      const filteredConnection = connections.find(
        (connection) => connection.type === type
      );
      setConnection(filteredConnection || null);
    } else {
      setConnection(null);
    }
  };

  return (
    <>
      <Navbar />
      <div className="container mt-5">
        <div className="text-center mb-4">
          <h2 className="display-4">Book Gas</h2>
          <p className="lead">
            Select connection type to proceed with your order
          </p>
        </div>
        <form onSubmit={handleSubmit}>
          <div className="mb-3">
            <label className="form-label">Connection Type:</label>
            <div className="d-flex justify-content-center">
              <div
                className="btn-group"
                role="group"
                aria-label="Connection Type"
              >
                <input
                  type="radio"
                  className="btn-check"
                  name="connectionType"
                  id="domestic"
                  value="DOMESTIC"
                  checked={connectionType === "DOMESTIC"}
                  onChange={(e) => setConnectionBasedOnType(e.target.value)}
                  autoComplete="off"
                />
                <label
                  className="btn btn-outline-primary btn-lg"
                  htmlFor="domestic"
                >
                  Domestic
                </label>

                <input
                  type="radio"
                  className="btn-check"
                  name="connectionType"
                  id="commercial"
                  value="COMMERCIAL"
                  checked={connectionType === "COMMERCIAL"}
                  onChange={(e) => setConnectionBasedOnType(e.target.value)}
                  autoComplete="off"
                />
                <label
                  className="btn btn-outline-primary btn-lg"
                  htmlFor="commercial"
                >
                  Commercial
                </label>
              </div>
            </div>
          </div>
          {connectionType && (
            <div className="mb-3">
              {connection ? (
                <div className="alert alert-info text-center" role="alert">
                  <h4 className="alert-heading">Connection Details</h4>
                  <hr />
                  <p>
                    <strong>Price:</strong> Rs. {prices[connectionType]}
                  </p>
                  <p>
                    <strong>Weight:</strong> {weights[connectionType]}
                  </p>
                </div>
              ) : (
                <div className="alert alert-warning text-center" role="alert">
                  <strong>No connection found!</strong> Please check if you have
                  the selected type of connection or create one.
                </div>
              )}
            </div>
          )}
          <div className="text-center">
            <button
              type="submit"
              className="btn btn-success btn-lg"
              disabled={!connection}
            >
              Book Now
            </button>
          </div>
        </form>
      </div>
    </>
  );
};

export default BookGas;
