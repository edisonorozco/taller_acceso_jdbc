package pojo;

import java.util.Date;

public class Libro {
    private Integer libroID;
    private String titulo;
    private String descripcion;
    private String publicado;
    private Integer autorId;
    private double precio;

    public Libro() {
    }

    public Libro(Integer libroID, String titulo, String descripcion, String publicado, Integer autorId, double precio) {
        this.libroID = libroID;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.publicado = publicado;
        this.autorId = autorId;
        this.precio = precio;
    }

    public Integer getLibroID() {
        return libroID;
    }

    public void setLibroID(Integer libroID) {
        this.libroID = libroID;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPublicado() {
        return publicado;
    }

    public void setPublicado(String publicado) {
        this.publicado = publicado;
    }

    public Integer getAutorId() {
        return autorId;
    }

    public void setAutorId(Integer autorId) {
        this.autorId = autorId;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
