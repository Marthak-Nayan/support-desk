const WelcomeCard = ({ role }) => (
  <div className="bg-white rounded-xl shadow p-6">
    <h2 className="text-xl font-semibold mb-2">Welcome 👋</h2>
    <p className="text-gray-600">
      Logged in as <b>{role}</b>
    </p>
  </div>
);

export default WelcomeCard;