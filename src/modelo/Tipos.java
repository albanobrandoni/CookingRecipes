
package modelo;

public class Tipos {
    
    private Long id = null;
    
    private String nombre;
    
    private Long idUsuarios;

    public Tipos(String nombre, Long idUsuarios) {
        this.nombre = nombre;
        this.idUsuarios = idUsuarios;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getIdUsuarios() {
        return idUsuarios;
    }

    public void setIdUsuarios(Long idUsuarios) {
        this.idUsuarios = idUsuarios;
    }
       
}
