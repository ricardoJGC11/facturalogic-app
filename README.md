# FacturaLogic - Core Engine

**FacturaLogic** es un motor de facturación desarrollado en **Java 17** con **Maven**. Implementa una arquitectura multicapa para separar la lógica de negocio, el acceso a datos y los modelos del sistema. Utiliza **MySQL** como base de datos y maneja transacciones mediante **Commit** y **Rollback** para garantizar la integridad de la información.

---

## Tecnologías utilizadas

- Java 17
- Maven
- MySQL 8+
- JDBC

---

## Arquitectura del proyecto

El proyecto está organizado en las siguientes capas:

```
src/
├── config/        # Configuración de la conexión a MySQL
├── model/         # Entidades del sistema (POJOs)
├── repository/    # Acceso a datos (DAO)
├── service/       # Lógica de negocio
└── App.java       # Punto de entrada
```

### Descripción de cada capa

- **config:** administra la conexión con la base de datos.
- **model:** contiene las entidades del sistema (`Producto`, `Cliente`, `Factura` y `DetalleFactura`).
- **repository:** realiza las operaciones CRUD y consultas SQL utilizando `PreparedStatement` para mayor seguridad.
- **service:** implementa la lógica de negocio, validaciones, cálculos de facturación y control de inventario.

---

## Base de datos

El sistema utiliza una base de datos MySQL con las siguientes tablas:

- **clientes**
- **productos**
- **facturas**
- **detalles_factura**

Estas tablas permiten administrar clientes, productos, ventas y el detalle de cada factura.

---

## Requisitos

Antes de ejecutar el proyecto asegúrate de tener instalado:

- Java JDK 17 o superior
- Apache Maven 3.9+
- MySQL Server 8+
- Git (opcional para clonar el repositorio)

---

## Instalación

### 1. Clonar el repositorio

```bash
git clone https://github.com/ricardoJGC11/facturalogic-app.git
cd facturalogic-app
```

### 2. Configurar la base de datos

1. Crear una base de datos llamada:

```sql
factura_db
```

2. Ejecutar el script SQL incluido en el proyecto para crear las tablas.

3. Configurar las credenciales de conexión en la clase correspondiente (`ConexionDB.java` o el archivo de configuración).

---

### 3. Compilar el proyecto

```bash
mvn clean compile
```

---

### 4. Ejecutar la aplicación

```bash
mvn exec:java "-Dexec.mainClass=com.facturalogic.App"
```

---

## Características

- Arquitectura multicapa.
- CRUD completo para las entidades principales.
- Control de inventario.
- Registro de facturas y detalles.
- Cálculo automático de subtotales y totales.
- Transacciones seguras con Commit y Rollback.
- Consultas SQL parametrizadas para prevenir SQL Injection.

---

## Autor

**Ricardo JGC**

GitHub: https://github.com/ricardoJGC11