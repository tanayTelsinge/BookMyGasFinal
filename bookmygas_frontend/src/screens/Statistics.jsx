import React, { useState, useEffect } from "react";
import { fetchStatistics } from "../services/statistics"; // Replace with actual service
import { Navbar } from "react-bootstrap";

function Statistics() {
  const [statistics, setStatistics] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    // Fetch statistics from the server when component mounts
    const getStatistics = async () => {
      try {
        const data = await fetchStatistics(); // Replace with actual API call
        setStatistics(data);
      } catch (error) {
        console.error("Failed to fetch statistics:", error);
      } finally {
        setLoading(false);
      }
    };

    getStatistics();
  }, []);

  if (loading) {
    return <div className="text-center mt-5">Loading...</div>;
  }

  return (
    <div className="container mt-5">
      <Navbar></Navbar>
      <h2 className="text-center mb-4">Statistics Overview</h2>
      <div style={{ display: "flex", justifyContent: "space-around" }}>
        <div
          style={{
            width: "30%",
            padding: "1rem",
            border: "1px solid #ddd",
            borderRadius: "8px",
          }}
        >
          <h4>Total Orders</h4>
          <p>{statistics.totalOrders}</p>
        </div>
        <div
          style={{
            width: "30%",
            padding: "1rem",
            border: "1px solid #ddd",
            borderRadius: "8px",
          }}
        >
          <h4>Total Users</h4>
          <p>{statistics.totalUsers}</p>
        </div>
        <div
          style={{
            width: "30%",
            padding: "1rem",
            border: "1px solid #ddd",
            borderRadius: "8px",
          }}
        >
          <h4>Total Revenue</h4>
          <p>₹{statistics.totalRevenue.toLocaleString()}</p>
        </div>
      </div>
    </div>
  );
}

export default Statistics;
