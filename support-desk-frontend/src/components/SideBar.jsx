// src/components/SideBar.jsx
import React from "react";
import { DASHBOARD_VIEW, ROLES } from "../constants/appConstants";

export default function SideBar({ role, setActiveView }) {
  return (
    <aside className="w-64 bg-white shadow-md">
      <div className="p-4 text-xl font-semibold border-b">
        Mini Support Desk
      </div>

      <nav className="p-4 space-y-2">
        <button
          onClick={() => setActiveView(DASHBOARD_VIEW.CREATE_TICKET)}
          className="w-full text-left px-3 py-2 rounded-lg hover:bg-gray-100"
        >
          Create Ticket
        </button>

        {role === ROLES.ADMIN && (
          <button
            onClick={() => setActiveView(DASHBOARD_VIEW.ALL_TICKETS)}
            className="w-full text-left px-3 py-2 rounded-lg hover:bg-gray-100"
          >
            View All Tickets
          </button>
        )}

        {role === ROLES.USER && (
          <button
            onClick={() => setActiveView(DASHBOARD_VIEW.MY_TICKETS)}
            className="w-full text-left px-3 py-2 rounded-lg hover:bg-gray-100"
          >
            My Tickets
          </button>
        )}
      </nav>
    </aside>
  );
}
