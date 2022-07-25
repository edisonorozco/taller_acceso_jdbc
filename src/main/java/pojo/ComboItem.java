package pojo;

public class ComboItem {
    private Integer autorId;
    private String nombreAutor;

    public ComboItem(Integer autorId, String nombreAutor) {
        this.autorId = autorId;
        this.nombreAutor = nombreAutor;
    }

    public Integer getAutorId() {
        return autorId;
    }

    public void setAutorId(Integer autorId) {
        this.autorId = autorId;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }
}
