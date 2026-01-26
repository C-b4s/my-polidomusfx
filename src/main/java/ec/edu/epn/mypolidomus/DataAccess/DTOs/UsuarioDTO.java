package ec.edu.epn.mypolidomus.DataAccess.DTOs;

public class UsuarioDTO {

    private Integer IdUsuario;
    private String Nombre;
    private String Correo;
    private String Contrasena;
    private String FechaCreacion;
    private String FechaModifica;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String nombre, String correo, String contrasena) {
        this.IdUsuario = 0;
        this.Nombre = nombre;
        this.Correo = correo;
        this.Contrasena = contrasena;
    }

    public UsuarioDTO(Integer idUsuario, String nombre, String correo, String contrasena, String fechaCreacion, String fechaModifica) {
        this.IdUsuario = idUsuario;
        this.Nombre = nombre;
        this.Correo = correo;
        this.Contrasena = contrasena;
        this.FechaCreacion = fechaCreacion;
        this.FechaModifica = fechaModifica;
    }

    public Integer getIdUsuario() { return IdUsuario; }
    public void setIdUsuario(Integer idUsuario) { IdUsuario = idUsuario; }

    public String getNombre() { return Nombre; }
    public void setNombre(String nombre) { Nombre = nombre; }

    public String getCorreo() { return Correo; }
    public void setCorreo(String correo) { Correo = correo; }

    public String getContrasena() { return Contrasena; }
    public void setContrasena(String contrasena) { Contrasena = contrasena; }

    public String getFechaCreacion() { return FechaCreacion; }
    public void setFechaCreacion(String fechaCreacion) { FechaCreacion = fechaCreacion; }

    public String getFechaModifica() { return FechaModifica; }
    public void setFechaModifica(String fechaModifica) { FechaModifica = fechaModifica; }

    @Override
    public String toString() {
        return getClass().getName()
        + "\n IdUsuario        : " + getIdUsuario()
        + "\n Nombre           : " + getNombre()
        + "\n Correo           : " + getCorreo()
        + "\n FechaCreacion    : " + getFechaCreacion()
        + "\n FechaModifica    : " + getFechaModifica()
        + "\n --------------------------- ";
    }
}