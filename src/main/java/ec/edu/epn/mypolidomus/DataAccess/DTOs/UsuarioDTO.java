package ec.edu.epn.mypolidomus.DataAccess.DTOs;

public class UsuarioDTO {

    private Integer IdUsuario;
    private Integer IdUsuarioTipo;
    private Integer IdEstado;
    private String Nombre;
    private String Correo;
    private String Contrasena;
    private String Direccion;
    private String Descripcion;
    private String Estado;
    private String FechaCreacion;
    private String FechaModifica;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String nombre, String correo, String contrasena, Integer idUsuarioTipo) {
        this.IdUsuario = 0;
        this.IdUsuarioTipo = idUsuarioTipo;
        this.Nombre = nombre;
        this.Correo = correo;
        this.Contrasena = contrasena;
        this.Estado = "A";
    }

    public UsuarioDTO(Integer idUsuario, Integer idUsuarioTipo, Integer idEstado, String nombre, String correo, 
                      String contrasena, String direccion, String descripcion, String estado, String fechaCreacion, String fechaModifica) {
        this.IdUsuario = idUsuario;
        this.IdUsuarioTipo = idUsuarioTipo;
        this.IdEstado = idEstado;
        this.Nombre = nombre;
        this.Correo = correo;
        this.Contrasena = contrasena;
        this.Direccion = direccion;
        this.Descripcion = descripcion;
        this.Estado = estado;
        this.FechaCreacion = fechaCreacion;
        this.FechaModifica = fechaModifica;
    }

    public Integer getIdUsuario() { return IdUsuario; }
    public void setIdUsuario(Integer idUsuario) { IdUsuario = idUsuario; }

    public Integer getIdUsuarioTipo() { return IdUsuarioTipo; }
    public void setIdUsuarioTipo(Integer idUsuarioTipo) { IdUsuarioTipo = idUsuarioTipo; }

    public Integer getIdEstado() { return IdEstado; }
    public void setIdEstado(Integer idEstado) { IdEstado = idEstado; }

    public String getNombre() { return Nombre; }
    public void setNombre(String nombre) { Nombre = nombre; }

    public String getCorreo() { return Correo; }
    public void setCorreo(String correo) { Correo = correo; }

    public String getContrasena() { return Contrasena; }
    public void setContrasena(String contrasena) { Contrasena = contrasena; }

    public String getDireccion() { return Direccion; }
    public void setDireccion(String direccion) { Direccion = direccion; }

    public String getDescripcion() { return Descripcion; }
    public void setDescripcion(String descripcion) { Descripcion = descripcion; }

    public String getEstado() { return Estado; }
    public void setEstado(String estado) { Estado = estado; }

    public String getFechaCreacion() { return FechaCreacion; }
    public void setFechaCreacion(String fechaCreacion) { FechaCreacion = fechaCreacion; }

    public String getFechaModifica() { return FechaModifica; }
    public void setFechaModifica(String fechaModifica) { FechaModifica = fechaModifica; }

    @Override
    public String toString() {
        return getClass().getName()
        + "\n IdUsuario        : " + getIdUsuario()
        + "\n IdUsuarioTipo    : " + getIdUsuarioTipo()
        + "\n Nombre           : " + getNombre()
        + "\n Correo           : " + getCorreo()
        + "\n Direccion        : " + getDireccion()
        + "\n Descripcion      : " + getDescripcion()
        + "\n Estado           : " + getEstado()
        + "\n FechaCreacion    : " + getFechaCreacion()
        + "\n FechaModifica    : " + getFechaModifica()
        + "\n --------------------------- ";
    }
}
