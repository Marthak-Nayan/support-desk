import { Route, Routes } from 'react-router-dom';
import './App.css';
import Dashboard from './pages/DashBoard';
import Login from './auth/login';
import Signup from './auth/signup';
import NotFound from './components/NotFound';
import { ToastContainer } from 'react-toastify';
function App() {
  return (
    <div className="min-h-screen items-center justify-center bg-gray-100">
      <ToastContainer
        position="bottom-right"
        autoClose={6000}
        newestOnTop
        closeOnClick
        pauseOnHover
        draggable
        theme="light"
      />
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="*" element={<NotFound />} />
      </Routes>
    </div>
  );
}

export default App;
