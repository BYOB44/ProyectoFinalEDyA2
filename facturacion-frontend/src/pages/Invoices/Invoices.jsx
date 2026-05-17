import { useState } from 'react';
import { apiRequest } from '../../helpers/api.js';
import { useFetch } from '../../hooks/useFetch.js';
import { formatCurrency, translatePriority, translateStatus, translateType } from '../../helpers/formatters.js';
import Table from '../../components/shared/Table.jsx';
import page from '../../styles/page.module.css';

function Invoices() {
  const { data: invoices, loading, error, load } = useFetch('/invoices');
  const { data: workers } = useFetch('/workers');
  const [form, setForm] = useState({ description: '', amount: '', type: 'NOMINA', priority: 'HIGH', workerId: '' });
  const [message, setMessage] = useState('');

  function updateField(field, value) {
    setForm((current) => ({ ...current, [field]: value }));
  }

  async function handleSubmit(event) {
    event.preventDefault();
    try {
      setMessage('');
      await apiRequest('/invoices', {
        method: 'POST',
        body: JSON.stringify({ ...form, amount: Number(form.amount), workerId: Number(form.workerId) })
      });
      setForm({ description: '', amount: '', type: 'NOMINA', priority: 'HIGH', workerId: '' });
      load();
    } catch (err) {
      setMessage(err.message);
    }
  }

  const rows = invoices || [];

  return (
    <section>
      <div className={page.header}>
        <h2>Facturas</h2>
        <p>Las facturas nacen en estado pendiente y luego entran a la cola de pagos.</p>
      </div>

      <form className={page.form} onSubmit={handleSubmit}>
        <label>Descripción<input value={form.description} onChange={(e) => updateField('description', e.target.value)} required /></label>
        <label>Monto<input type="number" value={form.amount} onChange={(e) => updateField('amount', e.target.value)} required /></label>
        <label>
          Tipo
          <select value={form.type} onChange={(e) => updateField('type', e.target.value)}>
            <option value="NOMINA">Nómina</option>
            <option value="VIATICOS">Viáticos</option>
            <option value="COMISION">Comisión</option>
            <option value="SERVICIO">Servicio</option>
            <option value="OTRO">Otro</option>
          </select>
        </label>
        <label>
          Prioridad
          <select value={form.priority} onChange={(e) => updateField('priority', e.target.value)}>
            <option value="HIGH">Alta</option>
            <option value="MEDIUM">Media</option>
            <option value="LOW">Baja</option>
          </select>
        </label>
        <label>
          Trabajador
          <select value={form.workerId} onChange={(e) => updateField('workerId', e.target.value)} required>
            <option value="">Seleccionar</option>
            {(workers || []).map((worker) => <option key={worker.id} value={worker.id}>{worker.fullName}</option>)}
          </select>
        </label>
        <button>Crear factura</button>
      </form>

      {message && <p className={page.message}>{message}</p>}
      {loading && <p>Cargando...</p>}
      {error && <p className={page.message}>{error}</p>}

      <Table
        columns={[
          { key: 'code', label: 'Código' },
          { key: 'workerName', label: 'Trabajador' },
          { key: 'description', label: 'Descripción' },
          { key: 'amount', label: 'Monto', render: (row) => formatCurrency(row.amount) },
          { key: 'type', label: 'Tipo', render: (row) => translateType(row.type) },
          { key: 'priority', label: 'Prioridad', render: (row) => translatePriority(row.priority) },
          { key: 'status', label: 'Estado', render: (row) => <span className={row.status === 'PAID' ? page.success : page.warning}>{translateStatus(row.status)}</span> }
        ]}
        rows={rows}
      />
    </section>
  );
}

export default Invoices;
