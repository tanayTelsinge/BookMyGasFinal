import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import { login } from "../services/user";
import { HttpStatusCode } from "axios";

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [isEmailEmpty, setEmailEmpty] = useState(false);
  const [isPasswordEmpty, setPasswordEmpty] = useState(false);

  const navigate = useNavigate();

  const onLogin = async () => {
    if (email.length === 0) {
      toast.error("Please enter email");
    } else if (password.length === 0) {
      toast.error("Please enter password");
    } else {
      const result = await login(email, password);
      if (result["status"] === HttpStatusCode.Ok) {
        const data = result["data"];
        sessionStorage["user"] = JSON.stringify(data.user);
        sessionStorage["token"] = data["jwt"];
        toast.success(`Welcome ${data.user.name} !`);
        navigate("/home");
      } else {
        toast.error(result["error"]);
      }
    }
  };

  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-6 col-lg-4">
          <div className="card p-4 shadow-lg border-light">
            <h2 className="text-center mb-4">Login</h2>
            <div className="mb-3">
              <label htmlFor="email" className="form-label">
                Email
              </label>
              <input
                id="email"
                type="email"
                className={`form-control ${isEmailEmpty ? "is-invalid" : ""}`}
                onChange={(e) => {
                  setEmail(e.target.value);
                  setEmailEmpty(e.target.value.length === 0);
                }}
              />
              {isEmailEmpty && (
                <div className="invalid-feedback">Email is mandatory</div>
              )}
            </div>
            <div className="mb-4">
              <label htmlFor="password" className="form-label">
                Password
              </label>
              <input
                id="password"
                type="password"
                className={`form-control ${
                  isPasswordEmpty ? "is-invalid" : ""
                }`}
                onChange={(e) => {
                  setPassword(e.target.value);
                  setPasswordEmpty(e.target.value.length === 0);
                }}
              />
              {isPasswordEmpty && (
                <div className="invalid-feedback">Password is mandatory</div>
              )}
            </div>
            <div className="mb-3">
              <div className="text-center">
                Don't have an account?{" "}
                <Link
                  to="/register"
                  className="text-decoration-none text-success fw-bold"
                >
                  Register here
                </Link>
              </div>
            </div>
            <div className="text-center">
              <button
                onClick={onLogin}
                className="btn btn-success w-100"
                style={{ boxShadow: "0 4px 6px rgba(0, 0, 0, 0.1)" }}
              >
                Login
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Login;
