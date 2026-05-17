# Documento final del sistema de facturación empresarial

## 1. Nombre del proyecto

**Sistema de Facturación Empresarial con Cola de Pagos**

## 2. Descripción general

El proyecto consiste en una aplicación web para administrar la facturación interna de una empresa. El sistema permite manejar departamentos, trabajadores, facturas y pagos mediante un flujo organizado. La lógica central del proyecto está en la cola de pagos, donde las facturas pendientes se procesan según una política FIFO o por prioridad.

## 3. Problema que resuelve

En muchas empresas pequeñas, las facturas, pagos internos, viáticos, comisiones y desprendibles se manejan de forma manual o desordenada. Esto puede generar pérdida de información, pagos duplicados, facturas sin responsable y falta de trazabilidad. Este sistema organiza el proceso y evita que se paguen facturas inexistentes o trabajadores no registrados.

## 4. Tecnologías utilizadas

- React con JavaScript para el frontend.
- Vite como herramienta de desarrollo frontend.
- Java con Spring Boot para el backend.
- Spring Security para autenticación y autorización.
- JWT para proteger las rutas privadas.
- Spring Data JPA para persistencia.
- H2 Database como base de datos local.
- CSS Modules para organizar los estilos por componente o página.

## 5. Roles del sistema

### Administrador Global

Tiene acceso completo al sistema. Puede gestionar departamentos, trabajadores, facturas, pagos y métricas generales.

### Tesorero / Pagador

Tiene acceso a facturas y cola de pagos. Su función principal es revisar facturas pendientes y procesar pagos.

### Trabajador

Tiene acceso limitado. Solo puede ver sus propias facturas o desprendibles asociados.

## 6. Flujo operativo

Primero, el administrador crea la estructura organizacional de la empresa mediante departamentos. Luego registra trabajadores y los asigna a un departamento. Después, el sistema permite crear facturas asociadas a cada trabajador. Las facturas nacen con estado pendiente y posteriormente entran a una cola de pagos. El tesorero puede procesar el siguiente pago según el modo FIFO o según prioridad. Cuando se procesa el pago, la factura cambia a estado pagado y se crea el comprobante de pago.

## 7. Modelo de datos

- Un departamento puede tener muchos trabajadores.
- Un departamento puede tener subdepartamentos.
- Un trabajador puede tener muchas facturas.
- Una factura puede tener un pago asociado.
- Un usuario tiene un rol y puede estar asociado a un trabajador.

## 8. Estructuras de datos utilizadas

### Queue

Se utiliza para la cola FIFO de pagos. Representa el caso en el que las facturas se pagan en el mismo orden en que fueron generadas.

### Stack

Se utiliza para mostrar el historial reciente de pagos. Como una pila trabaja con lógica LIFO, el último pago realizado aparece primero.

### Arbol

Se utiliza para representar la jerarquía de departamentos. Un departamento principal puede tener varios subdepartamentos, por eso se usa un árbol N-ario.

### Heap

Se utiliza para implementar una cola de prioridad. Las facturas con prioridad alta pueden ser procesadas antes que las facturas de prioridad media o baja.

### Grafos

Se utiliza para representar conexiones internas entre áreas de la empresa. Esto permite modelar rutas de flujo administrativo entre departamentos como administración, recursos humanos, tesorería y pagos.

## 9. Alcance

El proyecto implementa un MVP funcional con login, roles, gestión de datos principales, cola de pagos, visualización de métricas y uso explícito de estructuras de datos. La base de datos es local para facilitar la ejecución académica.


