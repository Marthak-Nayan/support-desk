import { myAxios } from "./helper";

export const loginUser = async (userData) => {
  try {
    const response = await myAxios.post("/auth/login", userData,{
      withCredentials: true,
    });
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

export const logoutUser = async () => {
  try {
    await myAxios.post("/auth/logout", null, {
      withCredentials: true,
    });
  } catch (error) {
    throw error;
  }
};


export const getCurrentUser = async () => {
  try {
    const response = await myAxios.get("/authMe", {
      withCredentials: true,
    });
    return response.data;
  } catch (error) {
    throw error;
  }
};
