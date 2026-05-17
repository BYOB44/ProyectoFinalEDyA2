import { useState } from 'react';
import { Navigate } from 'react-router-dom';
import { useAuth } from '../../hooks/useAuth.js';
import styles from './Login.module.css';

function Login() {
  const { token, login } = useAuth();
  const [username, setUsername] = useState('admin');
  const [password, setPassword] = useState('admin123');
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);

  if (token) {
    return <Navigate to="/dashboard" replace />;
  }

  async function handleSubmit(event) {
    event.preventDefault();
    try {
      setLoading(true);
      setError('');
      await login(username, password);
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  }

  return (
    <main className={styles.page}>
      <section className={styles.card}>
        <div className={styles.info}>
          <span className={styles.badge}>EDyA II + Facturación</span>
          <h1>Control interno de facturas y pagos</h1>
          <p>Login real con JWT, roles, cola FIFO, heap de prioridad, árbol de departamentos y grafo empresarial.</p>
        </div>

        <form className={styles.form} onSubmit={handleSubmit}>
          <h2>Iniciar sesión</h2>
          <label>
            Usuario
            <input value={username} onChange={(event) => setUsername(event.target.value)} />
          </label>
          <label>
            Contraseña
            <input type="password" value={password} onChange={(event) => setPassword(event.target.value)} />
          </label>
          {error && <p className={styles.error}>{error}</p>}
          <button disabled={loading}>{loading ? 'Ingresando...' : 'Entrar'}</button>

          <div className={styles.users}>
            <strong>Usuarios de prueba</strong>
            <small>admin / admin123</small>
            <small>tesorero / tesorero123</small>
            <small>trabajador / trabajador123</small>
          </div>
        </form>
      </section>
    </main>
  );
}

export default Login;
