import { useState } from 'react';
import { apiRequest } from '../../helpers/api.js';
import { useFetch } from '../../hooks/useFetch.js';
import Table from '../../components/shared/Table.jsx';
import page from '../../styles/page.module.css';

function Workers() {
  const { data: workers, loading, error, load } = useFetch('/workers');
  const { data: departments } = useFetch('/departments');
  const [form, setForm] = useState({ fullName: '', position: '', email: '', departmentId: '', username: '', password: '' });
  const [message, setMessage] = useState('');

  function updateField(field, value) {
    setForm((current) => ({ ...current, [field]: value }));
  }

  async function handleSubmit(event) {
    event.preventDefault();
    try {
      setMessage('');
      await apiRequest('/workers', {
        method: 'POST',
        body: JSON.stringify({ ...form, departmentId: Number(form.departmentId) })
      });
      setForm({ fullName: '', position: '', email: '', departmentId: '', username: '', password: '' });
      load();
    } catch (err) {
      setMessage(err.message);
    }
  }

  const rows = workers || [];

  return (
    <section>
      <div className={page.header}>
        <h2>Trabajadores</h2>
        <p>Registro del personal y asignación a departamentos.</p>
      </div>

      <form className={page.form} onSubmit={handleSubmit}>
        <label>Nombre completo<input value={form.fullName} onChange={(e) => updateField('fullName', e.target.value)} required /></label>
        <label>Cargo<input value={form.position} onChange={(e) => updateField('position', e.target.value)} required /></label>
        <label>Correo<input type="email" value={form.email} onChange={(e) => updateField('email', e.target.value)} required /></label>
        <label>
          Departamento
          <select value={form.departmentId} onChange={(e) => updateField('departmentId', e.target.value)} required>
            <option value="">Seleccionar</option>
            {(departments || []).map((department) => <option key={department.id} value={department.id}>{department.name}</option>)}
          </select>
        </label>
        <label>Usuario opcional<input value={form.username} onChange={(e) => updateField('username', e.target.value)} /></label>
        <label>Contraseña opcional<input value={form.password} onChange={(e) => updateField('password', e.target.value)} /></label>
        <button>Registrar trabajador</button>
      </form>

      {message && <p className={page.message}>{message}</p>}
      {loading && <p>Cargando...</p>}
      {error && <p className={page.message}>{error}</p>}

      <Table
        columns={[
          { key: 'fullName', label: 'Trabajador' },
          { key: 'position', label: 'Cargo' },
          { key: 'email', label: 'Correo' },
          { key: 'departmentName', label: 'Departamento' }
        ]}
        rows={rows}
      />
    </section>
  );
}

export default Workers;
