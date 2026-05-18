# Documento final del sistema de facturación empresarial

## 1. Nombre del proyecto

**Sistema de Facturación Empresarial con Cola de Pagos**

## 2. Descripción general

>El proyecto consiste en una aplicación web para administrar la facturación interna de una empresa. El sistema permite manejar departamentos, trabajadores, facturas y pagos mediante un flujo organizado. La lógica central del proyecto está en la cola de pagos, donde las facturas pendientes se procesan según una política FIFO o por prioridad.

## 3. Problema que resuelve

>En muchas empresas pequeñas, las facturas, pagos internos, viáticos, comisiones y desprendibles se manejan de forma manual o desordenada. Esto puede generar pérdida de información, pagos duplicados, facturas sin responsable y falta de trazabilidad. Este sistema organiza el proceso y evita que se paguen facturas inexistentes o trabajadores no registrados.



## 4. Roles del sistema

### Administrador Global

>Tiene acceso completo al sistema. Puede gestionar departamentos, trabajadores, facturas, pagos y métricas generales.

### Tesorero / Pagador

>Tiene acceso a facturas y cola de pagos. Su función principal es revisar facturas pendientes y procesar pagos.

### Trabajador

>Tiene acceso limitado. Solo puede ver sus propias facturas o desprendibles asociados.


## 5. Modelo de datos

>- Un departamento puede tener muchos trabajadores.
>- Un departamento puede tener subdepartamentos.
>- Un trabajador puede tener muchas facturas.
>- Una factura puede tener un pago asociado.
>- Un usuario tiene un rol y puede estar asociado a un trabajador.

## 6. Alcance

>El proyecto implementa un MVP funcional con login, roles, gestión de datos principales, cola de pagos, visualización de métricas y uso explícito de estructuras de datos. La base de datos es local para facilitar la ejecución académica.

## 6. Enlaces adicionales

>Figma:https://www.figma.com/design/Om1T1Vq7I7ayJWbUqxMud1/Facturacion_SAJ?node-id=1-3&t=jsrsSnI8KGW8XVJY-1

>Documentacion: https://docs.google.com/document/d/1ibFNL3AGuehmjJPag87AoS2K9Howj3rS3_2K0g9Ag1k/edit?usp=sharing

>Repositorio: https://github.com/BYOB44/ProyectoFinalEDyA2.git