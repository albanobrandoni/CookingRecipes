
package modelo;

public class Recetas {
    
    private Long id = null;
    
    private Long idUsuarios = null;
    
    private String fecha = null;
    
    private String autor;
    
    private String titulo;
    
    private String consejo;
            
    private String imagen;
    
    private int comensales;
    
    private String tiempo;
    
    private String tiempoPreparacion;
    
    private String tipo;
    
    public Recetas(String autor, String titulo, String consejo, String imagen, int comensales, String tiempo, String tiempoPreparacion, String tipo) {
        this.autor = autor;
        this.titulo = titulo;
        this.consejo = consejo;
        this.imagen = imagen;
        this.comensales = comensales;
        this.tiempo = tiempo;
        this.tiempoPreparacion = tiempoPreparacion;
        this.tipo = tipo;
    }
        
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUsuarios() {
        return idUsuarios;
    }

    public void setIdUsuarios(Long idUsuarios) {
        this.idUsuarios = idUsuarios;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConsejo() {
        return consejo;
    }

    public void setConsejo(String consejo) {
        this.consejo = consejo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getComensales() {
        return comensales;
    }

    public void setComensales(int comensales) {
        this.comensales = comensales;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public void setTiempoPreparacion(String tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
        
}
