package tpfinal.admin.manageusers;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import tpfinal.login.models.User;
import tpfinal.persistencia.repositorios.GestionRepo;
import tpfinal.vistas.VentanaJuego;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;

public class ManageUsers extends JFrame implements VentanaJuego {
    private JLabel titulo;
    private JPanel ventana;
    private JComboBox comboUsers;
    private JTextField usuario;
    private JPasswordField password1;
    private JPasswordField password2;
    private JCheckBox soyAdministradorCheckBox;
    private JTextField email;

    private ArrayList<User> usuariosRegistrados = new ArrayList<>();

    public ManageUsers() {
        add(ventana);
        setSize(700, 500); //tamaño de la ventana
        setLocationRelativeTo(null); // centra la ventana en la pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // cierra el programa al cerrar la ventana
        setVisible(true); // hace visible la ventana

        setTitle("ADMINISTRAR USUARIOS"); // titulo de la ventana
        GestionRepo gestion = new GestionRepo();
        usuariosRegistrados = (ArrayList<User>) gestion.listar();

        // muestro los datos de un archivo json en la ventana
        mostrarUser(usuariosRegistrados);

        comboUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sel = (String) comboUsers.getSelectedItem();
                User userSelected = gestion.obtenerUsuario(sel);
                usuario.setText(userSelected.getUsername());
                password1.setText(userSelected.getPassword());
                password2.setText(userSelected.getSecondPassword());
                email.setText(userSelected.getEmail());
                if (userSelected.getisAdmin()) {
                    soyAdministradorCheckBox.setSelected(true);
                } else {
                    soyAdministradorCheckBox.setSelected(false);
                }
            }
        });
    }


    // metodo para mostrar los User en cada uno de los label de la ventana, recibiendo los datos de un archivo json
    public void mostrarUser(ArrayList<User> usuariosRegistrados) {
        comboUsers.addItem("Elija usuario...");
        for (User user : usuariosRegistrados
        ) {
            comboUsers.addItem(user.getUsername());
        }

    }

    @Override
    public void actualizar() {

    }

    @Override
    public void dibujar(Graphics grafico) {

    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        ventana = new JPanel();
        ventana.setLayout(new GridLayoutManager(7, 6, new Insets(0, 0, 0, 0), -1, -1));
        ventana.setBackground(new Color(-16777216));
        titulo = new JLabel();
        Font tituloFont = this.$$$getFont$$$("Enchanted Land", -1, 20, titulo.getFont());
        if (tituloFont != null) titulo.setFont(tituloFont);
        titulo.setForeground(new Color(-1));
        titulo.setText("Usuarios");
        ventana.add(titulo, new GridConstraints(0, 2, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(112, 25), null, 0, false));
        final Spacer spacer1 = new Spacer();
        ventana.add(spacer1, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        ventana.add(spacer2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, new Dimension(27, 11), null, 0, false));
        final Spacer spacer3 = new Spacer();
        ventana.add(spacer3, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        ventana.add(spacer4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        comboUsers = new JComboBox();
        ventana.add(comboUsers, new GridConstraints(1, 2, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(112, 30), null, 0, false));
        usuario = new JTextField();
        ventana.add(usuario, new GridConstraints(2, 2, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        password1 = new JPasswordField();
        ventana.add(password1, new GridConstraints(3, 2, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        password2 = new JPasswordField();
        password2.setText("");
        ventana.add(password2, new GridConstraints(4, 2, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        soyAdministradorCheckBox = new JCheckBox();
        soyAdministradorCheckBox.setText("Soy Administrador");
        ventana.add(soyAdministradorCheckBox, new GridConstraints(6, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        email = new JTextField();
        ventana.add(email, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return ventana;
    }

}