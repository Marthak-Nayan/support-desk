import React, { useEffect, useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { loginUser } from '../api/authServices';
import { toast } from 'react-toastify';


const Login = () => {

  const navigate = useNavigate();

  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  useEffect(() => {
      const role = localStorage.getItem("role");
      if (role) {
        navigate("/dashboard");
      }
    }, [navigate]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    const formData = new FormData(e.target);
    const userData = Object.fromEntries(formData.entries());

    const emailRegex =
      /^[a-zA-Z0-9._%+-]+@([a-zA-Z0-9-]+\.)?(gmail\.com|yahoo\.com|[a-zA-Z0-9-]+\.in)$/;

    if (!emailRegex.test(userData.username)) {
      setError("Only gmail.com, yahoo.com or .in email addresses are allowed");
      return;
    }

    try {
      setLoading(true);
      setError("");

        const role = localStorage.getItem("role");
        if (role) {
          toast.success("Already logged in");
          navigate("/dashboard");
        }
      
      const data = await loginUser(userData);
      localStorage.setItem("role", data?.role);
      navigate("/dashboard");
    } catch (err) {
      const message =
        err?.response?.data?.message || "Signup failed. Try again.";
        setError(message);
    } finally {
      setLoading(false);
    }
  };

  return (
     <div className="min-h-screen flex items-center justify-center bg-gray-100">
      <div className="bg-white p-8 rounded-lg shadow-md w-full max-w-md">
        <h2 className="text-2xl font-bold text-center text-gray-800 mb-6">
          Mini Support Desk
        </h2>

        <form className="space-y-4" onSubmit={handleSubmit}>
          <div>
            <label className="block text-gray-600 mb-1">Username</label>
            <input
              type="email"
              name="username"
              placeholder="Email"
              required
              className={`w-full px-4 py-2 border rounded-md 
                ${error ? "border-red-500" : ""}`}
            />
          </div>

          <div>
            <label className="block text-gray-600 mb-1">Password</label>
            <input
              type="password"
              name="password"
              required
              placeholder="Enter password"
              className={`w-full px-4 py-2 border rounded-md 
                ${error ? "border-red-500" : ""}`}
            />
          </div>
          <button
            type="submit"
            disabled={loading}
            className={`w-full py-2 rounded-md text-white transition
              ${loading ? "bg-gray-400" : "bg-green-600 hover:bg-green-700"}`}
          >
            {loading ? "Signing in..." : "Sign In"}
          </button>
          {error && (
            <p className="text-red-500 text-sm mt-1">
              {error}
            </p>
          )}
        </form>

        <p className="text-center text-sm text-gray-600 mt-4">
          Don’t have an account?{" "}
          <Link to="/signup" className="text-blue-600 cursor-pointer hover:underline">
            Sign up
          </Link>
        </p>
      </div>
    </div>
  )
}

export default Login;
