export function formatCurrency(value) {
  const number = Number(value || 0);
  return new Intl.NumberFormat('es-CO', {
    style: 'currency',
    currency: 'COP',
    maximumFractionDigits: 0
  }).format(number);
}

export function translateStatus(status) {
  const values = {
    PENDING: 'Pendiente',
    PAID: 'Pagado'
  };
  return values[status] || status;
}

export function translatePriority(priority) {
  const values = {
    HIGH: 'Alta',
    MEDIUM: 'Media',
    LOW: 'Baja'
  };
  return values[priority] || priority;
}

export function translateType(type) {
  const values = {
    NOMINA: 'Nómina',
    VIATICOS: 'Viáticos',
    COMISION: 'Comisión',
    SERVICIO: 'Servicio',
    OTRO: 'Otro'
  };
  return values[type] || type;
}
