# Sistema de Facturación Empresarial - EDyA II
---
## Integrantes:

- Alejandro Molina Lara
- Santiago Gutierrez
- Juan Jose Bernal

---
El proyecto consiste en una aplicación web para administrar la facturación interna de una empresa. El sistema permite manejar departamentos, trabajadores, facturas y pagos mediante un flujo organizado. La lógica central del proyecto está en la cola de pagos, donde las facturas pendientes se procesan según una política FIFO o por prioridad y está dividido en:

- **Frontend:** React + JavaScript + Vite.
- **Backend:** Java + Spring Boot.
- **Base de datos local:** H2 Database.
- **Seguridad:** Login real con JWT y roles.
- **Estructuras de datos:** Queue, Stack, Tree, Heap y Graph.

## Problema que resuelve:

En muchas empresas pequeñas, las facturas, pagos internos, viáticos, comisiones y desprendibles se manejan de forma manual o desordenada. Esto puede generar pérdida de información, pagos duplicados, facturas sin responsable y falta de trazabilidad. Este sistema organiza el proceso y evita que se paguen facturas inexistentes o trabajadores no registrados.

## Modelo de datos:

- Un departamento puede tener muchos trabajadores.
- Un departamento puede tener subdepartamentos.
- Un trabajador puede tener muchas facturas.
- Una factura puede tener un pago asociado.
- Un usuario tiene un rol y puede estar asociado a un trabajador.

---


## Roles del sistema:

| Rol | Permisos principales |
| --- | ---------------------|
| ADMIN | Dashboard, departamentos, trabajadores, facturas, pagos, estructuras |
| TREASURER | Dashboard, facturas, cola de pagos, estructuras |
| WORKER | Ver sus propias facturas |

## Administrador:
Tiene acceso completo al sistema. Puede gestionar departamentos, trabajadores, facturas, pagos y métricas generales.

## Tesorero:
Tiene acceso a facturas y cola de pagos. Su función principal es revisar facturas pendientes y procesar pagos.

## Trabajador:
Tiene acceso limitado. Solo puede ver sus propias facturas o desprendibles asociados.

---

## Credenciales de Prueba / Prueba de Escritorio: 

| Rol | Usuario | Contraseña |
| --- | --- | --- |
| Admin | `admin` | `admin123` |
| Tesorero | `tesorero` | `tesorero123` |
| Trabajador | `trabajador` | `trabajador123` |

---

## Cómo ejecutar el backend:

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

## Cómo ejecutar el frontend:

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

## Estructuras de datos implementadas:

### 1. Queue / Cola:

Archivo:

```txt
facturacion-backend/src/main/java/com/empresa/facturacion/structures/CustomQueue.java
```

Se usa para representar la cola FIFO de pagos pendientes. La primera factura pendiente que entra es la primera que se procesa.

### 2. Stack / Pila:

Archivo:

```txt
facturacion-backend/src/main/java/com/empresa/facturacion/structures/CustomStack.java
```

Se usa para representar el historial reciente de pagos procesados. El último pago realizado aparece primero.

### 3. Tree / Árbol N-ario:

Archivo:

```txt
facturacion-backend/src/main/java/com/empresa/facturacion/structures/DepartmentTree.java
```

Se usa para representar la jerarquía de departamentos y subdepartamentos.

### 4. Heap / Cola de prioridad:

Archivo:

```txt
facturacion-backend/src/main/java/com/empresa/facturacion/structures/PriorityPaymentHeap.java
```

Se usa para procesar facturas según prioridad: nómina y pagos importantes pueden pasar antes que viáticos u otros pagos de menor prioridad.

### 5. Grafo:

Archivo:

```txt
facturacion-backend/src/main/java/com/empresa/facturacion/structures/CompanyGraph.java
```

Se usa para representar conexiones internas entre áreas de la empresa y visualizar rutas de flujo administrativo.

---

## Estructura General / Componentizacion:

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
    ├── public
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
## Alcance del sistema:

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
## Enlaces del proyecto
- Repositorio GitHub: `https://github.com/BYOB44/ProyectoFinalEDyA2#`
---
- Propuesta gráfica / Figma: `https://www.figma.com/design/Om1T1Vq7I7ayJWbUqxMud1/Facturacion_SAJ?node-id=43-116&t=0Cd8a1NLtL6rUwCP-1`
---
- Documentacion: `https://docs.google.com/document/d/1ibFNL3AGuehmjJPag87AoS2K9Howj3rS3_2K0g9Ag1k/edit?usp=sharing`
---
- Enlace a Netlify (Frontend):  `https://facturacionedya2.netlify.app/`
---

