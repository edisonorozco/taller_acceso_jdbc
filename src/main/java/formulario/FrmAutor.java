package formulario;

import interfaces.IDAOAutor;
import pojo.Autor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class FrmAutor extends JFrame {

    private IDAOAutor idaoAutor;

    public FrmAutor(IDAOAutor idaoAutor) {
        this.idaoAutor = idaoAutor;
        this.iniciarComponentes();
    }

    private void iniciarComponentes() {

        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(300, 175));
        setMaximumSize(new Dimension(300, 175));

        GroupLayout groupLayout = new GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(groupLayout);
        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(lblNombre)
                                .addComponent(lblEmail)
                                .addComponent(btnGuardar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(groupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtNombre)
                                .addComponent(txtEmail)
                                .addComponent(btnCancelar))
                        .addContainerGap()
                ));

        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(groupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblNombre)
                                        .addComponent(txtNombre, 27, 27, 27))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(groupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblEmail)
                                        .addComponent(txtEmail))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(groupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnGuardar)
                                        .addComponent(btnCancelar))
                                .addContainerGap())
        );

        getContentPane().add(pnlPrincipal);

        btnGuardar.addActionListener(btnNuevoAutor());

    }

    private ActionListener btnNuevoAutor() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (txtNombre.getText().isEmpty() || txtEmail.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(FrmAutor.this, "Por favor complete todo los campos", "Mensaje", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Autor autor1 = new Autor();
                autor1.setNombre(txtNombre.getText());
                autor1.setEmail(txtEmail.getText());

                try {
                    idaoAutor.registrarAutor(autor1);
                    JOptionPane.showMessageDialog(FrmAutor.this, "Autor registrado con exito", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        };
    }

    private JPanel pnlPrincipal = new JPanel();
    private JButton btnGuardar = new JButton("Guardar");
    private JButton btnCancelar = new JButton("Cancelar");
    private JLabel lblNombre = new JLabel("Nombre");
    private JLabel lblEmail = new JLabel("email");
    private JTextField txtNombre = new JTextField();
    private JTextField txtEmail = new JTextField();

}
