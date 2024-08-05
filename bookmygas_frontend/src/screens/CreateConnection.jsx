import React, { useState, useEffect } from "react";
import { toast } from "react-toastify";
import { fetchVendors } from "../services/vendor";
import { fetchAgencies } from "../services/agency";
import { createConnection, fetchConnections } from "../services/connection";
import { HttpStatusCode } from "axios";
import ConnectionList from "./ConnectionList"; // Adjust the import path as needed
import { useNavigate } from "react-router-dom";

const CreateConnection = () => {
  const user = JSON.parse(sessionStorage.user);
  const userId = user.userId;
  const [vendorId, setVendorId] = useState("");
  const [agencyId, setAgencyId] = useState("");
  const [connectionType, setConnectionType] = useState("");
  const [vendors, setVendors] = useState([]);
  const [agencies, setAgencies] = useState([]);
  const [connections, setConnections] = useState([]);
  const [requestDate] = useState(new Date().toISOString());
  const navigate = useNavigate();

  useEffect(() => {
    getVendors();
    getConnections(userId);
  }, []);

  const getVendors = async () => {
    const vendorsList = await fetchVendors();
    setVendors(vendorsList);
  };

  const getAgenciesForVendor = async (vendorId) => {
    setVendorId(vendorId);
    const agencyList = await fetchAgencies(vendorId);
    setAgencies(agencyList);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    if (connections != null && connections.length >= 2) {
      toast.error("Limit exceeded!");
      return;
    }
    const connectionRequest = {
      user: { userId },
      gasAgency: { agencyId : agencyId },
      requestDate: new Date().toISOString(),
      type: connectionType,
      status: "APPROVED",
    };

    const response = await createConnection(connectionRequest);
    if (response["status"] === HttpStatusCode.Created) {
      toast.success("Connection created successfully");
      navigate("/connections");
    } else {
      toast.error("Failed to create connection");
    }
  };

  const getConnections = async (userId) => {
    // Fetch the list of connections from your API
    const connectionsList = await fetchConnections(userId);
    setConnections(connectionsList);
  };

  return (
    <div className="container mt-4">
      <h2 className="mb-4 text-center">Apply For Connection</h2>
      <div className="row justify-content-center">
        <div className="col-md-8">
          <form onSubmit={handleSubmit} className="mb-4">
            <div className="mb-3">
              <label htmlFor="vendorSelect" className="form-label">
                <h5>Vendor</h5>
              </label>
              <select
                id="vendorSelect"
                className="form-select"
                value={vendorId}
                onChange={(e) => getAgenciesForVendor(e.target.value)}
                required
              >
                <option value="">Select Vendor</option>
                {vendors.map((vendor) => (
                  <option key={vendor.vendorId} value={vendor.vendorId}>
                    {vendor.vendorName}
                  </option>
                ))}
              </select>
            </div>
            <div className="mb-3">
              <label htmlFor="agencySelect" className="form-label">
                <h5>Agency</h5>
              </label>
              <select
                id="agencySelect"
                className="form-select"
                value={agencyId}
                onChange={(e) => setAgencyId(e.target.value)}
                required
              >
                <option value="">Select Agency</option>
                {agencies.map((agency) => (
                  <option key={agency.agencyId} value={agency.agencyId}>
                    {agency.agencyName}
                  </option>
                ))}
              </select>
            </div>
            <div className="mb-3">
              <label htmlFor="connectionTypeSelect" className="form-label">
                <h5>Connection Type</h5>
              </label>
              <select
                id="connectionTypeSelect"
                className="form-select"
                value={connectionType}
                onChange={(e) => setConnectionType(e.target.value)}
                required
              >
                <option value="">Select Type</option>
                <option value="DOMESTIC">Domestic</option>
                <option value="COMMERCIAL">Commercial</option>
              </select>
            </div>
            <button type="submit" className="btn btn-success w-100">
              Submit
            </button>
          </form>
        </div>
        <div className="text-center mt-4">
          <button
            className="btn btn-outline-secondary"
            onClick={() => navigate(-1)}
          >
            Go Back
          </button>
        </div>
      </div>
    </div>
  );
};

export default CreateConnection;
