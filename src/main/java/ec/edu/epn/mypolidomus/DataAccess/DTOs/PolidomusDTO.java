package ec.edu.epn.mypolidomus.DataAccess.DTOs; 

public class PolidomusDTO {
    private Integer IdPolidomus;
    private String Serie;
    private String Estado;
    private String FechaCreacion;
    private String FechaModifica;
    
    public PolidomusDTO(String serie) {
        IdPolidomus =0;
        Serie = serie;
    }
    public PolidomusDTO(){
    }
    public PolidomusDTO(Integer idPolidomus, String serie, String estado, String fechaCreacion,
            String fechaModifica) {
        IdPolidomus = idPolidomus;
        Serie = serie;
        Estado = estado;
        FechaCreacion = fechaCreacion;
        FechaModifica = fechaModifica;
    }
    public Integer getIdPolidomus() {
        return IdPolidomus;
    }
    public void setIdPolidomus(Integer idPolidomus) {
        IdPolidomus = idPolidomus;
    }
    public String getSerie() {
        return Serie;
    }
    public void setSerie(String serie) {
        Serie = serie;
    }
    public String getEstado() {
        return Estado;
    }
    public void setEstado(String estado) {
        Estado = estado;
    }
    public String getFechaCreacion() {
        return FechaCreacion;
    }
    public void setFechaCreacion(String fechaCreacion) {
        FechaCreacion = fechaCreacion;
    }
    public String getFechaModifica() {
        return FechaModifica;
    }
    public void setFechaModifica(String fechaModifica) {
        FechaModifica = fechaModifica;
    }
    @Override
    public String toString() {
        return getClass().getName()
        + "\n IdPolidomus : "+ getIdPolidomus  ()
        + "\n Serie        : "+ getSerie        ()
        + "\n Estado        : "+ getEstado          ()
        + "\n FechaCreacion : "+ getFechaCreacion   ()
        + "\n FechaModifica : "+ getFechaModifica   ()
        + "\n --------------------------- " ;
    }
}
