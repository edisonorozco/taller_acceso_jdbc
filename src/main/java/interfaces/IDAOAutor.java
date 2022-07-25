package interfaces;

import pojo.Autor;

import java.sql.SQLException;
import java.util.List;

public interface IDAOAutor {
    void registrarAutor(Autor autor) throws SQLException;
    List<Autor> listarAutores() throws SQLException;
}
