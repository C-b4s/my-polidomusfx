package ec.edu.epn.mypolidomus.DataAccess.DTOs;

public class PolidomusDTO {

    private Integer IdPolidomus;
    private String Serie;
    private String FechaCreacion;
    private String FechaModifica;

    public PolidomusDTO() {
    }

    public PolidomusDTO(String serie) {
        this.IdPolidomus = 0;
        this.Serie = serie;
    }

    public PolidomusDTO(Integer idPolidomus, String serie, String fechaCreacion, String fechaModifica) {
        this.IdPolidomus = idPolidomus;
        this.Serie = serie;
        this.FechaCreacion = fechaCreacion;
        this.FechaModifica = fechaModifica;
    }

    public Integer getIdPolidomus() { return IdPolidomus; }
    public void setIdPolidomus(Integer idPolidomus) { IdPolidomus = idPolidomus; }

    public String getSerie() { return Serie; }
    public void setSerie(String serie) { Serie = serie; }

    public String getFechaCreacion() { return FechaCreacion; }
    public void setFechaCreacion(String fechaCreacion) { FechaCreacion = fechaCreacion; }

    public String getFechaModifica() { return FechaModifica; }
    public void setFechaModifica(String fechaModifica) { FechaModifica = fechaModifica; }

    @Override
    public String toString() {
        return getClass().getName()
        + "\n IdPolidomus      : " + getIdPolidomus()
        + "\n Serie            : " + getSerie()
        + "\n FechaCreacion    : " + getFechaCreacion()
        + "\n FechaModifica    : " + getFechaModifica()
        + "\n --------------------------- ";
    }
}