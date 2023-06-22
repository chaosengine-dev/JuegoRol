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
import tpfinal.persistencia.UserExceptions;
import tpfinal.persistencia.UsuarioRepositorio;
import tpfinal.vistas.AdministrarVentanas;
import tpfinal.vistas.VentanaJuego;
import tpfinal.vistas.AdministrarVentanas;

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
    private JButton ButtonDelete;
    private JButton ButtonSave;
    private JButton ButtonCancel;
    private UsuarioRepositorio gestion = new UsuarioRepositorio();
    private JTextField IdUsuario;
    private JFrame jFramePrincipal;
    private ArrayList<User> usuariosRegistrados;

    /**
     * El constructor le da forma a la ventana JFrame, setea los atributos de la misma.
     * Asigna la tipografia a los JLabel.
     * Le asigna a los botones del formulario un listener que va a detectar el click y ejecutar
     * los metodos correspondientes.
     */
    public ManageUsers() {

        Font FUENTE_MEDIEVAL = LeerArchivosTxtPng.leerFuente("Recursos/Fuentes/eland.ttf");
        ImageIcon icon = Icono.crearIcono();

        jFramePrincipal = new JFrame("ADMINISTRAR USUARIOS");
        jFramePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFramePrincipal.setPreferredSize(new Dimension(950, 720));
        jFramePrincipal.setResizable(true);
        jFramePrincipal.pack();
        jFramePrincipal.setLocationRelativeTo(null);
        jFramePrincipal.setVisible(true);
        jFramePrincipal.setIconImage(icon.getImage());

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
        //Asigno listener a los botones
        ButtonSave.addActionListener(clickBotonGuardar());
        ButtonDelete.addActionListener(clickEliminarUsuario());
        ButtonCancel.addActionListener(clickBotonCancelar());

        comboUsers.addActionListener(cambioUsuarioComboBox());

        jFramePrincipal.add(ventana);
        jFramePrincipal.pack();
        jFramePrincipal.setLocationRelativeTo(null);
        jFramePrincipal.setVisible(true);
    }

    private ActionListener clickBotonCancelar() {
        return e -> {
            jFramePrincipal.dispose();
            AdministrarVentanas.iniciarVentanaAdmin();
            AdministrarVentanas.cambiarEstadoActual(11);
        };
    }

    private ActionListener clickBotonGuardar() {
        return e -> {
            try {
                // TODO: Se debe separar la logica de crear el usuario de la persistencia
                User newUser = new User(usuario.getText(), password1.getText(), password2.getText(), email.getText());
                newUser.setId(Integer.parseInt(IdUsuario.getText()));
                validarUsuario(newUser);
                if (soyAdministradorCheckBox.isSelected()) {
                    newUser.setAdmin(true);
                }
                //MODIFICAR USUARIO
                gestion.modificar(newUser);

                JOptionPane.showMessageDialog(null, "USUARIO MODIFICADO " + newUser.getUsername() + ".", "Usuario modificado", JOptionPane.INFORMATION_MESSAGE);
                jFramePrincipal.dispose();
                AdministrarVentanas.iniciarVentanaGestionUsuarios();
                AdministrarVentanas.cambiarEstadoActual(12);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        };
    }

    private ActionListener clickEliminarUsuario() {
        return e -> {
            if (comboUsers.getSelectedItem() != null && comboUsers.getSelectedIndex() != 0) {
                System.out.println(comboUsers.getSelectedIndex());
                String sel = (String) comboUsers.getSelectedItem();
                int option = JOptionPane.showConfirmDialog(null, "Seguro desea eliminar el usuario " + sel + "?");
                if (option == 0) {
                    gestion.eliminar(sel);
                    jFramePrincipal.dispose();
                    AdministrarVentanas.iniciarVentanaGestionUsuarios();
                    AdministrarVentanas.cambiarEstadoActual(12);
                    JOptionPane.showMessageDialog(null, "USUARIO ELIMINADO. ", "Eliminar usuario", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        };
    }

    private boolean validarUsuario(User user) throws UserExceptions {
        boolean existe = false;
        //VALIDAR EXISTENCIA DEL USUARIO
        for (User existingUser : usuariosRegistrados) {
            if (existingUser.getUsername().equals(user.getUsername()) && existingUser.getId() != user.getId()) {
                existe = true;
                break;
            }
        }
        if (existe) {
            throw new UserExceptions("El usuario " + user.getUsername() + " ya existe");
        }

        //VALIDAR DATOS DEL USUARIO
        if (user.getUsername().length() < 6) {
            throw new UserExceptions("El nombre de usuario debe tener al menos 6 caracteres");
        }
        if (!user.getUsername().matches("[a-zA-Z0-9]*")) {
            throw new UserExceptions("El nombre de usuario solo puede contener letras y numeros");
        }
        if (user.getPassword().length() < 6) {
            throw new UserExceptions("La contraseña debe tener al menos 6 caracteres");
        }
        if (!user.getPassword().equals(user.getSecondPassword())) {
            throw new UserExceptions("Las contraseñas no coinciden");
        }
        if (!user.getEmail().contains("@")) {
            throw new UserExceptions("El email debe contener un @");
        }
        return true;
    }

    private ActionListener cambioUsuarioComboBox() {
        return e -> {
            if (comboUsers.getSelectedItem() != null && comboUsers.getSelectedIndex() != 0) {
                String sel = (String) comboUsers.getSelectedItem();
                User userSelected = obtenerUsuario(sel);
                usuario.setText(userSelected.getUsername());
                password1.setText(userSelected.getPassword());
                password2.setText(userSelected.getSecondPassword());
                email.setText(userSelected.getEmail());
                IdUsuario.setText(String.valueOf(userSelected.getId()));
                if (userSelected.getisAdmin()) {
                    soyAdministradorCheckBox.setSelected(true);
                } else {
                    soyAdministradorCheckBox.setSelected(false);
                }
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
        for (User user : usuariosRegistrados) {
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
        ventana.setEnabled(true);
        ventana.setForeground(new Color(-16777216));
        final Spacer spacer1 = new Spacer();
        ventana.add(spacer1, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        ventana.add(spacer2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        email = new JTextField();
        ventana.add(email, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textoAdministrarUsuario = new JLabel();
        textoAdministrarUsuario.setBackground(new Color(-16777216));
        Font textoAdministrarUsuarioFont = this.$$$getFont$$$(null, -1, -1, textoAdministrarUsuario.getFont());
        if (textoAdministrarUsuarioFont != null) textoAdministrarUsuario.setFont(textoAdministrarUsuarioFont);
        textoAdministrarUsuario.setForeground(new Color(-394241));
        textoAdministrarUsuario.setText("Administrar usuarios");
        ventana.add(textoAdministrarUsuario, new GridConstraints(0, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        textoEmail = new JLabel();
        textoEmail.setBackground(new Color(-16777216));
        Font textoEmailFont = this.$$$getFont$$$(null, -1, -1, textoEmail.getFont());
        if (textoEmailFont != null) textoEmail.setFont(textoEmailFont);
        textoEmail.setForeground(new Color(-394241));
        textoEmail.setText("E-mail");
        ventana.add(textoEmail, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        soyAdministradorCheckBox = new JCheckBox();
        soyAdministradorCheckBox.setBackground(new Color(-16777216));
        soyAdministradorCheckBox.setFocusPainted(false);
        Font soyAdministradorCheckBoxFont = this.$$$getFont$$$(null, -1, -1, soyAdministradorCheckBox.getFont());
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
        ButtonCancel = new JButton();
        ButtonCancel.setBackground(new Color(-16777216));
        ButtonCancel.setBorderPainted(false);
        ButtonCancel.setContentAreaFilled(false);
        ButtonCancel.setDefaultCapable(true);
        ButtonCancel.setFocusPainted(false);
        ButtonCancel.setForeground(new Color(-1));
        ButtonCancel.setIcon(new ImageIcon(getClass().getResource("/Login/imagenes/Boton Cancelar.png")));
        ButtonCancel.setText("");
        ventana.add(ButtonCancel, new GridConstraints(6, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
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
        ventana.add(ButtonDelete, new GridConstraints(6, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        ButtonSave = new JButton();
        ButtonSave.setBackground(new Color(-16777216));
        ButtonSave.setBorderPainted(false);
        ButtonSave.setContentAreaFilled(false);
        ButtonSave.setEnabled(true);
        ButtonSave.setFocusPainted(false);
        ButtonSave.setForeground(new Color(-1));
        ButtonSave.setIcon(new ImageIcon(getClass().getResource("/Login/imagenes/Boton Guardar.png")));
        ButtonSave.setText("");
        ventana.add(ButtonSave, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        IdUsuario = new JTextField();
        IdUsuario.setBackground(new Color(-16777216));
        IdUsuario.setEditable(false);
        IdUsuario.setEnabled(true);
        IdUsuario.setFocusCycleRoot(false);
        IdUsuario.setFocusTraversalPolicyProvider(false);
        IdUsuario.setFocusable(false);
        IdUsuario.setForeground(new Color(-16777216));
        IdUsuario.setRequestFocusEnabled(true);
        IdUsuario.setSelectedTextColor(new Color(-16777216));
        IdUsuario.setText("");
        IdUsuario.setVerifyInputWhenFocusTarget(true);
        IdUsuario.setVisible(false);
        ventana.add(IdUsuario, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        usuario = new JTextField();
        usuario.setText("");
        ventana.add(usuario, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textoUsername = new JLabel();
        textoUsername.setBackground(new Color(-16777216));
        Font textoUsernameFont = this.$$$getFont$$$(null, -1, -1, textoUsername.getFont());
        if (textoUsernameFont != null) textoUsername.setFont(textoUsernameFont);
        textoUsername.setForeground(new Color(-394241));
        textoUsername.setText("Username");
        ventana.add(textoUsername, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textoPassword = new JLabel();
        textoPassword.setBackground(new Color(-16777216));
        Font textoPasswordFont = this.$$$getFont$$$(null, -1, -1, textoPassword.getFont());
        if (textoPasswordFont != null) textoPassword.setFont(textoPasswordFont);
        textoPassword.setForeground(new Color(-394241));
        textoPassword.setText("Password");
        ventana.add(textoPassword, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textoRepeatpassword = new JLabel();
        textoRepeatpassword.setBackground(new Color(-16777216));
        Font textoRepeatpasswordFont = this.$$$getFont$$$(null, -1, -1, textoRepeatpassword.getFont());
        if (textoRepeatpasswordFont != null) textoRepeatpassword.setFont(textoRepeatpasswordFont);
        textoRepeatpassword.setForeground(new Color(-394241));
        textoRepeatpassword.setText("Repeat password");
        ventana.add(textoRepeatpassword, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        password1 = new JPasswordField();
        ventana.add(password1, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        password2 = new JPasswordField();
        password2.setEnabled(true);
        password2.setText("");
        ventana.add(password2, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
