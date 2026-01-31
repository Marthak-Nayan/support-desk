import { authHeader, myAxios } from "./helper";

export const createTicket = async (ticketData) => {
    try {
        const response = await myAxios.post("/createTicket", ticketData, authHeader());
        return response.data;
    } catch (error) {
        const message = error?.response?.data?.message || "Failed to create ticket.";
        throw new Error(message);
    }
};
