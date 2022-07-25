package interfaces;

import pojo.Libro;

import java.sql.SQLException;
import java.util.List;

public interface IDAOLibro {
    void registrarLibro(Libro libro) throws SQLException;
    void modificarLibro(Libro libro) throws SQLException;
    void eliminarLibro(Integer id) throws SQLException;
    List<Libro> listarLibros() throws SQLException;
}
