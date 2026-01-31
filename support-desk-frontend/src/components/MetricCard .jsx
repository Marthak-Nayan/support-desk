const MetricCard = ({ label, value }) => (
  <div className="bg-white rounded-xl shadow p-6 text-center">
    <p className="text-gray-500">{label}</p>
    <h2 className="text-3xl font-bold">{value}</h2>
  </div>
);

export default MetricCard;