
package vista.reportes;

public class ReporteIndiceReceta {
    private String titulo;
    private String pagina;

    public ReporteIndiceReceta(String titulo, String pagina) {
        this.titulo = titulo;
        this.pagina = pagina;
    }

    
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPagina() {
        return pagina;
    }

    public void setPagina(String pagina) {
        this.pagina = pagina;
    }
    
    
}
