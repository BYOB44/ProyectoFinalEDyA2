import { useEffect, useState } from 'react';
import { apiRequest } from '../../helpers/api.js';
import { useFetch } from '../../hooks/useFetch.js';
import Table from '../../components/shared/Table.jsx';
import page from '../../styles/page.module.css';

function Departments() {
  const { data, loading, error, load } = useFetch('/departments');
  const [name, setName] = useState('');
  const [parentId, setParentId] = useState('');
  const [message, setMessage] = useState('');

  async function handleSubmit(event) {
    event.preventDefault();
    try {
      setMessage('');
      await apiRequest('/departments', {
        method: 'POST',
        body: JSON.stringify({ name, parentId: parentId ? Number(parentId) : null })
      });
      setName('');
      setParentId('');
      load();
    } catch (err) {
      setMessage(err.message);
    }
  }

  const rows = data || [];

  return (
    <section>
      <div className={page.header}>
        <h2>Departamentos</h2>
        <p>Permite construir la jerarquía de áreas de la empresa mediante un árbol N-ario.</p>
      </div>

      <form className={page.form} onSubmit={handleSubmit}>
        <label>
          Nombre
          <input value={name} onChange={(event) => setName(event.target.value)} placeholder="Ej: Contabilidad" required />
        </label>
        <label>
          Departamento padre
          <select value={parentId} onChange={(event) => setParentId(event.target.value)}>
            <option value="">Sin padre</option>
            {rows.map((department) => (
              <option key={department.id} value={department.id}>{department.name}</option>
            ))}
          </select>
        </label>
        <button>Crear departamento</button>
      </form>

      {message && <p className={page.message}>{message}</p>}
      {loading && <p>Cargando...</p>}
      {error && <p className={page.message}>{error}</p>}

      <Table
        columns={[
          { key: 'id', label: 'ID' },
          { key: 'name', label: 'Departamento' },
          { key: 'parentName', label: 'Padre', render: (row) => row.parentName || 'Principal' }
        ]}
        rows={rows}
      />
    </section>
  );
}

export default Departments;
