import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';

function Navbar() {
  const navigate = useNavigate();
  const user = JSON.parse(sessionStorage.getItem('user') || '{}'); // Default to empty object if no user data

  const handleLogout = () => {
    sessionStorage.clear();
    toast.success('You have been logged out.');
    navigate('/login');
  };

  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
      <div className="container-fluid">
        <Link className="navbar-brand" to="/">Home</Link>
        <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarNav">
          <ul className="navbar-nav me-auto">
            {/* Render navigation links based on user type */}
            {user && user.userType === "admin" ? (
              <>
                <li className="nav-item">
                  <Link className="nav-link" to="/statistics">Statistics</Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/agencies">Agencies</Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/vendors">Vendors</Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/users">Users</Link>
                </li>
              </>
            ) : (
              <>
                <li className="nav-item">
                  <Link className="nav-link" to="/home">Home</Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/connection-view">Connections</Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/book-gas">Book</Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/feedback">Feedback</Link>
                </li>
              </>
            )}
          </ul>
          <ul className="navbar-nav ms-auto">
            {user ? (
              <li className="nav-item">
                <button className="nav-link btn" onClick={handleLogout}>Logout</button>
              </li>
            ) : (
              <li className="nav-item">
                <Link className="nav-link" to="/login">Login</Link>
              </li>
            )}
          </ul>
        </div>
      </div>
    </nav>
  );
}

export default Navbar;
