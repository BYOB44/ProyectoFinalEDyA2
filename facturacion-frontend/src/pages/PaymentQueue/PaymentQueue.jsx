import { useEffect, useState } from 'react';
import { apiRequest } from '../../helpers/api.js';
import { formatCurrency, translatePriority, translateType } from '../../helpers/formatters.js';
import Table from '../../components/shared/Table.jsx';
import page from '../../styles/page.module.css';
import styles from './PaymentQueue.module.css';

function PaymentQueue() {
  const [mode, setMode] = useState('fifo');
  const [queue, setQueue] = useState([]);
  const [history, setHistory] = useState([]);
  const [message, setMessage] = useState('');
  const [loading, setLoading] = useState(false);

  async function loadData(selectedMode = mode) {
    try {
      setLoading(true);
      setMessage('');
      const [queueData, historyData] = await Promise.all([
        apiRequest(`/payments/queue?mode=${selectedMode}`),
        apiRequest('/payments/history')
      ]);
      setQueue(queueData);
      setHistory(historyData);
    } catch (err) {
      setMessage(err.message);
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => {
    loadData(mode);
  }, [mode]);

  async function processPayment() {
    try {
      setLoading(true);
      setMessage('');
      const payment = await apiRequest(`/payments/process?mode=${mode}`, { method: 'POST' });
      setMessage(`Pago procesado: ${payment.invoiceCode}`);
      loadData(mode);
    } catch (err) {
      setMessage(err.message);
    } finally {
      setLoading(false);
    }
  }

  const next = queue[0];

  return (
    <section>
      <div className={page.header}>
        <h2>Cola de pagos</h2>
        <p>Motor principal del proyecto: pagos por FIFO o por prioridad usando Heap.</p>
      </div>

      <div className={styles.actions}>
        <button className={mode === 'fifo' ? page.actionButton : page.secondaryButton} onClick={() => setMode('fifo')}>Modo FIFO</button>
        <button className={mode === 'priority' ? page.actionButton : page.secondaryButton} onClick={() => setMode('priority')}>Modo Prioridad</button>
        <button className={page.actionButton} onClick={processPayment} disabled={loading || queue.length === 0}>Procesar siguiente pago</button>
      </div>

      {message && <p className={page.message}>{message}</p>}
      {loading && <p>Cargando cola...</p>}

      {next && (
        <article className={styles.nextCard}>
          <span>Siguiente en la fila</span>
          <h3>{next.workerName}</h3>
          <p>{next.code} · {translateType(next.type)} · Prioridad {translatePriority(next.priority)}</p>
          <strong>{formatCurrency(next.amount)}</strong>
        </article>
      )}

      <div className={page.grid}>
        <div>
          <h3>Facturas en cola</h3>
          <Table
            columns={[
              { key: 'code', label: 'Factura' },
              { key: 'workerName', label: 'Trabajador' },
              { key: 'type', label: 'Tipo', render: (row) => translateType(row.type) },
              { key: 'priority', label: 'Prioridad', render: (row) => translatePriority(row.priority) },
              { key: 'amount', label: 'Monto', render: (row) => formatCurrency(row.amount) }
            ]}
            rows={queue}
          />
        </div>

        <div>
          <h3>Historial de pagos recientes / Stack</h3>
          <Table
            columns={[
              { key: 'invoiceCode', label: 'Factura' },
              { key: 'workerName', label: 'Trabajador' },
              { key: 'amount', label: 'Monto', render: (row) => formatCurrency(row.amount) },
              { key: 'processedBy', label: 'Procesado por' }
            ]}
            rows={history}
            emptyText="Todavía no hay pagos procesados"
          />
        </div>
      </div>
    </section>
  );
}

export default PaymentQueue;
