<<<<<<< HEAD
# Sistema de Facturación Empresarial - EDyA II

Proyecto académico que simula una empresa que necesita gestionar departamentos, trabajadores, facturas y una cola de pagos. El sistema está dividido en:

- **Frontend:** React + JavaScript + Vite.
- **Backend:** Java + Spring Boot.
- **Base de datos local:** H2 Database.
- **Seguridad:** Login real con JWT y roles.
- **Estructuras de datos:** Queue, Stack, Tree, Heap y Graph.

---

## Integrantes

- Alejandro Molina Lara
- Santiago gutierrez
- Juan Jose Bernal

## Enlaces del proyecto

- Repositorio GitHub: `https://github.com/BYOB44/ProyectoFinalEDyA2#`
---
- Propuesta gráfica / Figma: `agregar enlace`

---

## Roles del sistema

| Rol | Permisos principales |
| --- | ---------------------|
| ADMIN | Dashboard, departamentos, trabajadores, facturas, pagos, estructuras |
| TREASURER | Dashboard, facturas, cola de pagos, estructuras |
| WORKER | Ver sus propias facturas |

---

## Usuarios de prueba

| Rol | Usuario | Contraseña |
| --- | --- | --- |
| Admin | `admin` | `admin123` |
| Tesorero | `tesorero` | `tesorero123` |
| Trabajador | `trabajador` | `trabajador123` |

---

## Cómo ejecutar el backend

```bash
cd facturacion-backend
mvn spring-boot:run
```

El backend queda disponible en:

```txt
http://localhost:8080
```

Consola H2:

```txt
http://localhost:8080/h2-console
```

Datos H2:

```txt
JDBC URL: jdbc:h2:mem:facturaciondb
User: sa
Password: password
```

---

## Cómo ejecutar el frontend

```bash
cd facturacion-frontend
npm install
npm run dev
```

El frontend queda disponible normalmente en:

```txt
http://localhost:5173
```

---

## Estructuras de datos implementadas

### 1. Queue / Cola

Archivo:

```txt
facturacion-backend/src/main/java/com/empresa/facturacion/structures/CustomQueue.java
```

Se usa para representar la cola FIFO de pagos pendientes. La primera factura pendiente que entra es la primera que se procesa.

### 2. Stack / Pila

Archivo:

```txt
facturacion-backend/src/main/java/com/empresa/facturacion/structures/CustomStack.java
```

Se usa para representar el historial reciente de pagos procesados. El último pago realizado aparece primero.

### 3. Tree / Árbol N-ario

Archivo:

```txt
facturacion-backend/src/main/java/com/empresa/facturacion/structures/DepartmentTree.java
```

Se usa para representar la jerarquía de departamentos y subdepartamentos.

### 4. Heap / Cola de prioridad

Archivo:

```txt
facturacion-backend/src/main/java/com/empresa/facturacion/structures/PriorityPaymentHeap.java
```

Se usa para procesar facturas según prioridad: nómina y pagos importantes pueden pasar antes que viáticos u otros pagos de menor prioridad.

### 5. Grafo

Archivo:

```txt
facturacion-backend/src/main/java/com/empresa/facturacion/structures/CompanyGraph.java
```

Se usa para representar conexiones internas entre áreas de la empresa y visualizar rutas de flujo administrativo.

---

## Estructura general

```txt
facturacion-empresa/
├── facturacion-backend/
│   ├── pom.xml
│   └── src/main/java/com/empresa/facturacion/
│       ├── config/
│       ├── controller/
│       ├── dto/
│       ├── model/
│       ├── repository/
│       ├── service/
│       └── structures/
└── facturacion-frontend/
    ├── package.json
    └── src/
        ├── components/
        ├── context/
        ├── helpers/
        ├── hooks/
        ├── pages/
        ├── routes/
        └── styles/
```

---

## Alcance del sistema

El sistema permite:

- Login con roles.
- Menú privado según rol.
- Gestión de departamentos.
- Gestión de trabajadores.
- Creación de facturas.
- Consulta de facturas pendientes y pagadas.
- Cola FIFO de pagos.
- Cola de prioridad con Heap.
- Procesamiento de pagos.
- Vista de estructuras de datos usadas en el proyecto.
- Base de datos local H2.

---

=======
# ProyectoFinalEDyA2
En este repositorio se realizara el proyecto final de EDyA2 
>>>>>>> acadb66bc5b0f4c6832fb63d1ada2db8e97c9499
