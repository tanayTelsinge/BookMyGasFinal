import React from "react";
import { Link, useNavigate } from "react-router-dom";
import Navbar from "../components/navbar";
import "../styles/home.css"; // Ensure this imports the CSS file containing the styles

function Home() {
  const navigate = useNavigate();
  let user = null;

  if (sessionStorage.user) {
    user = JSON.parse(sessionStorage.user);
  }

  const handleOptionClick = (e, path) => {
    if (!user) {
      e.preventDefault();
      alert("Please log in to access this option.");
      navigate("/login");
    }
  };

  return (
    <div>
      <Navbar />
      <div className="container mt-5">
        <div className="text-center mb-4">
          <h2 className="display-4">
            Welcome {user ? user.name : "Guest"}
          </h2>
          <p className="lead">Choose an option below to get started</p>
        </div>
        <div className="row justify-content-center">
          <div className="col-md-8">
            <div className="row g-4">
              {/* Conditionally render Statistics link if user is an admin */}
              {user && user.userType === "admin" && (
                <>
                  <div className="col-md-6 col-lg-3 d-flex align-items-stretch">
                    <Link
                      to="/statistics"
                      className="btn btn-outline-info btn-lg w-100 d-flex align-items-center justify-content-center"
                      style={{ minHeight: "150px" }}
                    >
                      Statistics
                    </Link>
                  </div>
                  <div className="col-md-6 col-lg-3 d-flex align-items-stretch">
                    <Link
                      to="/agencies"
                      className="btn btn-outline-info btn-lg w-100 d-flex align-items-center justify-content-center"
                      style={{ minHeight: "150px" }}
                    >
                      Agencies
                    </Link>
                  </div>
                  <div className="col-md-6 col-lg-3 d-flex align-items-stretch">
                    <Link
                      to="/vendors"
                      className="btn btn-outline-info btn-lg w-100 d-flex align-items-center justify-content-center"
                      style={{ minHeight: "150px" }}
                    >
                      Vendors
                    </Link>
                  </div>
                  <div className="col-md-6 col-lg-3 d-flex align-items-stretch">
                    <Link
                      to="/users"
                      className="btn btn-outline-info btn-lg w-100 d-flex align-items-center justify-content-center"
                      style={{ minHeight: "150px" }}
                    >
                      Users
                    </Link>
                  </div>
                </>
              )}

              {/* Additional links for all users */}
              <div className="col-md-6 col-lg-3 d-flex align-items-stretch">
                <Link
                  to="/connection-view"
                  onClick={(e) => handleOptionClick(e, "/connection-view")}
                  className="btn btn-outline-primary btn-lg w-100 d-flex align-items-center justify-content-center"
                  style={{ minHeight: "150px" }}
                >
                  Apply / View Connections
                </Link>
              </div>
              <div className="col-md-6 col-lg-3 d-flex align-items-stretch">
                <Link
                  to="/book-gas"
                  onClick={(e) => handleOptionClick(e, "/book-gas")}
                  className="btn btn-outline-secondary btn-lg w-100 d-flex align-items-center justify-content-center"
                  style={{ minHeight: "150px" }}
                >
                  Book Gas
                </Link>
              </div>
              <div className="col-md-6 col-lg-3 d-flex align-items-stretch">
                <Link
                  to="/order-history"
                  onClick={(e) => handleOptionClick(e, "/order-history")}
                  className="btn btn-outline-success btn-lg w-100 d-flex align-items-center justify-content-center"
                  style={{ minHeight: "150px" }}
                >
                  View Order History
                </Link>
              </div>
              <div className="col-md-6 col-lg-3 d-flex align-items-stretch">
                <Link
                  to="/feedback"
                  onClick={(e) => handleOptionClick(e, "/feedback")}
                  className="btn btn-outline-warning btn-lg w-100 d-flex align-items-center justify-content-center"
                  style={{ minHeight: "150px" }}
                >
                  Take Feedback
                </Link>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Home;
