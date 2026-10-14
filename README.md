# 📱 TecnoStore - Sistema de Gestión y Ventas de Telefonía Móvil

**TecnoStore** es una aplicación de consola desarrollada en **Java** bajo el patrón de arquitectura **MVC (Modelo-Vista-Controlador)** y el patrón de diseño **DAO**. El sistema integra una base de datos relacional **MySQL** para la gestión transaccional, **persistencia dual en archivos binarios (`.dat`)** para respaldos y auditorías, y potentes consultas analíticas implementadas mediante la **Java Stream API**.

---

## 🚀 Características Principales

* **Autenticación de Vendedores**: Sistema de login seguro conectado a base de datos con registro automático de intentos en la bitácora de auditoría.
* **Gestión Integral de Inventario**: Administración completa (CRUD) de celulares, marcas, gamas, sistemas operativos y vendedores.
* **Proceso Avanzado de Ventas**: 
  * Validación en tiempo real de stock disponible.
  * Registro rápido de clientes nuevos.
  * Cálculo automático de subtotales, IVA (19%) y total general.
  * Generación de factura detallada en consola.
* **Persistencia en Archivos Binarios (`.dat`)**:
  * Respaldo histórico de ventas (`ventas_tecnostore.dat`).
  * Bitácora de auditoría de acciones del sistema (`historial_acciones.dat`) que registra logins, accesos a menús, creación de clientes y ventas.
* **Módulo de Reportes y Analítica (Stream API)**: Procesamiento funcional de datos para obtener:
  * Celulares con stock bajo ($\le 5$ unidades).
  * Top 3 de celulares más vendidos.
  * Ventas totales agrupadas por mes.
  * Filtros dinámicos de ventas e historial de auditoría mediante expresiones lambda y flujos de datos (`Stream`).

---

## 🛠️ Tecnologías y Herramientas Utilizadas

* **Lenguaje:** Java (JDK 17 o superior recomendado).
* **Base de Datos:** MySQL / JDBC (`ConexionDB`).
* **Paradigmas:** Programación Orientada a Objetos (POO), Programación Funcional (Stream API).
* **Persistencia:** Serialización de objetos Java para archivos binarios `.dat`.
* **Arquitectura:** MVC, Patrón DAO.

---

## 📂 Estructura del Proyecto

El código fuente está modularizado en los siguientes paquetes principales:

```text
TecnoStore/
│
├── config/              # Configuración de conexión a la Base de Datos (ConexionDB)
├── controller/          # Controladores de lógica de negocio y menús
│   ├── Celular/         # Gestión de menús de celulares
│   ├── Gama/            # Gestión de gamas
│   ├── Logueo/          # Control de autenticación (LogueoController)
│   ├── Marca/           # Gestión de marcas
│   ├── Reporte/         # Procesamiento analítico y Stream API (ReporteController)
│   ├── SistemaOperativo/# Gestión de sistemas operativos
│   ├── Venta/           # Lógica de transacciones comerciales (VentaController)
│   └── Vendedor/        # Gestión de personal de ventas
│
├── dao/                 # Interfaces de Acceso a Datos
│   └── impl/            # Implementaciones DAO conectadas a MySQL (CelularDaoImpl, VentaDaoImpl, etc.)
│
├── model/               # Clases POJO / Entidades y Gestores de Archivos .dat
│   ├── AccionLog.java         # Modelo para la bitácora de auditoría
│   ├── AuditoriaArchivo.java  # Lógica de lectura/escritura de historial_acciones.dat
│   ├── DatosVentaArchivo.java # Lógica de lectura/escritura de ventas_tecnostore.dat
│   └── VentaRegistro.java     # Modelo de registro binario de ventas
│
└── view/                # Capa de presentación en consola y validadores
    ├── validarNumeros/  # Validaciones robustas para entradas numéricas
    └── validarTexto/    # Validaciones para cadenas de texto y correos