# REQUERIMIENTOS

## Requerimiento 1
El Arduino debe activar el teclado matricial y la pantalla LCD cuando el individuo esté en un rango de cuatro metros. 

## Requerimiento 2
El solicitante debe poder ingresar su contraseña mediante el teclado matricial.

## Requerimiento 3
Si es la primera vez que el solicitante usa el dispositivo, la AppPolidomus  redirigir al solicitante al panel de registro, donde el mismo creará una contraseña y su perfil será guardado como usuario principal del hogar de forma predeterminada.

## Requerimiento 4
El solicitante que intente acceder a la casa y no esté registrado como usuario principal del hogar, deberá pedirle un código único que podrá ser generado en el módulo de Acceso del Menú de la AppPolidomus.

## Requerimiento 5
La AppPolidomus debe verificar si el nombre de usuario y la clave ingresados están registrados en la base de datos. De ser este el caso, se abre la puerta. Caso contrario, el solicitante podrá tener hasta cinco intentos antes que el sistema se bloquee.

## Requerimiento 6
Si el sistema se bloqueó por número máximo de intentos fallidos, la AppPolidomus deberá enviar un correo al solicitante con un código único, el cual tendrá una validez de  

## Requerimiento 7
El solicitante deberá ingresar el código único al sistema mediante el teclado matricial. Además, deberá crear una nueva contraseña y la AppPolidomus deberá cambiar el estado de su antigua clave a 'X'.

## Requerimiento 8
El usuario principal del hogar debe poder consultar el historial de accesos con fecha, hora e intentos fallidos.



