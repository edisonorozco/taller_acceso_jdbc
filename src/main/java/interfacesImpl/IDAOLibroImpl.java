package interfacesImpl;

import dao.Conexion;
import interfaces.IDAOLibro;
import pojo.Libro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IDAOLibroImpl extends Conexion implements IDAOLibro {

    @Override
    public void registrarLibro(Libro libro) throws SQLException {

        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("INSERT INTO tbl_libro (titulo, description, publicado, autor_id, precio)\n" +
                    "VALUES (?,?,?,?,?)");
            st.setString(1, libro.getTitulo());
            st.setString(2, libro.getDescripcion());
            st.setString(3, formatoFecha(new Date()));
            st.setInt(4, libro.getAutorId());
            st.setDouble(5, libro.getPrecio());
            st.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }

    }

    @Override
    public void modificarLibro(Libro libro) throws SQLException {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("UPDATE tbl_libro SET titulo='" + libro.getTitulo() + "', description='" + libro.getDescripcion() + "', publicado='" + libro.getPublicado() + "', autor_id='" + libro.getAutorId() + "', precio='" + libro.getPrecio() + "' WHERE libro_id = ?");
            st.setInt(1, libro.getLibroID());
            st.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void eliminarLibro(Integer id) throws SQLException {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM tbl_libro WHERE libro_id = ?");
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public List<Libro> listarLibros() throws SQLException {
        List<Libro> libroList;

        try {

            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM tbl_libro");
            libroList = new ArrayList<>();
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Libro libro = new Libro();
                libro.setLibroID(rs.getInt("libro_id"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setDescripcion(rs.getString("description"));
                libro.setPublicado(rs.getString("publicado"));
                libro.setAutorId(rs.getInt("autor_id"));
                libro.setPrecio(rs.getDouble("precio"));
                libroList.add(libro);
            }

            rs.close();
            st.close();

        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }

        return libroList;
    }

    private String formatoFecha(Date date) {
        return new SimpleDateFormat("yyyy/MM/dd").format(date);
    }
}
