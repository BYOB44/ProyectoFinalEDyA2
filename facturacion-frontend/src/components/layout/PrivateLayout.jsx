import { Outlet } from 'react-router-dom';
import Sidebar from '../shared/Sidebar.jsx';
import Topbar from '../shared/Topbar.jsx';
import styles from './PrivateLayout.module.css';

function PrivateLayout() {
  return (
    <div className={styles.layout}>
      <Sidebar />
      <main className={styles.main}>
        <Topbar />
        <section className={styles.content}>
          <Outlet />
        </section>
      </main>
    </div>
  );
}

export default PrivateLayout;
