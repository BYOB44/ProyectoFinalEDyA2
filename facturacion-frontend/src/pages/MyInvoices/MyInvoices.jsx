import { useFetch } from '../../hooks/useFetch.js';
import { formatCurrency, translatePriority, translateStatus, translateType } from '../../helpers/formatters.js';
import Table from '../../components/shared/Table.jsx';
import page from '../../styles/page.module.css';

function MyInvoices() {
  const { data, loading, error } = useFetch('/invoices/my');
  const rows = data || [];

  return (
    <section>
      <div className={page.header}>
        <h2>Mis facturas</h2>
        <p>Consulta personal de facturas asociadas al trabajador autenticado.</p>
      </div>

      {loading && <p>Cargando...</p>}
      {error && <p className={page.message}>{error}</p>}

      <Table
        columns={[
          { key: 'code', label: 'Código' },
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

export default MyInvoices;
