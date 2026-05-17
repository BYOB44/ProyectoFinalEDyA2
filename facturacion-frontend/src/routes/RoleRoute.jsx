import { Navigate } from 'react-router-dom';
import { useAuth } from '../hooks/useAuth.js';

function RoleRoute({ roles, children }) {
  const { user } = useAuth();

  if (!user) {
    return <Navigate to="/login" replace />;
  }

  if (!roles.includes(user.role)) {
    return <Navigate to={user.role === 'WORKER' ? '/my-invoices' : '/dashboard'} replace />;
  }

  return children;
}

export default RoleRoute;
