
# 🦉 OWL Backend

## 📘 Descripción General
**OWL** es una plataforma diseñada para ayudar a los inversionistas a encontrar, comparar y analizar **Fondos de Inversión Colectiva (FICs)** de manera sencilla y centralizada.  

El módulo **owl-backend** constituye el núcleo del sistema, desarrollado en **Spring Boot (Java 17)**. Se encarga de gestionar la lógica de negocio, la persistencia de datos y la exposición de servicios REST que son consumidos por el frontend y otros módulos del sistema.

---

## 🏗️ Arquitectura General
El backend sigue una **arquitectura por capas**, lo que garantiza una separación clara de responsabilidades y un mantenimiento ágil:

- **Model:** Contiene las entidades JPA que representan el dominio del sistema (Usuario, Fic, Características, Composición, Calificación, etc.).
- **Repository:** Interfaces que gestionan el acceso y persistencia de datos mediante Spring Data JPA.
- **Service:** Implementa la lógica de negocio, incluyendo la recomendación de FICs según el nivel de riesgo del usuario.
- **Controller:** Expone los endpoints REST que comunican el backend con el frontend.

---

## ⚙️ Tecnologías Clave
- **Spring Boot / Java 17**
- **Spring Data JPA / Hibernate**
- **DTOs** para transferencia de datos
- **JWT (JSON Web Token)** para autenticación
- **BCrypt** para cifrado de contraseñas
- **PostgreSQL** (producción) y **H2** (desarrollo y pruebas)

---

## 💾 Bases de Datos
- **Producción:** PostgreSQL  
- **Desarrollo y pruebas:** H2 (en memoria)

El sistema incluye un inicializador de datos que crea usuarios y FICs de ejemplo al iniciar la aplicación, permitiendo su uso inmediato sin configuración adicional.

---

## 🔐 Seguridad
El sistema cuenta con dos roles principales:
- **ADMIN:** Acceso total al sistema.
- **USER:** Acceso restringido a operaciones específicas.

Las contraseñas se almacenan cifradas y la autenticación se maneja mediante **JWT**, asegurando sesiones sin estado y protección en las peticiones a los endpoints.

---

## 📊 Lógica de Recomendación
El backend recomienda FICs a los usuarios con base en su **nivel de riesgo**, obtenido a partir de un **cuestionario**.  
Luego, compara dicho nivel con el **tipo de FIC** (Renta Fija, Mixta, Alternativa o Desconocida) para ofrecer sugerencias personalizadas y coherentes con el perfil del inversionista.

---

## 🔗 Módulos del Sistema
El sistema OWL está compuesto por los siguientes módulos:

1. **owl-backend:** API REST para gestión de usuarios, FICs y recomendaciones.  
2. **Web Scraping:** Obtiene automáticamente los datos de FICs desde fuentes oficiales.  
3. **ETL:** Transforma y carga los datos hacia la base de datos del backend.  
4. **Frontend:** Interfaz de usuario que consume la API REST del backend.

---

## 🚀 Ejecución y Configuración Local

### Requisitos Previos
- Java 17+
- Maven 3.6+
- PostgreSQL (para entorno de producción)

### Configuración de Base de Datos
Editar el archivo `application.properties` según el entorno deseado (PostgreSQL o H2).

## 📡 Endpoints Principales

El módulo **owl-backend** expone una serie de endpoints REST que permiten gestionar usuarios, fondos de inversión colectiva (FICs) y generar recomendaciones personalizadas según el perfil del inversionista.

### Usuarios
- **GET** `/api/usuarios` → Lista todos los usuarios registrados.  
- **GET** `/api/usuarios/{id}` → Obtiene la información de un usuario específico.  
- **POST** `/api/usuarios` → Crea un nuevo usuario.  
- **PUT** `/api/usuarios/{id}` → Actualiza los datos de un usuario existente.  
- **DELETE** `/api/usuarios/{id}` → Elimina un usuario del sistema.  

### FICs (Fondos de Inversión Colectiva)
- **GET** `/api/fics` → Retorna todos los fondos disponibles.  
- **GET** `/api/fics/{id}` → Obtiene un FIC específico junto con sus relaciones.  
- **POST** `/api/fics` → Registra un nuevo fondo de inversión.  
- **PUT** `/api/fics/{id}` → Actualiza la información de un FIC.  
- **DELETE** `/api/fics/{id}` → Elimina un FIC existente.  

### Recomendaciones
- **GET** `/api/recomendaciones` → Devuelve las recomendaciones personalizadas de FICs.  
- **GET** `/api/recomendaciones/usuario/{id}` → Obtiene las recomendaciones según el usuario.  
## 📊 Modelo de Datos Principal

El modelo de datos de **OWL** está estructurado en torno a la entidad principal **Fic**, que representa un fondo de inversión colectiva.  
A su alrededor se relacionan otras entidades que describen sus características, rendimiento y composición. Además, se incluye la entidad **Usuario**, utilizada para asociar los perfiles de riesgo y generar recomendaciones personalizadas.

- **Usuario:** Representa al inversionista, contiene datos personales, nivel de riesgo y credenciales seguras.  
- **Fic:** Entidad principal que almacena la información general de cada fondo.  
- **Características:** Describe atributos del fondo, como tipo de renta o moneda.  
- **Composición:** Indica la distribución de activos dentro del fondo.  
- **Calificación:** Registra las evaluaciones de agencias calificadoras.  
- **PlazoDuracion:** Define los plazos de inversión asociados al fondo.  
- **PrincipalesInversiones:** Contiene los activos o holdings más relevantes del FIC.  
- **RentabilidadVolatilidad:** Guarda los indicadores de rendimiento y riesgo histórico del fondo.  


### Comandos
```bash
# Compilar el proyecto
mvn clean install

# Ejecutar la aplicación
mvn spring-boot:run



