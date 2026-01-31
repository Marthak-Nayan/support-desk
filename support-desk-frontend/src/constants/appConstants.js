export const ROLES = {
  ADMIN: "ADMIN",
  USER: "USER",
};

export const DASHBOARD_VIEW = {
  HOME: "HOME",
  CREATE_TICKET: "CREATE_TICKET",
  MY_TICKETS: "MY_TICKETS",
  ALL_TICKETS: "ALL_TICKETS",
};

export const TICKET_STATUS = {
  OPEN: "OPEN",
  IN_PROGRESS: "IN_PROGRESS",
  RESOLVED: "RESOLVED",
  CLOSED: "CLOSED",
};

export const TICKET_PRIORITY = Object.freeze([
  { value: "LOW", label: "Low" },
  { value: "MEDIUM", label: "Medium" },
  { value: "HIGH", label: "High" },
]);
