import { createContext, useEffect, useMemo, useState } from 'react';
import { loginRequest } from '../helpers/api.js';

export const AuthContext = createContext(null);

export function AuthProvider({ children }) {
  const [token, setToken] = useState(() => localStorage.getItem('token'));
  const [user, setUser] = useState(() => {
    const stored = localStorage.getItem('user');
    return stored ? JSON.parse(stored) : null;
  });

  useEffect(() => {
    if (token) {
      localStorage.setItem('token', token);
    } else {
      localStorage.removeItem('token');
    }
  }, [token]);

  useEffect(() => {
    if (user) {
      localStorage.setItem('user', JSON.stringify(user));
    } else {
      localStorage.removeItem('user');
    }
  }, [user]);

  async function login(username, password) {
    const data = await loginRequest(username, password);
    setToken(data.token);
    setUser({ username: data.username, role: data.role, workerId: data.workerId });
  }

  function logout() {
    setToken(null);
    setUser(null);
  }

  function hasRole(roles) {
    if (!user) return false;
    return roles.includes(user.role);
  }

  const value = useMemo(() => ({ token, user, login, logout, hasRole }), [token, user]);

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}
