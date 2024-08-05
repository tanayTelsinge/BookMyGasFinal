import React from "react";
import { Link, useNavigate } from "react-router-dom";
import Navbar from "../components/navbar";
import "../styles/home.css"; // Ensure this imports the CSS file containing the styles

function ConnectionView() {
  const navigate = useNavigate();
  const user = JSON.parse(sessionStorage.user);

  return (
    <div>
      <Navbar />
      <div className="container mt-5">
        <div className="text-center mb-4">
          <h2 className="display-4">Welcome {user.name}</h2>
          <p className="lead">Choose an option below to get started</p>
        </div>
        <div className="row justify-content-center">
          <div className="col-md-8">
            <div className="row g-4">
              <div className="col-md-6 d-flex align-items-stretch justify-content-center">
                <Link
                  to="/connections"
                  className="btn btn-outline-primary btn-lg w-100 d-flex align-items-center justify-content-center"
                  style={{ minHeight: "150px" }}
                >
                  View Existing Connections
                </Link>
              </div>
              <div className="col-md-6 d-flex align-items-stretch justify-content-center">
                <Link
                  to="/create-connection"
                  className="btn btn-outline-secondary btn-lg w-100 d-flex align-items-center justify-content-center"
                  style={{ minHeight: "150px" }}
                >
                  Apply New Connection
                </Link>
              </div>
            </div>
          </div>
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
}

export default ConnectionView;
