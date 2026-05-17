import { LogOut } from 'lucide-react';
import { useAuth } from '../../hooks/useAuth.js';
import styles from './Topbar.module.css';

function Topbar() {
  const { user, logout } = useAuth();

  return (
    <header className={styles.topbar}>
      <div>
        <h1>facturación-SAJ</h1>
        <p>Gestión de trabajadores, facturas y cola de pagos</p>
      </div>
      <div className={styles.userBox}>
        <span>{user?.username}</span>
        <small>{user?.role}</small>
        <button onClick={logout} title="Cerrar sesión">
          <LogOut size={18} />
        </button>
      </div>
    </header>
  );
}

export default Topbar;
