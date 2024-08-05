import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import { register } from "../services/user";
import { HttpStatusCode } from "axios";

function Register() {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [address, setAddress] = useState("");
  const [userType, setUserType] = useState("customer");

  const navigate = useNavigate();

  const onRegister = async () => {
    if (name.length === 0) {
      toast.error("Please enter name");
    } else if (email.length === 0) {
      toast.error("Please enter email");
    } else if (password.length === 0) {
      toast.error("Please enter password");
    } else if (confirmPassword.length === 0) {
      toast.error("Please confirm the password");
    } else if (password !== confirmPassword) {
      toast.error("Password does not match");
    } else if (userType.length === 0) {
      toast.error("Please select user type");
    } else if (phoneNumber.length === 0) {
      toast.error("Please enter phone number");
    } else if (address.length === 0) {
      toast.error("Please enter address");
    } else {
      const result = await register(
        name,
        email,
        password,
        userType,
        phoneNumber,
        address
      );
      if (result.status === HttpStatusCode.Created) {
        toast.success("Successfully registered a new user");
        navigate("/login");
      } else {
        toast.error(result.error);
      }
    }
  };

  return (
    <div className="container mt-4">
      <div className="row justify-content-center">
        <div className="col-md-8 col-lg-6">
          <div className="card p-4 shadow-lg border-light">
            <h2 className="text-center mb-3">Register</h2>
            <div className="mb-3">
              <label htmlFor="name" className="form-label">
                Name
              </label>
              <input
                id="name"
                type="text"
                className="form-control"
                onChange={(e) => setName(e.target.value)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="email" className="form-label">
                Email
              </label>
              <input
                id="email"
                type="email"
                className="form-control"
                onChange={(e) => setEmail(e.target.value)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="password" className="form-label">
                Password
              </label>
              <input
                id="password"
                type="password"
                className="form-control"
                onChange={(e) => setPassword(e.target.value)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="confirmPassword" className="form-label">
                Confirm Password
              </label>
              <input
                id="confirmPassword"
                type="password"
                className="form-control"
                onChange={(e) => setConfirmPassword(e.target.value)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="phoneNumber" className="form-label">
                Phone Number
              </label>
              <input
                id="phoneNumber"
                type="tel"
                className="form-control"
                onChange={(e) => setPhoneNumber(e.target.value)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="address" className="form-label">
                Address
              </label>
              <input
                id="address"
                type="text"
                className="form-control"
                onChange={(e) => setAddress(e.target.value)}
              />
            </div>
            <div className="text-center mb-3">
              Already have an account?{" "}
              <Link to="/login" className="text-decoration-none text-success">
                Login here
              </Link>
            </div>
            <div className="text-center">
              <button
                onClick={onRegister}
                className="btn btn-success w-100"
                style={{ boxShadow: "0 4px 6px rgba(0, 0, 0, 0.1)" }}
              >
                Register
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Register;
