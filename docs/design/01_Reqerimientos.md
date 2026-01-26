# REQUERIMIENTOS

## Requerimiento 1

El Arduino debe activar el teclado matricial y la pantalla LCD cuando el individuo esté en un rango de distancia de máximo cuatro metros.

## Requerimiento 2

Si es la primera vez que el dueño del producto usa el dispositivo, la AppPoliDomus deberá redirigir al usuario al panel de registro, donde creará una contraseña de seis a diez caracteres y su perfil será guardado en la base de datos con su nombre completo.

## Requerimiento 3

La AppPoliDomus debe verificar si la clave ingresada está registrada en la base de datos. De ser este el caso, se abrirá la puerta. Caso contrario, el dueño del producto podrá realizar un máximo de cinco intentos antes de que el sistema se bloquee.

## Requerimiento 4

Si el sistema se bloqueó por número máximo de intentos fallidos, la AppPoliDomus deberá enviar un correo al dueño del producto con un código único de desbloqueo OTP, cuya validez será de 10 minutos. En el caso de que este no se ingrese en el tiempo señalado, el acceso permanecerá bloqueado hasta que el usuario ingrese la combinación de teclas "*##*#**" para recibir un nuevo código en su correo.

## Requerimiento 5

El dueño del hogar debe poder consultar el historial de accesos, intentos fallidos y bloqueos en la AppPoliDomus con fecha y hora.

## Requerimiento 6

Si el dueño del hogar olvida la contraseña, podrá ingresar las teclas "3124" para que la AppPoliDomus envíe un OTP a su correo personal asociado al sistema, el cual tendrá una duración máxima de 10 minutos. Una vez ingresado el código, se podrá cambiar la contraseña o el correo electrónico en el módulo "Cambio de credenciales" de la AppPoliDomus. El correo o la clave antiguas deberán ser marcadas con una 'X' en el campo "Estado" de la base de datos.
