package formulario;

import com.toedter.calendar.JDateChooser;
import interfaces.IDAOAutor;
import interfaces.IDAOLibro;
import pojo.Autor;
import pojo.Libro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FrmLibro extends JFrame {

    private boolean actualizar = false;
    private IDAOLibro idaoLibro;
    private IDAOAutor idaoAutor;
    private FrmPrincipal frmPrincipal;
    private Libro libroCons;

    public FrmLibro(IDAOLibro idaoLibro, IDAOAutor idaoAutor, FrmPrincipal frmPrincipal) {
        this.idaoLibro = idaoLibro;
        this.idaoAutor = idaoAutor;
        this.frmPrincipal = frmPrincipal;
        this.iniciarComponentes();
    }

    public FrmLibro(IDAOLibro idaoLibro, IDAOAutor idaoAutor, FrmPrincipal frmPrincipal, boolean actualizar, Libro libro) {
        this.idaoLibro = idaoLibro;
        this.idaoAutor = idaoAutor;
        this.frmPrincipal = frmPrincipal;
        this.actualizar = actualizar;
        this.libroCons = libro;
        this.iniciarComponentes();
    }

    private void iniciarComponentes() {

        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(300, 300));
        setMaximumSize(new Dimension(300, 300));

        GroupLayout groupLayout = new GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(groupLayout);
        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(lblTitulo)
                                .addComponent(lblDescripcion)
                                .addComponent(lblPublicado)
                                .addComponent(lblAutor)
                                .addComponent(lblPrecio)
                                .addComponent(btnGuardar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(groupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtTitulo)
                                .addComponent(txtDescripcion)
                                .addComponent(calendar)
                                .addComponent(cmbAutor)
                                .addComponent(txtPrecio)
                                .addComponent(btnCancelar))
                        .addContainerGap()
                ));

        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(groupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblTitulo)
                                        .addComponent(txtTitulo, 27, 27, 27))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(groupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblDescripcion)
                                        .addComponent(txtDescripcion))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(groupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblPublicado)
                                        .addComponent(calendar))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(groupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblAutor)
                                        .addComponent(cmbAutor))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(groupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblPrecio)
                                        .addComponent(txtPrecio))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(groupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnGuardar)
                                        .addComponent(btnCancelar))
                                .addContainerGap())
        );

        getContentPane().add(pnlPrincipal);

        cargarAutores();
        btnGuardar.addActionListener(btnGuardarActionPerformed());

    }

    private void cargarAutores() {

        List<Autor> autorList;

        try {

            autorList = idaoAutor.listarAutores();

            for (Autor autor : autorList) {
                cmbAutor.addItem(autor.getNombre());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ActionListener btnGuardarActionPerformed() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (txtTitulo.getText().isEmpty() || txtDescripcion.getText().isEmpty() || txtPrecio.getText().isEmpty() || cmbAutor.getSelectedIndex() < 0) {
                    JOptionPane.showMessageDialog(FrmLibro.this, "Por favor complete todo los campos", "Mensaje", JOptionPane.ERROR_MESSAGE);
                    return;
                }


                String comboItem = (String) cmbAutor.getSelectedItem();
                Integer idAutor = 0;

                try {

                    idAutor = getInteger(comboItem, idAutor);

                    if(actualizar) {

                        libroCons.setTitulo(txtTitulo.getText());
                        libroCons.setDescripcion(txtDescripcion.getText());
                        libroCons.setPublicado(formatoFecha(calendar.getDate()));
                        libroCons.setAutorId(idAutor);
                        libroCons.setPrecio(Double.parseDouble(txtPrecio.getText()));

                        idaoLibro.modificarLibro(libroCons);
                        JOptionPane.showMessageDialog(FrmLibro.this, "Libro actualizado con exito", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                        frmPrincipal.cargarLibros();
                        return;
                    }

                    Libro libro1 = new Libro();
                    libro1.setTitulo(txtTitulo.getText());
                    libro1.setDescripcion(txtDescripcion.getText());
                    libro1.setPublicado(formatoFecha(calendar.getDate()));
                    libro1.setAutorId(idAutor);
                    libro1.setPrecio(Double.parseDouble(txtPrecio.getText()));

                    idaoLibro.registrarLibro(libro1);
                    JOptionPane.showMessageDialog(FrmLibro.this, "Libro registrado con exito", "Mensaje", JOptionPane.INFORMATION_MESSAGE);

                    frmPrincipal.cargarLibros();

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        };
    }

    private Integer getInteger(String comboItem, Integer idAutor) throws SQLException {
        for (Autor autor : idaoAutor.listarAutores()) {
            if (autor.getNombre().equals(comboItem))
                idAutor = autor.getAutorId();
        }
        return idAutor;
    }

    private String formatoFecha(Date date) {
        return new SimpleDateFormat("yyyy/MM/dd").format(date);
    }

    private JPanel pnlPrincipal = new JPanel();
    private JButton btnGuardar = new JButton("Guardar");
    private JButton btnCancelar = new JButton("Cancelar");
    private JLabel lblTitulo = new JLabel("Titulo");
    private JLabel lblDescripcion = new JLabel("Descripcion");
    private JLabel lblPublicado = new JLabel("Publicado");
    private JLabel lblAutor = new JLabel("Autor");
    private JLabel lblPrecio = new JLabel("Precio");
    public JTextField txtTitulo = new JTextField();
    public JTextField txtDescripcion = new JTextField();
    public JTextField txtPrecio = new JTextField();
    public JDateChooser calendar = new JDateChooser();
    private JComboBox<String> cmbAutor = new JComboBox<>();
}
