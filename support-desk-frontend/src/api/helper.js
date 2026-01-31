import axios from "axios";


export const BASE_URL = "http://localhost:8080/api";

export const myAxios = axios.create({
  baseURL: BASE_URL,
});

export const authHeader = () => {
  const token = localStorage.getItem("token");
  return {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };
};

myAxios.interceptors.response.use(
    response => response, 
    error => {
        const errMsg = error.response?.data?.message;

        if (
            errMsg === "Invalid or expired JWT token" ||
            errMsg === "You are not authorized to access this resource" ||
            errMsg === "JWT token is missing" ||
            errMsg === "JWT token has expired"
        ){
        localStorage.removeItem("token"); 
        localStorage.removeItem("role");
        window.location.href = "/login";
      }

        return Promise.reject(error); 
    }
);
