
package modelo;

public class Ingredientes {
    private Long id = null;
    
    private String nombre;
    
    private Long idUsers;

    public Ingredientes(String nombre, Long idUsers) {
        this.nombre = nombre;
        this.idUsers = idUsers;
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

    public Long getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(Long idUsers) {
        this.idUsers = idUsers;
    }
       
    
}
