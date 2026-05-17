import styles from './StatCard.module.css';

function StatCard({ title, value, note }) {
  return (
    <article className={styles.card}>
      <span>{title}</span>
      <strong>{value}</strong>
      {note && <small>{note}</small>}
    </article>
  );
}

export default StatCard;