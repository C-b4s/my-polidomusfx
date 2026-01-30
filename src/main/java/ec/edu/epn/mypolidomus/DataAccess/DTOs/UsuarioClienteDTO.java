package ec.edu.epn.mypolidomus.DataAccess.DTOs;

public class UsuarioClienteDTO {

    private Integer IdUsuarioCliente;
    private String Nombre;
    private String Correo;
    private String Contrasena;
    private String Direccion;
    private Integer IdEstado;
    private String Estado;
    private String FechaCreacion;
    private String FechaModifica;

    public UsuarioClienteDTO() {
    }

    public UsuarioClienteDTO(String nombre, String correo, String contrasena, String direccion) {
        this.IdUsuarioCliente = 0;
        this.Nombre = nombre;
        this.Correo = correo;
        this.Contrasena = contrasena;
        this.Direccion = direccion;
        this.Estado = "A";
    }

    public UsuarioClienteDTO(Integer idUsuarioCliente, String nombre, String correo, String contrasena, String direccion, Integer idEstado, String estado, String fechaCreacion, String fechaModifica) {
        this.IdUsuarioCliente = idUsuarioCliente;
        this.Nombre = nombre;
        this.Correo = correo;
        this.Contrasena = contrasena;
        this.Direccion = direccion;
        this.IdEstado = idEstado;
        this.Estado = estado;
        this.FechaCreacion = fechaCreacion;
        this.FechaModifica = fechaModifica;
    }

    public Integer getIdUsuarioCliente() { return IdUsuarioCliente; }
    public void setIdUsuarioCliente(Integer idUsuarioCliente) { IdUsuarioCliente = idUsuarioCliente; }

    public String getNombre() { return Nombre; }
    public void setNombre(String nombre) { Nombre = nombre; }

    public String getCorreo() { return Correo; }
    public void setCorreo(String correo) { Correo = correo; }

    public String getContrasena() { return Contrasena; }
    public void setContrasena(String contrasena) { Contrasena = contrasena; }

    public String getDireccion() { return Direccion; }
    public void setDireccion(String direccion) { Direccion = direccion; }

    public Integer getIdEstado() { return IdEstado; }
    public void setIdEstado(Integer idEstado) { IdEstado = idEstado; }

    public String getEstado() { return Estado; }
    public void setEstado(String estado) { Estado = estado; }

    public String getFechaCreacion() { return FechaCreacion; }
    public void setFechaCreacion(String fechaCreacion) { FechaCreacion = fechaCreacion; }

    public String getFechaModifica() { return FechaModifica; }
    public void setFechaModifica(String fechaModifica) { FechaModifica = fechaModifica; }

    @Override
    public String toString() {
        return getClass().getName()
        + "\n IdUsuarioCliente : " + getIdUsuarioCliente()
        + "\n Nombre           : " + getNombre()
        + "\n Correo           : " + getCorreo()
        + "\n Direccion        : " + getDireccion()
        + "\n Estado           : " + getEstado()
        + "\n FechaCreacion    : " + getFechaCreacion()
        + "\n FechaModifica    : " + getFechaModifica()
        + "\n --------------------------- ";
    }
}