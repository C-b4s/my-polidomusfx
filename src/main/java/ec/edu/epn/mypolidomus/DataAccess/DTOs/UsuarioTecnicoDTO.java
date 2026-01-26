package ec.edu.epn.mypolidomus.DataAccess.DTOs;

public class UsuarioTecnicoDTO {

    private Integer IdUsuarioTecnico;
    private String Descripcion;
    private String Estado;
    private String FechaCreacion;
    private String FechaModifica;

    public UsuarioTecnicoDTO() {
    }

    public UsuarioTecnicoDTO(String descripcion) {
        this.IdUsuarioTecnico = 0;
        this.Descripcion = descripcion;
        this.Estado = "A";
    }

    public UsuarioTecnicoDTO(Integer idUsuarioTecnico, String descripcion, String estado, String fechaCreacion, String fechaModifica) {
        this.IdUsuarioTecnico = idUsuarioTecnico;
        this.Descripcion = descripcion;
        this.Estado = estado;
        this.FechaCreacion = fechaCreacion;
        this.FechaModifica = fechaModifica;
    }

    public Integer getIdUsuarioTecnico() { return IdUsuarioTecnico; }
    public void setIdUsuarioTecnico(Integer idUsuarioTecnico) { IdUsuarioTecnico = idUsuarioTecnico; }

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
        + "\n IdUsuarioTecnico : " + getIdUsuarioTecnico()
        + "\n Descripcion      : " + getDescripcion()
        + "\n Estado           : " + getEstado()
        + "\n FechaCreacion    : " + getFechaCreacion()
        + "\n FechaModifica    : " + getFechaModifica()
        + "\n --------------------------- ";
    }
}