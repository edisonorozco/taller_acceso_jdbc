package formulario;

import interfaces.IDAOAutor;
import interfacesImpl.IDAOLibroImpl;
import pojo.Autor;
import pojo.Libro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class FrmPrincipal extends JFrame {

    private IDAOAutor idaoAutor;
    private IDAOLibroImpl idaoLibro;

    public FrmPrincipal(IDAOLibroImpl idaoLibro, IDAOAutor idaoAutor) {
        this.idaoLibro = idaoLibro;
        this.idaoAutor = idaoAutor;
        iniciarComponentes();
    }

    private void iniciarComponentes() {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(600, 300));
        setMaximumSize(new Dimension(600, 300));

        pnlBotones.setLayout(new BoxLayout(pnlBotones, BoxLayout.X_AXIS));
        pnlBotones.add(Box.createRigidArea(new Dimension(5, 0)));
        pnlBotones.add(btnNuevoAutor);
        pnlBotones.add(Box.createRigidArea(new Dimension(5, 0)));
        pnlBotones.add(btnNuevoLibro);
        pnlBotones.add(Box.createRigidArea(new Dimension(5, 0)));
        pnlBotones.add(btnModificarLibro);
        pnlBotones.add(Box.createRigidArea(new Dimension(5, 0)));
        pnlBotones.add(btnEliminarLibro);

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"id", "titulo", "description", "publicado", "autor", "precio"});
        tblLibros.setModel(tableModel);
        jScrollPane.setViewportView(tblLibros);

        pnlPrincipal.setLayout(new BoxLayout(pnlPrincipal, BoxLayout.Y_AXIS));
        pnlPrincipal.add(Box.createRigidArea(new Dimension(0, 5)));
        pnlPrincipal.add(pnlBotones);
        pnlPrincipal.add(Box.createRigidArea(new Dimension(0, 5)));
        pnlPrincipal.add(jScrollPane);

        getContentPane().add(pnlPrincipal);

        cargarLibros();

        btnNuevoLibro.addActionListener(btnNuevoLibroActionPerformed());
        btnNuevoAutor.addActionListener(btnNuevoAutorActionListener());
        btnModificarLibro.addActionListener(btnModificarLibroActionPerformed());
        btnEliminarLibro.addActionListener(btnEliminarActionPerformed());
    }

    public void cargarLibros() {

        List<Libro> libroList;

        try {

            libroList = idaoLibro.listarLibros();
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.setColumnIdentifiers(new String[]{"id", "titulo", "description", "publicado", "autor", "precio"});

            for (Libro libro : libroList) {

                String autor = "";

                for (Autor autor1 : idaoAutor.listarAutores()) {
                    if (autor1.getAutorId().equals(libro.getAutorId()))
                        autor = autor1.getNombre();
                }

                tableModel.addRow(new Object[]{libro.getLibroID(), libro.getTitulo(), libro.getDescripcion(), libro.getPublicado(), autor, libro.getPrecio()});
            }

            tblLibros.setModel(tableModel);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private ActionListener btnNuevoLibroActionPerformed() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> new FrmLibro(idaoLibro, idaoAutor, FrmPrincipal.this).setVisible(true));
            }
        };
    }

    private ActionListener btnNuevoAutorActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> new FrmAutor(idaoAutor).setVisible(true));
            }
        };
    }

    private ActionListener btnModificarLibroActionPerformed() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selectRow = tblLibros.getSelectedRow();

                if (selectRow < 0)
                    return;

                Integer idLibro = Integer.valueOf(String.valueOf(tblLibros.getValueAt(selectRow, 0)));

                try {

                    Libro libro = null;
                    List<Libro> libroList = idaoLibro.listarLibros();
                    for (Libro libro1 : libroList) {
                        if (libro1.getLibroID().equals(idLibro))
                            libro = libro1;
                    }

                    if (null == libro)
                        return;

                    FrmLibro frmLibro = new FrmLibro(idaoLibro, idaoAutor, FrmPrincipal.this, true, libro);

                    frmLibro.txtTitulo.setText(libro.getTitulo());
                    frmLibro.txtDescripcion.setText(libro.getDescripcion());
                    frmLibro.txtPrecio.setText(String.valueOf(libro.getPrecio()));
                    frmLibro.calendar.setDate(new Date(libro.getPublicado()));

                    frmLibro.setVisible(true);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        };
    }

    private ActionListener btnEliminarActionPerformed() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selectRow = tblLibros.getSelectedRow();

                if (selectRow < 0)
                    return;

                Integer idLibro = Integer.valueOf(String.valueOf(tblLibros.getValueAt(selectRow, 0)));

                try {
                    idaoLibro.eliminarLibro(idLibro);
                    cargarLibros();
                    JOptionPane.showMessageDialog(FrmPrincipal.this, "Libro eliminado con exito", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        };
    }

    private final JPanel pnlPrincipal = new JPanel();
    private final JButton btnNuevoAutor = new JButton("Nuevo Autor");
    private final JButton btnNuevoLibro = new JButton("Nuevo Libro");
    private final JButton btnModificarLibro = new JButton("Modificar Libro");
    private final JButton btnEliminarLibro = new JButton("Eliminar Libro");
    private final JScrollPane jScrollPane = new JScrollPane();
    public final JTable tblLibros = new JTable();
    private final JPanel pnlBotones = new JPanel();

}
