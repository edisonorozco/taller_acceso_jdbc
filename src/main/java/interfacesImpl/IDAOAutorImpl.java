package interfacesImpl;

import dao.Conexion;
import interfaces.IDAOAutor;
import pojo.Autor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IDAOAutorImpl extends Conexion implements IDAOAutor {

    @Override
    public void registrarAutor(Autor autor) throws SQLException {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("INSERT INTO tbl_autor (nombre, email)\n" +
                    "VALUES (?,?)");
            st.setString(1, autor.getNombre());
            st.setString(2, autor.getEmail());
            st.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public List<Autor> listarAutores() throws SQLException {

        List<Autor> autorList;

        try {

            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM tbl_autor");
            autorList = new ArrayList<>();
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Autor autor = new Autor();
                autor.setAutorId(rs.getInt("autor_id"));
                autor.setNombre(rs.getString("nombre"));
                autor.setEmail(rs.getString("email"));
                autorList.add(autor);
            }

            rs.close();
            st.close();

        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }

        return autorList;
    }
}
