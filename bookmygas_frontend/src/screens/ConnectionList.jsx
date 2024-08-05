import React, { useEffect, useState } from "react";
import { deleteConnection, fetchConnections } from "../services/connection";
import Navbar from "../components/navbar";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";

const ConnectionList = () => {
  const [connections, setConnections] = useState([]);
  const user = JSON.parse(sessionStorage.user);
  const navigate = useNavigate();

  useEffect(() => {
    getConnections(user.userId);
  }, [user.userId]);

  const getConnections = async (userId) => {
    try {
      const connectionsList = await fetchConnections(userId);
      setConnections(connectionsList);
    } catch (error) {
      toast.error("Failed to fetch connections");
    }
  };

  const handleDelete = async (requestId) => {
    try {
      await deleteConnection(requestId);
      setConnections(
        connections.filter((conn) => conn.requestId !== requestId)
      );
      toast.success("Connection deleted successfully");
    } catch (error) {
      toast.error("Failed to delete connection");
    }
  };

  return (
    <>
      <Navbar />
      <div className="container mt-5">
        <h3 className="mt-4 mb-3 text-center">Your Connections</h3>
        {connections.length === 0 ? (
          <div className="text-center mt-5">
            <div className="alert alert-info">
              <h4 className="alert-heading">No Connections Found</h4>
              <p className="mb-0">
                It looks like you haven't created any connections yet. Click the
                button below to create a new connection.
              </p>
            </div>
          </div>
        ) : (
          <table className="table table-striped">
            <thead>
              <tr>
                <th>Agency Name</th>
                <th>Email</th>
                <th>Contact</th>
                <th>Type</th>
                <th>Request Date</th>
                <th>Status</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {connections.map((connection) => (
                <tr key={connection.requestId}>
                  <td>{connection.gasAgency.agencyName}</td>
                  <td>{connection.gasAgency.email}</td>
                  <td>{connection.gasAgency.phoneNumber}</td>
                  <td>{connection.type}</td>
                  <td>{new Date(connection.requestDate).toLocaleString()}</td>
                  <td>{connection.status}</td>
                  <td>
                    <button
                      className="btn btn-outline-danger btn-sm"
                      onClick={() => handleDelete(connection.requestId)}
                    >
                      Delete
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
        <div className="text-center mt-4">
          <button
            className="btn btn-outline-secondary me-3"
            onClick={() => navigate(-1)}
          >
            Go Back
          </button>
          <button
            className="btn btn-outline-primary"
            onClick={() => navigate("/create-connection")}
          >
            Create Connection
          </button>
        </div>
      </div>
    </>
  );
};

export default ConnectionList;
