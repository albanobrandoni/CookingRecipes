
package modelo;


public class Usuarios {
    
    private Long id = null;
    
    private String nombre;
    
    private String password;
    
    private String perfil;
    
    

    public Usuarios(String nombre, String password, String perfil) {
        this.nombre = nombre;
        this.password = password;
        this.perfil = perfil;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
   
   
}
