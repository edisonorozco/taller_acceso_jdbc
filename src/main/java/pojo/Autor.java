package pojo;

public class Autor {
    private Integer autorId;
    private String nombre;
    private String email;

    public Autor() {
    }

    public Autor(Integer autorId, String nombre, String email) {
        this.autorId = autorId;
        this.nombre = nombre;
        this.email = email;
    }

    public Integer getAutorId() {
        return autorId;
    }

    public void setAutorId(Integer autorId) {
        this.autorId = autorId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
