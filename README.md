# school-api

Este es un proyecto de SpringBoot por lo que se puede desplegar utilizando la clase principal `ApoloApplication.java`,  para personalizar el ambiente del despliegue se debe crear una archivo `application.yml` en los recursos del proyecto, se puede ver un ejemplo de cómo se debe configurar el archivo en la ruta [application.yml.dist](src/main/resources/application.yml.dist).


una vez desplegado se puede consumir el api desde la dirección:


https://localhost:8081

### Configurar Conexión Base de Datos

Para configurar la conexión a la base de datos se debe crear una sección datasource en el archivo application.yml.
```
datasource:  
  username: root  
  password: ''  
  url: jdbc:mysql://[url_bd]/nombre_bd?createDatabaseIfNotExist=true&useSSL=false
```

### Configurar Email
Para configurar el servicio de correos se deben ingresar un usuario y contraseña en la sección email del archivo application.yml, adicionalmente se debe usar un host propio, para el ejemplo se utiliza mailtrap.
```
mail:  
   host: "smtp.mailtrap.io"  
   port: 2525  
   protocol: 'smtp'  
   username: ""  
   password: ""
```
