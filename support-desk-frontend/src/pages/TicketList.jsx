import React, { useEffect, useState } from "react";
import { getAllTickets, updateTicket, updateTicketByUser } from "../api/userServices";
//import { getAllTickets, updateTicket, updateTicketByUser } from "../services/userServices";

const isAdmin = () => localStorage.getItem("role") === "ADMIN";

const STATUS_OPTIONS = ["OPEN", "IN_PROGRESS", "RESOLVED", "CLOSED"];
const PRIORITY_OPTIONS = ["LOW", "MEDIUM", "HIGH"];

export default function TicketList() {
    const [tickets, setTickets] = useState([]);
    const [loading, setLoading] = useState(true);

    // Pagination
    const [pageNumber, setPageNumber] = useState(0);
    const [totalPages, setTotalPages] = useState(0);

    const [editingTicket, setEditingTicket] = useState(null);
    const [isModalOpen, setIsModalOpen] = useState(false);

    // Filter states
    const [selectedStatuses, setSelectedStatuses] = useState([]);
    const [selectedPriorities, setSelectedPriorities] = useState([]);

    useEffect(() => {
        loadTickets();
    }, [pageNumber]);

    const loadTickets = async () => {
        try {
            setLoading(true);
            const response = await getAllTickets(pageNumber, {
                statuses: selectedStatuses,
                priorities: selectedPriorities
            });

            let filteredTickets = response.ticketList || [];

            // Apply filters if selected
            if (selectedStatuses.length > 0) {
                filteredTickets = filteredTickets.filter(ticket =>
                    selectedStatuses.includes(ticket.status)
                );
            }
            if (selectedPriorities.length > 0) {
                filteredTickets = filteredTickets.filter(ticket =>
                    selectedPriorities.includes(ticket.priority)
                );
            }

            setTickets(filteredTickets);
            setTotalPages(Math.ceil(response.totalElements / response.size));
        } catch (err) {
            console.error("Failed to load tickets", err);
        } finally {
            setLoading(false);
        }
    };

    const handleSave = async () => {
        try {
            let successMsg = null;
            if (isAdmin()) {
                const data = await updateTicket(editingTicket.id, editingTicket);
                successMsg = data.message;
            } else {
                const data = await updateTicketByUser(editingTicket.id, editingTicket);
                successMsg = data.message;
            }
            setIsModalOpen(false);
            setEditingTicket(null);
            loadTickets();
            alert("Ticket updated successfully");
        } catch (err) {
            console.error(err);
            alert("Failed to update ticket: " + err.message);
        }
    };

    // Pagination
    const handlePrevPage = () => pageNumber > 0 && setPageNumber(pageNumber - 1);
    const handleNextPage = () => pageNumber < totalPages - 1 && setPageNumber(pageNumber + 1);

    // Filter handlers
    const handleStatusChange = (status) => {
        setSelectedStatuses(prev =>
            prev.includes(status)
                ? prev.filter(s => s !== status)
                : [...prev, status]
        );
    };

    const handlePriorityChange = (priority) => {
        setSelectedPriorities(prev =>
            prev.includes(priority)
                ? prev.filter(p => p !== priority)
                : [...prev, priority]
        );
    };

    const applyFilters = () => {
        setPageNumber(0);
        loadTickets();
    };

    if (loading) {
        return <p className="text-center mt-10">Loading tickets...</p>;
    }

    return (
        <div className="p-6">
            <h2 className="text-2xl font-bold mb-4">Ticket List</h2>

            {/* Filter Section */}
            <div className="mb-4 p-4 border rounded bg-gray-50">
                <h3 className="font-semibold mb-2">Filters</h3>

                <div className="flex gap-6 mb-2">
                    {/* Status Filter */}
                    <div>
                        <p className="font-medium">Status:</p>
                        {STATUS_OPTIONS.map(status => (
                            <label key={status} className="inline-flex items-center mr-3">
                                <input
                                    type="checkbox"
                                    checked={selectedStatuses.includes(status)}
                                    onChange={() => handleStatusChange(status)}
                                    className="mr-1"
                                />
                                {status}
                            </label>
                        ))}
                    </div>

                    {/* Priority Filter */}
                    <div>
                        <p className="font-medium">Priority:</p>
                        {PRIORITY_OPTIONS.map(priority => (
                            <label key={priority} className="inline-flex items-center mr-3">
                                <input
                                    type="checkbox"
                                    checked={selectedPriorities.includes(priority)}
                                    onChange={() => handlePriorityChange(priority)}
                                    className="mr-1"
                                />
                                {priority}
                            </label>
                        ))}
                    </div>
                </div>

                <button
                    onClick={applyFilters}
                    className="px-4 py-2 rounded bg-blue-600 text-white hover:bg-blue-700"
                >
                    Apply
                </button>
            </div>

            {tickets.length === 0 ? (
                <p>No tickets found</p>
            ) : (
                <>
                    <table className="w-full border border-gray-300">
                        <thead className="bg-gray-200">
                            <tr className="text-center">
                                <th className="border p-2">No.</th>
                                <th className="border p-2">Title</th>
                                <th className="border p-2">Status</th>
                                <th className="border p-2">Priority</th>
                                <th className="border p-2">Owner</th>
                                {isAdmin() && <th className="border p-2">Action</th>}
                            </tr>
                        </thead>
                        <tbody>
                            {tickets.map((ticket, index) => (
                                <tr key={ticket.id} className="text-center">
                                    <td className="border p-2">{pageNumber * 10 + index + 1}</td>
                                    <td className="border p-2">{ticket.title}</td>
                                    <td className="border p-2">{ticket.status}</td>
                                    <td className="border p-2">{ticket.priority}</td>
                                    <td className="border p-2">{ticket.createdBy?.username || "N/A"}</td>
                                    {/*<td className="border p-2">
                                        {(isAdmin() || ticket.status === "OPEN" || ticket.status === "IN_PROGRESS") ? (
                                            <button
                                                onClick={() => {
                                                    setEditingTicket({ ...ticket, status: ticket.status ?? "OPEN" });
                                                    setIsModalOpen(true);
                                                }}
                                                className="bg-blue-600 text-white px-3 py-1 rounded hover:bg-blue-700"
                                            >
                                                Edit
                                            </button>
                                        ) : (
                                            <span className="text-gray-400">Not allowed</span>
                                        )}
                                    </td>*/}
                                    {isAdmin() && (
                                    <td className="border p-2">
                                        
                                            <button
                                                onClick={() => {
                                                    setEditingTicket({ ...ticket, status: ticket.status ?? "OPEN" });
                                                    setIsModalOpen(true);
                                                }}
                                                className="bg-blue-600 text-white px-3 py-1 rounded hover:bg-blue-700"
                                            >
                                                Edit
                                            </button>
                                    </td>
                                    )}
                                </tr>
                            ))}
                        </tbody>
                    </table>

                    {/* Pagination */}
                    <div className="flex justify-between items-center mt-4">
                        <button
                            onClick={handlePrevPage}
                            disabled={pageNumber === 0}
                            className="px-4 py-2 rounded border disabled:opacity-50"
                        >
                            Previous
                        </button>

                        <span className="font-semibold">
                            Page {pageNumber + 1} of {totalPages}
                        </span>

                        <button
                            onClick={handleNextPage}
                            disabled={pageNumber === totalPages - 1}
                            className="px-4 py-2 rounded border disabled:opacity-50"
                        >
                            Next
                        </button>
                    </div>
                </>
            )}

            {/* Edit Modal */}
            {isModalOpen && editingTicket && (
                <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50">
                    <div className="bg-white p-6 rounded shadow-lg w-96">
                        <h2 className="text-xl font-bold mb-4">Edit Ticket</h2>

                        <label className="block mb-2">Title</label>
                        <input
                            type="text"
                            value={editingTicket.title}
                            onChange={(e) =>
                                setEditingTicket({ ...editingTicket, title: e.target.value })
                            }
                            className="border p-2 w-full mb-4 rounded"
                        />

                        <label className="block mb-2">Description</label>
                        <textarea
                            value={editingTicket.description}
                            onChange={(e) =>
                                setEditingTicket({ ...editingTicket, description: e.target.value })
                            }
                            className="border p-2 w-full mb-4 rounded"
                        />

                        <label className="block mb-2">Priority</label>
                        <select
                            value={editingTicket.priority}
                            onChange={(e) =>
                                setEditingTicket({ ...editingTicket, priority: e.target.value })
                            }
                            className="border p-2 w-full mb-4 rounded"
                        >
                            <option value="LOW">LOW</option>
                            <option value="MEDIUM">MEDIUM</option>
                            <option value="HIGH">HIGH</option>
                        </select>


                        <label className="block mb-2">Status</label>
                        <select
                            value={editingTicket.status}
                            onChange={(e) =>
                                setEditingTicket({ ...editingTicket, status: e.target.value })
                            }
                            className="border p-2 w-full mb-4 rounded"
                        >
                            <option value="OPEN">OPEN</option>
                            <option value="IN_PROGRESS">IN_PROGRESS</option>
                            <option value="RESOLVED">RESOLVED</option>
                            <option value="CLOSED">CLOSED</option>
                        </select>


                        <div className="flex justify-end gap-2">
                            <button
                                onClick={() => setIsModalOpen(false)}
                                className="px-4 py-2 rounded border"
                            >
                                Cancel
                            </button>
                            <button
                                onClick={handleSave}
                                className="px-4 py-2 rounded bg-blue-600 text-white hover:bg-blue-700"
                            >
                                Save
                            </button>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
}
