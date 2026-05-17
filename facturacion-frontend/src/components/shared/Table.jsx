import styles from './Table.module.css';

function Table({ columns, rows, emptyText = 'No hay datos para mostrar' }) {
  return (
    <div className={styles.tableBox}>
      <table>
        <thead>
          <tr>
            {columns.map((column) => (
              <th key={column.key}>{column.label}</th>
            ))}
          </tr>
        </thead>
        <tbody>
          {rows.length === 0 && (
            <tr>
              <td colSpan={columns.length} className={styles.empty}>{emptyText}</td>
            </tr>
          )}
          {rows.map((row, index) => (
            <tr key={row.id || row.code || index}>
              {columns.map((column) => (
                <td key={column.key}>{column.render ? column.render(row) : row[column.key]}</td>
              ))}
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default Table;
