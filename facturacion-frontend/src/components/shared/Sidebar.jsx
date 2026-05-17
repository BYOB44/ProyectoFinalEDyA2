import { NavLink } from 'react-router-dom';
import { Building2, CreditCard, FileText, LayoutDashboard, Network, Users, WalletCards } from 'lucide-react';
import { useAuth } from '../../hooks/useAuth.js';
import styles from './Sidebar.module.css';

function Sidebar() {
  const { user } = useAuth();

  const items = [
    { to: '/dashboard', label: 'Dashboard', icon: LayoutDashboard, roles: ['ADMIN', 'TREASURER'] },
    { to: '/departments', label: 'Departamentos', icon: Building2, roles: ['ADMIN'] },
    { to: '/workers', label: 'Trabajadores', icon: Users, roles: ['ADMIN'] },
    { to: '/invoices', label: 'Facturas', icon: FileText, roles: ['ADMIN', 'TREASURER'] },
    { to: '/payments', label: 'Cola de pagos', icon: WalletCards, roles: ['ADMIN', 'TREASURER'] },
    { to: '/my-invoices', label: 'Mis facturas', icon: CreditCard, roles: ['WORKER'] },
    { to: '/structures', label: 'Estructuras EDyA2', icon: Network, roles: ['ADMIN', 'TREASURER'] }
  ];

  return (
    <aside className={styles.sidebar}>
      <div className={styles.brand}>
        <span className={styles.logo}>FE</span>
        <div>
          <strong>FacturaEmpresa</strong>
          <small>Pagos internos</small>
        </div>
      </div>

      <nav className={styles.nav}>
        {items
          .filter((item) => item.roles.includes(user?.role))
          .map((item) => {
            const Icon = item.icon;
            return (
              <NavLink key={item.to} to={item.to} className={({ isActive }) => `${styles.link} ${isActive ? styles.active : ''}`}>
                <Icon size={18} />
                <span>{item.label}</span>
              </NavLink>
            );
          })}
      </nav>
    </aside>
  );
}

export default Sidebar;
