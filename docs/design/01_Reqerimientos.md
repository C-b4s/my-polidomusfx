# REQUERIMIENTOS

## Requerimiento 1

El Arduino debe activar el teclado matricial y la pantalla LCD cuando el individuo esté en un rango de distancia de máximo cuatro metros.

## Requerimiento 2

El solicitante debe poder ingresar sus datos mediante el teclado matricial.

## Requerimiento 3

Si es la primera vez que el solicitante usa el dispositivo, la AppPoliDomus deberá redirigir al solicitante al panel de registro, donde el mismo creará una contraseña e ingresará su nombre completo mediante el teclado matricial. Además, su perfil será guardado como usuario principal del hogar de forma predeterminada, con el nombre "Domus1726ApellidoUsuario".

## Requerimiento 4

El solicitante que intente acceder a la casa y no se haya registrado previamente, deberá pedirle un código único al usuario principal, el cual será generado en el panel "Nuevo Miembro" de la AppPoliDomus.

## Requerimiento 5

El solicitante deberá ingresar el código único en el panel de registro para poder crear su contraseña e ingresar su nombre completo usando el teclado matricial. Además, su perfil será almacenado como miembro del hogar en la base de datos, con el nombre "Poli-ApellidoUsuarioDomus".

## Requerimiento 6

La AppPoliDomus debe verificar si el nombre de usuario y la clave ingresados están registrados en la base de datos. De ser este el caso, se abrirá la puerta. Caso contrario, el solicitante podrá realizar un máximo de cinco intentos antes de que el sistema se bloquee.

## Requerimiento 7

Si el sistema se bloqueó por número máximo de intentos fallidos, la AppPoliDomus deberá enviar un correo al solicitante con un código único, el cual tendrá una validez de 5 minutos.

## Requerimiento 8

Para desbloquear el sistema, el solicitante deberá ingresar el código único al sistema mediante el teclado matricial en un plazo máximo de cinco minutos. Posteriormente, deberá crear una nueva contraseña y la AppPoliDomus deberá cambiar el estado de su antigua clave a 'X'.

## Requerimiento 9

El usuario principal del hogar debe poder consultar el historial de accesos con fecha, hora e intentos fallidos.

## Requerimiento 10

El sistema debe garantizar que solo exista un usuario principal del hogar y que solo él pueda visualizar los paneles "Nuevo Miembro" y "Delegar Usuario principal". 

## Requerimiento 11
Si un miembro del hogar desea convertirse en usuario principal, deberá solicitarle al usuario principal actual que le delegue esta función mediante el panel "Delegar Usuario principal", en el menú de la AppPoliDomus.

## Requerimiento 12
El usuario principal debe poder ingresar su contraseña y seleccionar el miembro del hogar que desea convertirse en usuario principal, en el panel "Delegar Usuario principal" de la AppPoliDomus. Estos cambios deberán almacenarse en la base de datos.
