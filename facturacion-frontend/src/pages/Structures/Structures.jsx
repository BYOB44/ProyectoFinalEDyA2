import { useFetch } from '../../hooks/useFetch.js';
import { formatCurrency } from '../../helpers/formatters.js';
import page from '../../styles/page.module.css';
import styles from './Structures.module.css';

function renderTree(nodes) {
  return (
    <ul className={styles.tree}>
      {nodes.map((node) => (
        <li key={node.id}>
          <span>{node.name}</span>
          {node.children?.length > 0 && renderTree(node.children)}
        </li>
      ))}
    </ul>
  );
}

function Structures() {
  const { data, loading, error } = useFetch('/structures/summary');

  if (loading) return <p>Cargando estructuras...</p>;
  if (error) return <p className={page.message}>{error}</p>;

  return (
    <section>
      <div className={page.header}>
        <h2>Estructuras de datos EDyA2</h2>
        <p>Vista explicativa de las 5 estructuras usadas dentro del backend.</p>
      </div>

      <div className={styles.grid}>
        <article className={styles.card}>
          <h3>Queue / Cola FIFO</h3>
          <p>Orden de facturas pendientes según llegada.</p>
          {(data.fifoQueue || []).slice(0, 5).map((item) => <small key={item.id}>{item.code} · {item.workerName}</small>)}
        </article>

        <article className={styles.card}>
          <h3>Heap / Cola de prioridad</h3>
          <p>Orden por prioridad alta, media o baja.</p>
          {(data.priorityHeapOrder || []).slice(0, 5).map((item) => <small key={item.id}>{item.code} · {item.priority} · {formatCurrency(item.amount)}</small>)}
        </article>

        <article className={styles.card}>
          <h3>Stack / Historial</h3>
          <p>Últimos pagos procesados en lógica LIFO.</p>
          {(data.paymentStack || []).length === 0 && <small>Sin pagos procesados todavía.</small>}
          {(data.paymentStack || []).slice(0, 5).map((item) => <small key={item.id}>{item.invoiceCode} · {item.workerName}</small>)}
        </article>

        <article className={styles.card}>
          <h3>Tree / Departamentos</h3>
          <p>Jerarquía organizacional.</p>
          {renderTree(data.departmentTree || [])}
        </article>

        <article className={styles.cardWide}>
          <h3>Graph / Flujo empresarial</h3>
          <p>Conexiones internas entre áreas. Ruta de ejemplo: {(data.examplePath || []).join(' → ')}</p>
          <div className={styles.graphList}>
            {Object.entries(data.companyGraph || {}).map(([node, edges]) => (
              <small key={node}><strong>{node}</strong> → {edges.join(', ') || 'sin salida'}</small>
            ))}
          </div>
        </article>
      </div>
    </section>
  );
}

export default Structures;
