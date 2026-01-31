import React, { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { signupUser } from '../api/authServices';

const Signup = () => {

  const navigate = useNavigate();

  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();

    const formData = new FormData(e.target);
    const userData = Object.fromEntries(formData.entries());

    // Frontend validation
    const emailRegex =
      /^[a-zA-Z0-9._%+-]+@([a-zA-Z0-9-]+\.)?(gmail\.com|yahoo\.com|[a-zA-Z0-9-]+\.in)$/;

    if (!emailRegex.test(userData.username)) {
      setError("Only gmail.com, yahoo.com or .in email addresses are allowed");
      return;
    }

    try {
      setLoading(true);
      setError("");

      await signupUser(userData);
      navigate("/login");

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
        <h2 className="text-2xl font-bold text-center mb-6">
          Create Account
        </h2>

        <form className="space-y-4" onSubmit={handleSubmit}>

          <input
            type="text"
            name="fullName"
            placeholder="Full Name"
            required
            className="w-full px-4 py-2 border rounded-md"
          />

          <div>
            <input
              type="email"
              name="username"
              placeholder="Email"
              required
              className={`w-full px-4 py-2 border rounded-md 
                ${error ? "border-red-500" : ""}`}
            />

            {error && (
              <p className="text-red-500 text-sm mt-1">
                {error}
              </p>
            )}
          </div>

          <input
            type="password"
            name="password"
            placeholder="Password"
            required
            minLength={6}
            className="w-full px-4 py-2 border rounded-md"
          />

          <button
            type="submit"
            disabled={loading}
            className={`w-full py-2 rounded-md text-white transition
              ${loading ? "bg-gray-400" : "bg-green-600 hover:bg-green-700"}`}
          >
            {loading ? "Signing up..." : "Sign Up"}
          </button>
        </form>

        <p className="text-center text-sm mt-4">
          Already have an account?{" "}
          <Link to="/login" className="text-blue-600 hover:underline">
            Login
          </Link>
        </p>
      </div>
    </div>
  );
};

export default Signup;
