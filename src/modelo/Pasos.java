
package modelo;

public class Pasos {
    private Long id;
            
    private String descripcion;
    
    private int orden;
    
    private Long idRecetas;

    public Pasos(String descripcion, int orden, Long idRecetas) {
        this.descripcion = descripcion;
        this.orden = orden;
        this.idRecetas = idRecetas;
    }

    public Pasos(String descripcion, int orden) {
        this.descripcion = descripcion;
        this.orden = orden;
    }
        
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public Long getIdRecetas() {
        return idRecetas;
    }

    public void setIdRecetas(Long idRecetas) {
        this.idRecetas = idRecetas;
    }
          
}
