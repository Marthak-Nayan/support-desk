import React, { useCallback, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import CreateTicketForm from "./CreateTicketForm";
import { DASHBOARD_VIEW } from "../constants/appConstants";
import TicketList from "./TicketList";
import WelcomeCard from "../components/Welcome";
import MetricCard from "../components/MetricCard ";
import SideBar from "../components/SideBar";
import { getTicketMatrix } from "../api/userServices";
import { logoutUser } from "../api/authServices";



export default function Dashboard() {
  const navigate = useNavigate();
  const role = localStorage.getItem("role");
  const [loading, setLoading] = useState(false);


  const [activeView, setActiveView] = useState("HOME");
  const [matrix, setMatrix] = useState({
    todayTickets: 0,
    weekTickets: 0,
    openTickets: 0,
    resolvedTickets: 0
  });

  const loadMatrix = useCallback(async () => {
    try {
      setLoading(true);
      const res = await getTicketMatrix();
      setMatrix(res);
    } catch (err) {
      console.error("Failed to load ticket metrics", err);
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    loadMatrix();
  }, []);

  const logoutAction = () => {
    logoutUser();
    localStorage.removeItem("role");
    navigate("/login");
  };

  

  return (
    <div className="flex h-screen bg-gray-100">
      <SideBar role={role} setActiveView={setActiveView} />

      {/* Dashboard Section */}
      <div className="flex-1 flex flex-col">
        <header className="h-16 bg-white shadow-sm flex items-center justify-end px-6">

          <div className="flex justify-end items-end gap-4">
            <button
              className="px-3 py-2 text-sm rounded-lg border hover:bg-gray-100"
              onClick={logoutAction}
            >
              Logout
            </button>
          </div>
        </header>

        {/* Content */}
        <main className="flex-1 p-6 overflow-auto">
          {activeView === DASHBOARD_VIEW.HOME && (
            <>
              <WelcomeCard role={role} />

              <div className="grid grid-cols-4 gap-6 mt-6">
                <MetricCard label="Today Tickets" value={matrix.todayTickets} />
                <MetricCard label="This Week" value={matrix.weekTickets} />
                <MetricCard label="Open" value={matrix.openTickets} />
                <MetricCard label="Resolved" value={matrix.resolvedTickets} />
              </div>

              {loading && (
                <p className="text-center text-gray-500 mt-4">
                  Loading metrics...
                </p>
              )}
            </>
          )}

          {activeView === DASHBOARD_VIEW.CREATE_TICKET && (
            <CreateTicketForm onSuccess={() => { loadMatrix(); setActiveView(DASHBOARD_VIEW.HOME); }} />
          )}

          {(activeView === DASHBOARD_VIEW.MY_TICKETS || activeView === DASHBOARD_VIEW.ALL_TICKETS) && (
            <TicketList onSuccess={() => setActiveView(DASHBOARD_VIEW.HOME)} />
          )}
        </main>
      </div>
    </div>
  );
}
