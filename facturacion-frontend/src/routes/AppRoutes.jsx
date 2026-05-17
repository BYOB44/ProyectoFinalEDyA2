import { Navigate, Route, Routes } from 'react-router-dom';
import Login from '../pages/Login/Login.jsx';
import Dashboard from '../pages/Dashboard/Dashboard.jsx';
import Departments from '../pages/Departments/Departments.jsx';
import Workers from '../pages/Workers/Workers.jsx';
import Invoices from '../pages/Invoices/Invoices.jsx';
import PaymentQueue from '../pages/PaymentQueue/PaymentQueue.jsx';
import MyInvoices from '../pages/MyInvoices/MyInvoices.jsx';
import Structures from '../pages/Structures/Structures.jsx';
import NotFound from '../pages/NotFound/NotFound.jsx';
import PrivateLayout from '../components/layout/PrivateLayout.jsx';
import PrivateRoute from './PrivateRoute.jsx';
import RoleRoute from './RoleRoute.jsx';

function AppRoutes() {
  return (
    <Routes>
      <Route path="/login" element={<Login />} />

      <Route element={<PrivateRoute />}>
        <Route element={<PrivateLayout />}>
          <Route path="/" element={<Navigate to="/dashboard" replace />} />
          <Route path="/dashboard" element={<RoleRoute roles={["ADMIN", "TREASURER"]}><Dashboard /></RoleRoute>} />
          <Route path="/departments" element={<RoleRoute roles={["ADMIN"]}><Departments /></RoleRoute>} />
          <Route path="/workers" element={<RoleRoute roles={["ADMIN"]}><Workers /></RoleRoute>} />
          <Route path="/invoices" element={<RoleRoute roles={["ADMIN", "TREASURER"]}><Invoices /></RoleRoute>} />
          <Route path="/payments" element={<RoleRoute roles={["ADMIN", "TREASURER"]}><PaymentQueue /></RoleRoute>} />
          <Route path="/my-invoices" element={<RoleRoute roles={["WORKER"]}><MyInvoices /></RoleRoute>} />
          <Route path="/structures" element={<RoleRoute roles={["ADMIN", "TREASURER"]}><Structures /></RoleRoute>} />
        </Route>
      </Route>

      <Route path="*" element={<NotFound />} />
    </Routes>
  );
}

export default AppRoutes;
