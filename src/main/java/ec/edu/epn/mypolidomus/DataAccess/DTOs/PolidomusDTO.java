package ec.edu.epn.mypolidomus.DataAccess.DTOs;

public class PolidomusDTO {
    
    // Identificadores y Relaciones
    private Integer IdPolidomus;

    private String Serie;
    private String Alias;
    private String TipoPredio;

    private String Direccion;
    private String Referencia;

    private String Estado;
    private String FechaCreacion;
    private String FechaModifica;

    // 1. Constructor Vacio
    public PolidomusDTO() {
    }

    // 2. Constructor para Crear nuevo (Mínimo necesario)
    public PolidomusDTO(Integer idUsuarioCliente, String serie, String alias) {
        IdPolidomus = 0;
        Serie = serie;
        Alias = alias;
        Estado = "A"; 
    }

    // 3. Constructor Completo (Lectura de la BD)
    public PolidomusDTO(Integer idPolidomus, Integer idUsuarioCliente, String serie, String alias, 
                        String tipoPredio, String direccion, String referencia, Double latitud, 
                        Double longitud, String observaciones, String estado, String fechaCreacion, 
                        String fechaModifica) {
        IdPolidomus = idPolidomus;
        Serie = serie;
        Alias = alias;
        TipoPredio = tipoPredio;
        Direccion = direccion;
        Referencia = referencia;
        Estado = estado;
        FechaCreacion = fechaCreacion;
        FechaModifica = fechaModifica;
    }

    // --- GETTERS Y SETTERS ---

    public Integer getIdPolidomus() { return IdPolidomus; }
    public void setIdPolidomus(Integer idPolidomus) { IdPolidomus = idPolidomus; }


    public String getSerie() { return Serie; }
    public void setSerie(String serie) { Serie = serie; }

    public String getAlias() { return Alias; }
    public void setAlias(String alias) { Alias = alias; }

    public String getTipoPredio() { return TipoPredio; }
    public void setTipoPredio(String tipoPredio) { TipoPredio = tipoPredio; }

    public String getDireccion() { return Direccion; }
    public void setDireccion(String direccion) { Direccion = direccion; }

    public String getReferencia() { return Referencia; }
    public void setReferencia(String referencia) { Referencia = referencia; }

    public String getEstado() { return Estado; }
    public void setEstado(String estado) { Estado = estado; }

    public String getFechaCreacion() { return FechaCreacion; }
    public void setFechaCreacion(String fechaCreacion) { FechaCreacion = fechaCreacion; }

    public String getFechaModifica() { return FechaModifica; }
    public void setFechaModifica(String fechaModifica) { FechaModifica = fechaModifica; }

    @Override
    public String toString() {
        return getClass().getName()
        + "\n IdPolidomus      : " + getIdPolidomus()
        + "\n Serie            : " + getSerie()
        + "\n Alias            : " + getAlias()
        + "\n TipoPredio       : " + getTipoPredio()
        + "\n Direccion        : " + getDireccion()
        + "\n Estado           : " + getEstado()
        + "\n FechaCreacion    : " + getFechaCreacion()
        + "\n FechaModifica    : " + getFechaModifica()
        + "\n --------------------------- ";
    }
}