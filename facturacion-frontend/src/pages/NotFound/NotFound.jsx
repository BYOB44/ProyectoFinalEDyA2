import { Link } from 'react-router-dom';
import page from '../../styles/page.module.css';

function NotFound() {
  return (
    <section className={page.panel}>
      <h2>Página no encontrada</h2>
      <p>La ruta que intentaste abrir no existe.</p>
      <Link to="/dashboard">Volver al dashboard</Link>
    </section>
  );
}

export default NotFound;
