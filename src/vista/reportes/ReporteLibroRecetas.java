
package vista.reportes;

import java.awt.Image;
import java.util.List;

public class ReporteLibroRecetas {
    
    private String titulo;
    private String autor;
    private String tipo;
    private String comensales;        
    private String preparacion;
    private String coccion;
    private List<String> ingredientes;        
    private List<String> pasos;
    private String consejo;
    private Image imagen;

    public ReporteLibroRecetas(String titulo, String autor, String tipo, String comensales, String preparacion, String coccion, List<String> ingredientes, List<String> pasos, String consejo, Image imagen) {
        this.titulo = titulo;
        this.autor = autor;
        this.tipo = tipo;
        this.comensales = comensales;
        this.preparacion = preparacion;
        this.coccion = coccion;
        this.ingredientes = ingredientes;
        this.pasos = pasos;
        this.consejo = consejo;
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getComensales() {
        return comensales;
    }

    public void setComensales(String comensales) {
        this.comensales = comensales;
    }

    public String getPreparacion() {
        return preparacion;
    }

    public void setPreparacion(String preparacion) {
        this.preparacion = preparacion;
    }

    public String getCoccion() {
        return coccion;
    }

    public void setCoccion(String coccion) {
        this.coccion = coccion;
    }

    public List<String> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<String> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public List<String> getPasos() {
        return pasos;
    }

    public void setPasos(List<String> pasos) {
        this.pasos = pasos;
    }

    public String getConsejo() {
        return consejo;
    }

    public void setConsejo(String consejo) {
        this.consejo = consejo;
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }
        
    
    
}
