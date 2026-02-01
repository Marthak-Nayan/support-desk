import { myAxios } from "./helper";

export const getAllTickets = async (pageNumber, filters = {}) => {
    try {
        const response = await myAxios.get(`/tickets/getTickets`, {
            params: {
                page: pageNumber,
                size: 10,
                ...filters
            },
            withCredentials: true,
        });
        return response.data;
    } catch (error) {
        const message =
            error?.response?.data?.message ||
            "Failed to fetch tickets.";
        throw new Error(message);
    }
};

export const updateTicket = async (ticketId, updatedData) => {
    try {
        const response = await myAxios.put(`/admin/updateTicket/${ticketId}`, updatedData, {
            withCredentials: true,
        });
        return response.data;
    } catch (error) {
        const message = error?.response?.data?.message || "Failed to update ticket.";
        throw new Error(message);
    }
};

export const getTicketMatrix = async () => {
    try {
        const response = await myAxios.get("/tickets/getTicketMatrix", {
            withCredentials: true,
        });
        return response.data;
    } catch (error) {
        const message = error?.response?.data?.message || "Failed to fetch ticket matrix.";
        throw new Error(message);
    }
};

export const updateTicketByUser = async (ticketId, updatedData) => {
    try {
        const response = await myAxios.put(`/user/updateTicket/${ticketId}`, updatedData, {
            withCredentials: true,
        });
        return response.data;
    } catch (error) {
        const message = error?.response?.data?.message || "Failed to update ticket.";
        throw new Error(message);
    }
};
