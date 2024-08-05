import logo from './logo.svg';
import './App.css';
import Register from './screens/Register';
import { Route, Routes } from 'react-router-dom';
import Login from './screens/Login';
import Home from './screens/Home';
import CreateConnection from './screens/CreateConnection';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import ConnectionList from './screens/ConnectionList';
import ConnectionView from './screens/ConnectionView';
import BookGas from './screens/BookGas';
import PaymentPage from './screens/PaymentPage';
import OrderHistory from './screens/OrderHistory';
import FeedbackForm from './screens/Feedback';
import ProtectedRoute from './components/ProtectedRoute';

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/register" element={<Register />} />
        <Route path="/login" element={<Login />} />
        <Route path="/home" element={<Home />} />
        <Route path="/create-connection" element={<CreateConnection />} />
        <Route path="/connection-view" element={<ProtectedRoute component={ConnectionView} />} />
        <Route path="/connections" element={<ProtectedRoute component={ConnectionList} />} />
        <Route path="/book-gas" element={<ProtectedRoute component={BookGas} />} />
        <Route path="/payment" element={<ProtectedRoute component={PaymentPage} />} />
        <Route path="/order-history" element={<ProtectedRoute component={OrderHistory} />} />
        <Route path="/feedback" element={<FeedbackForm />} />
        {/* Add more routes here as needed */}
        <Route path="/" element={<Home />} />
      </Routes>
      <ToastContainer />
    </div>
  );
}

export default App;
