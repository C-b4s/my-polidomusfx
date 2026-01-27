package ec.edu.epn.mypolidomus.DataAccess.DTOs;

public class ArduinosDTO {

    private Integer IdArduinos;
    private String Nombre;
    private String Descripcion;
    private String Estado;
    private String FechaCreacion;
    private String FechaModifica;

    public ArduinosDTO() {
    }

    public ArduinosDTO(String nombre, String descripcion) {
        this.IdArduinos = 0;
        this.Nombre = nombre;
        this.Descripcion = descripcion;
        this.Estado = "A";
    }

    public ArduinosDTO(Integer idArduinos, String nombre, String descripcion, String estado, String fechaCreacion, String fechaModifica) {
        this.IdArduinos = idArduinos;
        this.Nombre = nombre;
        this.Descripcion = descripcion;
        this.Estado = estado;
        this.FechaCreacion = fechaCreacion;
        this.FechaModifica = fechaModifica;
    }

    public Integer getIdArduinos() { return IdArduinos; }
    public void setIdArduinos(Integer idArduinos) { IdArduinos = idArduinos; }

    public String getNombre() { return Nombre; }
    public void setNombre(String nombre) { Nombre = nombre; }

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
        + "\n IdArduinos       : " + getIdArduinos()
        + "\n Nombre           : " + getNombre()
        + "\n Descripcion      : " + getDescripcion()
        + "\n Estado           : " + getEstado()
        + "\n FechaCreacion    : " + getFechaCreacion()
        + "\n FechaModifica    : " + getFechaModifica()
        + "\n --------------------------- ";
    }
}