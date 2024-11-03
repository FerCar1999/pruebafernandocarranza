# Documentación Técnica

## **Arquitectura de la Aplicación**

La aplicación está diseñada en tres capas principales:

- **Capa de Presentación**:  
  Se utiliza HTML, Bootstrap y JS para la interfaz de usuario, permitiendo la visualización y el ingreso de información de productos, categorías, usuarios, así como iniciar sesión.

- **Capa de lógica de negocio**:Implementada en Spring con Java EE 8, maneja las operaciones CRUD para productos, categorías y usuarios.

- **Capa de Persistencia**:Utiliza Spring Data JPA con Hibernate para interactuar con una base de datos MySQL 8.1+, gestionando la persistencia de los datos de productos, categorías y usuarios.

---

## 📥 **Instrucciones de Instalación y Ejecución**


### Clona este repositorio:
```bash
git clone https://github.com/FerCar1999/pruebafernandocarranza.git
```
### Configura la base de datos:
- Crea una base de datos en MySQL 8.1+
-  Actualiza el archivo de propiedades ubicado en `Other Sources/src/main/resources/<default package>` de Spring con tus credenciales y URL de conexión a MySQL

### Ejecuta la aplicación:

- Importa el proyecto en Netbeans
  - Ve a `File`>`Open Project` y selecciona la carpeta adonde clonaste el proyecto

- Configura el Servidor Apache Tomcat 11 en NetBeans
  - **Descarga Apache Tomcat 11** desde el [sitio oficial de Apache Tomcat](https://tomcat.apache.org/download-11.cgi).
  - Configura Tomcat 11 en NetBeans
    - Ve a `Tools`>`Servers`
    - Selecciona `Add Server`, elige `Apache Tomcat` y sigue los pasos para agregarlo
    - Asegúrate de que esté configurado para JDK 17 y compatible con Java EE 8.

## **Despliegue a Producción**

Para desplegar la aplicación en un servidor de producción, sigue estos pasos:

- **Configura el servidor**:  
  - Preparar el entorno con el soporte para JDK 17, Java EE 8 y MySQL 8.1+.
  - Configura un servidor de aplicaciones compatible con Spring, como WildFly o Tomcat

- **Configura las variables de entorno**:Asegúrate de que las variables de entorno para la conexión a la base de datos estén configuradas con las credenciales de producción.

- **Empaqueta la aplicación**:
    - Haz el `build` de la aplicación
    - Al terminar de hacer el build, ingresa a la carpeta `target`
    - Copia el archivo llamado `pruebafernandocarranza-0.0.1-SNAPSHOT.war`

- **Despliegue en servidor**:Sube el archivo WAR generado al servidor de producción y cópialo en la carpeta webapps de Tomcat, que suele estar en `/path/to/tomcat/webapps/`.

---

# **Biblioteca**
Este documento describe cómo agregar un archivo JAR personalizado a un proyecto en Java y cómo configurarlo para que esté disponible en el código.

## **Prerrequisitos**
- Asegúrate de tener el archivo JAR personalizado que deseas agregar al proyecto, en este caso el archivo llamado biblioteca.jar
- Asegúrate de que el JAR sea compatible con la versión de Java que utiliza tu proyecto

## **Pasos para Agregar un Archivo JAR Personalizado a un Proyecto**
- En NetBeans
    - Haz clic derecho en el proyecto y selecciona `Properties`
    - Ve a la sección `Libraries`
    - Haz clic en `Add JAR/Folder`, selecciona el archivo JAR en la carpeta libs o lib, y confirma