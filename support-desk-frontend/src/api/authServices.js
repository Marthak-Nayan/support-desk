import { myAxios } from "./helper";

export const loginUser = async (userData) => {
  try {
    const response = await myAxios.post("/auth/login", userData);
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const signupUser = async (userData) => {
  try {
    const response = await myAxios.post("/auth/signup", userData);
    return response.data;
  } catch (error) {
    throw error;
  }
};
