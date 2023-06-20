package tpfinal.admin.manageusers;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import tpfinal.graficos.Icono;
import tpfinal.login.models.User;
import tpfinal.persistencia.LeerArchivosTxtPng;
import tpfinal.persistencia.UsuarioRepositorio;
import tpfinal.vistas.VentanaJuego;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Clase que crea la ventana de administrar usuarios
 */
public class ManageUsers extends JFrame implements VentanaJuego {
    private JPanel ventana;
    private JComboBox<String> comboUsers;
    private JTextField usuario;
    private JPasswordField password1;
    private JPasswordField password2;
    private JCheckBox soyAdministradorCheckBox;
    private JTextField email;
    private JLabel textoUsername;
    private JLabel textoPassword;
    private JLabel textoRepeatpassword;
    private JLabel textoEmail;
    private JLabel textoAdministrarUsuario;
    private JButton button1;
    private JButton ButtonDelete;
    private JButton ButtonSave;
    private final UsuarioRepositorio gestion = new UsuarioRepositorio();

    /**
     * El constructor le da forma a la ventana JFrame, setea los atributos de la misma.
     * Asigna la tipografia a los JLabel.
     * Le asigna a los botones del formulario un listener que va a detectar el click y ejecutar
     * los metodos correspondientes.
     */
    public ManageUsers() {
        JFrame frame;
        Font FUENTE_MEDIEVAL = LeerArchivosTxtPng.leerFuente("Recursos/Fuentes/eland.ttf");
        ArrayList<User> usuariosRegistrados;
        frame = new JFrame("ADMINISTRAR USUARIOS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(850, 720));
        frame.setResizable(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        ImageIcon icon = Icono.crearIcono();
        frame.setIconImage(icon.getImage());
        // Setear tipografia
        textoAdministrarUsuario.setFont(FUENTE_MEDIEVAL.deriveFont(Font.BOLD, 46));
        textoUsername.setFont(FUENTE_MEDIEVAL.deriveFont(Font.BOLD, 26));
        textoEmail.setFont(FUENTE_MEDIEVAL.deriveFont(Font.BOLD, 26));
        textoPassword.setFont(FUENTE_MEDIEVAL.deriveFont(Font.BOLD, 26));
        textoRepeatpassword.setFont(FUENTE_MEDIEVAL.deriveFont(Font.BOLD, 26));
        soyAdministradorCheckBox.setFont(FUENTE_MEDIEVAL.deriveFont(Font.BOLD, 26));


        usuariosRegistrados = (ArrayList<User>) gestion.listar();

        // muestro los datos de un archivo json en la ventana
        mostrarUser(usuariosRegistrados);


        comboUsers.addActionListener(cambioUsuarioComboBox());

        frame.add(ventana);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private ActionListener cambioUsuarioComboBox() {
        return e -> {
            String sel = (String) comboUsers.getSelectedItem();
            User userSelected = obtenerUsuario(sel);
            usuario.setText(userSelected.getUsername());
            password1.setText(userSelected.getPassword());
            password2.setText(userSelected.getSecondPassword());
            email.setText(userSelected.getEmail());
            if (userSelected.getisAdmin()) {
                soyAdministradorCheckBox.setSelected(true);
            } else {
                soyAdministradorCheckBox.setSelected(false);
            }
        };
    }

    /**
     * Busca en la lista de User, el usuario que coincida con el nombre enviado por parametro.
     *
     * @param nombre Nombre de usuario buscado
     * @return Objeto del tipo User encontrado, null en caso contrario.
     */
    public User obtenerUsuario(String nombre) {
        UsuarioRepositorio gestion = new UsuarioRepositorio();
        for (User user : gestion.listar()) {
            if (user.getUsername().equals(nombre)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Crea el combobox con los usuarios que lee del ArrayList enviado por parametro
     *
     * @param usuariosRegistrados ArrayList con todos los usuarios registrados, que lee del Json.
     */
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
        ventana.setLayout(new GridLayoutManager(7, 4, new Insets(0, 0, 0, 0), -1, -1));
        ventana.setBackground(new Color(-16777216));
        final Spacer spacer1 = new Spacer();
        ventana.add(spacer1, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        ventana.add(spacer2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        usuario = new JTextField();
        ventana.add(usuario, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        password1 = new JPasswordField();
        ventana.add(password1, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        email = new JTextField();
        ventana.add(email, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        password2 = new JPasswordField();
        password2.setEnabled(true);
        password2.setText("");
        ventana.add(password2, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textoAdministrarUsuario = new JLabel();
        textoAdministrarUsuario.setBackground(new Color(-16777216));
        Font textoAdministrarUsuarioFont = this.$$$getFont$$$("Enchanted Land", -1, 48, textoAdministrarUsuario.getFont());
        if (textoAdministrarUsuarioFont != null) textoAdministrarUsuario.setFont(textoAdministrarUsuarioFont);
        textoAdministrarUsuario.setForeground(new Color(-394241));
        textoAdministrarUsuario.setText("Administrar usuarios");
        ventana.add(textoAdministrarUsuario, new GridConstraints(0, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        textoUsername = new JLabel();
        textoUsername.setBackground(new Color(-16777216));
        Font textoUsernameFont = this.$$$getFont$$$("Enchanted Land", -1, 28, textoUsername.getFont());
        if (textoUsernameFont != null) textoUsername.setFont(textoUsernameFont);
        textoUsername.setForeground(new Color(-394241));
        textoUsername.setText("Username");
        ventana.add(textoUsername, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textoPassword = new JLabel();
        textoPassword.setBackground(new Color(-16777216));
        Font textoPasswordFont = this.$$$getFont$$$("Enchanted Land", -1, 28, textoPassword.getFont());
        if (textoPasswordFont != null) textoPassword.setFont(textoPasswordFont);
        textoPassword.setForeground(new Color(-394241));
        textoPassword.setText("Password");
        ventana.add(textoPassword, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textoRepeatpassword = new JLabel();
        textoRepeatpassword.setBackground(new Color(-16777216));
        Font textoRepeatpasswordFont = this.$$$getFont$$$("Enchanted Land", -1, 28, textoRepeatpassword.getFont());
        if (textoRepeatpasswordFont != null) textoRepeatpassword.setFont(textoRepeatpasswordFont);
        textoRepeatpassword.setForeground(new Color(-394241));
        textoRepeatpassword.setText("Repeat password");
        ventana.add(textoRepeatpassword, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textoEmail = new JLabel();
        textoEmail.setBackground(new Color(-16777216));
        Font textoEmailFont = this.$$$getFont$$$("Enchanted Land", -1, 28, textoEmail.getFont());
        if (textoEmailFont != null) textoEmail.setFont(textoEmailFont);
        textoEmail.setForeground(new Color(-394241));
        textoEmail.setText("E-mail");
        ventana.add(textoEmail, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        soyAdministradorCheckBox = new JCheckBox();
        soyAdministradorCheckBox.setBackground(new Color(-16777216));
        soyAdministradorCheckBox.setFocusPainted(false);
        Font soyAdministradorCheckBoxFont = this.$$$getFont$$$("Enchanted Land", -1, 16, soyAdministradorCheckBox.getFont());
        if (soyAdministradorCheckBoxFont != null) soyAdministradorCheckBox.setFont(soyAdministradorCheckBoxFont);
        soyAdministradorCheckBox.setForeground(new Color(-394241));
        soyAdministradorCheckBox.setText("Soy Administrador");
        ventana.add(soyAdministradorCheckBox, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        comboUsers = new JComboBox();
        comboUsers.setEditable(true);
        comboUsers.setEnabled(true);
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        comboUsers.setModel(defaultComboBoxModel1);
        ventana.add(comboUsers, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(200, 30), null, 0, false));
        button1 = new JButton();
        button1.setBackground(new Color(-16777216));
        button1.setBorderPainted(false);
        button1.setContentAreaFilled(false);
        button1.setDefaultCapable(true);
        button1.setFocusPainted(false);
        button1.setForeground(new Color(-1));
        button1.setIcon(new ImageIcon(getClass().getResource("/Login/imagenes/Boton Cancelar.png")));
        button1.setText("");
        ventana.add(button1, new GridConstraints(6, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        ButtonDelete = new JButton();
        ButtonDelete.setBackground(new Color(-16777216));
        ButtonDelete.setBorderPainted(false);
        ButtonDelete.setContentAreaFilled(false);
        ButtonDelete.setEnabled(true);
        ButtonDelete.setFocusPainted(false);
        ButtonDelete.setFocusable(true);
        ButtonDelete.setForeground(new Color(-1));
        ButtonDelete.setIcon(new ImageIcon(getClass().getResource("/Login/imagenes/Boton Borrar.png")));
        ButtonDelete.setText("");
        ventana.add(ButtonDelete, new GridConstraints(6, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        ButtonSave = new JButton();
        ButtonSave.setBackground(new Color(-16777216));
        ButtonSave.setBorderPainted(false);
        ButtonSave.setContentAreaFilled(false);
        ButtonSave.setEnabled(true);
        ButtonSave.setFocusPainted(false);
        ButtonSave.setForeground(new Color(-1));
        ButtonSave.setIcon(new ImageIcon(getClass().getResource("/Login/imagenes/Boton Guardar.png")));
        ButtonSave.setText("");
        ventana.add(ButtonSave, new GridConstraints(6, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
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
