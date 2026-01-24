# REQUERIMIENTOS

## Requerimiento 1

El Arduino debe activar el teclado matricial y la pantalla LCD cuando el individuo esté en un rango de distancia de máximo cuatro metros.

## Requerimiento 2

El solicitante deberá ingresar su contraseña a la AppPoliDomus mediante el teclado matricial. Si es la primera vez que el solicitante usa el dispositivo, la AppPoliDomus deberá redirigir al solicitante al panel de registro, donde ingresará su nombre completo y creará una contraseña mediante el teclado matricial. Además, si no existen registros en la base de datos, su perfil será guardado como usuario principal del hogar de forma predeterminada, con el nombre "Domus1726ApellidoUsuario".

## Requerimiento 3

El solicitante que intente entrar a la casa y no se haya registrado previamente, deberá pedirle un código único al usuario principal, quien generará la clave correspondiente en el panel "Nuevo Miembro" de la AppPoliDomus. Luego, deberá ingresarla en el panel de registro para poder crear su contraseña e ingresar su nombre completo usando el teclado matricial. Además, su perfil será almacenado como miembro del hogar en la base de datos, con el nombre "Poli-ApellidoUsuarioDomus".

## Requerimiento 4

La AppPoliDomus debe verificar si la clave ingresada está registrada en la base de datos. De ser este el caso, se abrirá la puerta. Caso contrario, el solicitante podrá realizar un máximo de cinco intentos antes de que el sistema se bloquee.

## Requerimiento 5

Si el sistema se bloqueó por número máximo de intentos fallidos, la AppPoliDomus deberá enviar un correo al UsuarioPrincipal con un código único de desbloqueo OTP, cuya validez será de 10 minutos. En el caso de que este no se ingrese en el tiempo señalado, el acceso permanecerá bloqueado hasta que el usuario ingrese la combinación de teclas "*++*+**" para recibir un nuevo código en su correo.

## Requerimiento 6

El usuario principal del hogar debe poder consultar el historial de accesos, intentos fallidos y bloqueos en la AppPoliDomus con fecha y hora.

## Requerimiento 7

El sistema debe garantizar que solo exista un usuario principal del hogar y que solo él pueda visualizar los paneles "Nuevo Miembro" y "Delegar Usuario principal". 

## Requerimiento 8
El usuario principal debe poder delegar sus funciones a otro miembro del hogar en el panel "Delegar Usuario principal", de la AppPoliDomus, para lo cual deberá ingresar su contraseña. Estos cambios deberán almacenarse en la base de datos.
