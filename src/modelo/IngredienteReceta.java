
package modelo;


public class IngredienteReceta {
    
    private long idIngrediente;
    
    private long idReceta;
    
    private String cantidad;

    public IngredienteReceta(long idIngrediente, long idReceta, String cantidad) {
        this.idIngrediente = idIngrediente;
        this.idReceta = idReceta;
        this.cantidad = cantidad;
    }

    public long getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(long idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public long getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(long idReceta) {
        this.idReceta = idReceta;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
        
    
    
}
