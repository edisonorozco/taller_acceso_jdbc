import formulario.FrmPrincipal;
import interfacesImpl.IDAOAutorImpl;
import interfacesImpl.IDAOLibroImpl;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FrmPrincipal(new IDAOLibroImpl(), new IDAOAutorImpl()).setVisible(true));
    }

}
