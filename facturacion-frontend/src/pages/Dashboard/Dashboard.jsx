import StatCard from '../../components/shared/StatCard.jsx';
import { useFetch } from '../../hooks/useFetch.js';
import { formatCurrency } from '../../helpers/formatters.js';
import page from '../../styles/page.module.css';

function Dashboard() {
  const { data, loading, error } = useFetch('/dashboard/stats');

  if (loading) return <p>Cargando métricas...</p>;
  if (error) return <p className={page.message}>{error}</p>;

  return (
    <section>
      <div className={page.header}>
        <h2>Dashboard general</h2>
        <p>Resumen operativo de departamentos, trabajadores, facturas pendientes y pagos procesados.</p>
      </div>

      <div className={page.statsGrid}>
        <StatCard title="Departamentos" value={data.departments} note="Árbol organizacional" />
        <StatCard title="Trabajadores" value={data.workers} note="Personal registrado" />
        <StatCard title="Facturas pendientes" value={data.pendingInvoices} note={formatCurrency(data.pendingAmount)} />
        <StatCard title="Facturas pagadas" value={data.paidInvoices} note={formatCurrency(data.paidAmount)} />
        <StatCard title="Estructura principal" value="Queue" note="Motor de pagos FIFO" />
        <StatCard title="Modo alterno" value="Heap" note="Pagos por prioridad" />
      </div>
    </section>
  );
}

export default Dashboard;
