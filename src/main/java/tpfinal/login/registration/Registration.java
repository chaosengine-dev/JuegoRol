package tpfinal.login.registration;

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
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionListener;
import java.util.Locale;

/**
 * Clase que crea la ventana de registro.
 */
public class Registration implements VentanaJuego {
    private JLabel registPassword;
    private JLabel registPasswordTwo;
    private JLabel registEmail;
    private JLabel registUser;
    private JTextField usernameTextField;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton crearUusuario;
    private JButton cancelarRegistro;
    private JPanel jPanelPrincipal;
    private JTextField emailField;
    private JFrame jFramePrincipal;
    private final Font FUENTE_MEDIEVAL = LeerArchivosTxtPng.leerFuente("Recursos/Fuentes/eland.ttf");
    private UsuarioRepositorio newUser = new UsuarioRepositorio();

    /**
     * El constructor le da forma a la ventana JFrame, setea los atributos de la misma.
     * Asigna la tipografia a los JLabel.
     * Le asigna a los botones del formulario un listener que va a detectar el click y ejecutar
     * los metodos correspondientes.
     */
    public Registration() {
        jFramePrincipal = new JFrame("REGISTRO USUARIO");
        jFramePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFramePrincipal.setPreferredSize(new Dimension(850, 720));
        jFramePrincipal.setResizable(true);
        jFramePrincipal.add(jPanelPrincipal);
        jFramePrincipal.pack();
        jFramePrincipal.setLocationRelativeTo(null);
        ImageIcon icon = Icono.crearIcono();
        jFramePrincipal.setIconImage(icon.getImage());
        jFramePrincipal.setVisible(true);
        registUser.setFont(FUENTE_MEDIEVAL.deriveFont(Font.BOLD, 32));
        registPassword.setFont(FUENTE_MEDIEVAL.deriveFont(Font.BOLD, 32));
        registPasswordTwo.setFont(FUENTE_MEDIEVAL.deriveFont(Font.BOLD, 32));
        registEmail.setFont(FUENTE_MEDIEVAL.deriveFont(Font.BOLD, 32));

        crearUusuario.addActionListener(clickBotonRegistrar());
        cancelarRegistro.addActionListener(clickBotonCancelar());
    }

    /**
     * Listener del boton Registrarse, va a leer los textfield del formulario.
     * Crear un objeto del tipo User con los datos obtenidos y agregarlo al json.
     * Si el resultado de agregar un usuario es true continua la ejecucion del juego.
     *
     * @return ActionListener para el boton asociado.
     */
    private ActionListener clickBotonRegistrar() {
        return e -> {
            try {
                // TODO: Se debe separar la logica de crear el usuario de la persistencia
                User newUser = new User(usernameTextField.getText(), passwordField1.getText(), passwordField2.getText(), emailField.getText());
                //userAgregado = newUser.agregar(new User(usernameTextField.getText(), passwordField1.getText(), passwordField2.getText(), emailField.getText()));

                //VALIDAR EL USUARIO ANTES DE AGREGARLO
                validarUsuario(newUser);

                //OBTENGO EL PROXIMO ID
                int nextID = getNextID();

                //ASIGNO EL ID AL USUARIO
                newUser.setId(nextID);

                //AGREGAR EL USUARIO AL REPOSITORIO
                UsuarioRepositorio repositorio = new UsuarioRepositorio();
                repositorio.agregar(newUser);

                JOptionPane.showMessageDialog(null, "Usuario agregado " + newUser.getUsername() + " correctamente", "Usuario agregado", JOptionPane.INFORMATION_MESSAGE);
                jFramePrincipal.dispose();
                AdministrarVentanas.cambiarEstadoActual(9);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        };
    }

    private boolean validarUsuario(User user) throws UserExceptions {
        boolean existe = false;
        //VALIDAR EXISTENCIA DEL USUARIO
        UsuarioRepositorio repositorio = new UsuarioRepositorio();
        List<User> userList = repositorio.listar();
        for (User existingUser : userList) {
            if (existingUser.getUsername().equals(user.getUsername())) {
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

    /**
     * Obtiene el siguiente id para almacenar un User nuevo.
     * @return int con el ID siguiente.
     */
    private int getNextID() {
        int nextID = 0;
        for (User user : this.newUser.listar()) {
            if (user.getId() > nextID) {
                nextID = user.getId();
            }
        }
        return nextID + 1;
    }

    /**
     * Listener del boton Cancelar.
     * Vuelve a la ventana anterior.
     *
     * @return ActionListener para el boton asociado.
     */
    private ActionListener clickBotonCancelar() {
        return e -> {
            jFramePrincipal.dispose();
            AdministrarVentanas.cambiarEstadoActual(9);
        };
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
        jPanelPrincipal = new JPanel();
        jPanelPrincipal.setLayout(new GridLayoutManager(13, 5, new Insets(0, 0, 0, 0), -1, -1));
        jPanelPrincipal.setBackground(new Color(-16777216));
        final JLabel label1 = new JLabel();
        label1.setHorizontalAlignment(0);
        label1.setHorizontalTextPosition(0);
        label1.setIcon(new ImageIcon(getClass().getResource("/Login/imagenes/presentacion.png")));
        label1.setText("");
        jPanelPrincipal.add(label1, new GridConstraints(1, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cancelarRegistro = new JButton();
        cancelarRegistro.setBorderPainted(false);
        cancelarRegistro.setContentAreaFilled(false);
        cancelarRegistro.setFocusPainted(false);
        cancelarRegistro.setHorizontalAlignment(0);
        cancelarRegistro.setIcon(new ImageIcon(getClass().getResource("/Login/imagenes/botonCancelar.png")));
        cancelarRegistro.setIconTextGap(4);
        cancelarRegistro.setText("");
        cancelarRegistro.setVerticalAlignment(0);
        jPanelPrincipal.add(cancelarRegistro, new GridConstraints(11, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        usernameTextField = new JTextField();
        usernameTextField.setHorizontalAlignment(2);
        usernameTextField.setMargin(new Insets(2, 6, 2, 6));
        usernameTextField.setText("");
        jPanelPrincipal.add(usernameTextField, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), null, 0, false));
        passwordField1 = new JPasswordField();
        passwordField1.setText("");
        jPanelPrincipal.add(passwordField1, new GridConstraints(5, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), null, 0, false));
        final Spacer spacer1 = new Spacer();
        jPanelPrincipal.add(spacer1, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        crearUusuario = new JButton();
        crearUusuario.setBorderPainted(false);
        crearUusuario.setContentAreaFilled(false);
        crearUusuario.setFocusPainted(false);
        crearUusuario.setHorizontalAlignment(0);
        crearUusuario.setIcon(new ImageIcon(getClass().getResource("/Login/imagenes/botonAceptar.png")));
        crearUusuario.setIconTextGap(4);
        crearUusuario.setText("");
        crearUusuario.setVerticalAlignment(0);
        jPanelPrincipal.add(crearUusuario, new GridConstraints(11, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        registEmail = new JLabel();
        Font registEmailFont = this.$$$getFont$$$("Enchanted Land", -1, 28, registEmail.getFont());
        if (registEmailFont != null) registEmail.setFont(registEmailFont);
        registEmail.setForeground(new Color(-1));
        registEmail.setText("Email");
        jPanelPrincipal.add(registEmail, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        registPasswordTwo = new JLabel();
        Font registPasswordTwoFont = this.$$$getFont$$$("Enchanted Land", -1, 28, registPasswordTwo.getFont());
        if (registPasswordTwoFont != null) registPasswordTwo.setFont(registPasswordTwoFont);
        registPasswordTwo.setForeground(new Color(-1));
        registPasswordTwo.setText("Repeat Password");
        jPanelPrincipal.add(registPasswordTwo, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        registPassword = new JLabel();
        Font registPasswordFont = this.$$$getFont$$$("Enchanted Land", -1, 28, registPassword.getFont());
        if (registPasswordFont != null) registPassword.setFont(registPasswordFont);
        registPassword.setForeground(new Color(-1));
        registPassword.setText("Password");
        jPanelPrincipal.add(registPassword, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        registUser = new JLabel();
        registUser.setEnabled(true);
        Font registUserFont = this.$$$getFont$$$("Fira Code", -1, -1, registUser.getFont());
        if (registUserFont != null) registUser.setFont(registUserFont);
        registUser.setForeground(new Color(-1));
        registUser.setText("Username");
        jPanelPrincipal.add(registUser, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 2, false));
        final Spacer spacer2 = new Spacer();
        jPanelPrincipal.add(spacer2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        jPanelPrincipal.add(spacer3, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        jPanelPrincipal.add(spacer4, new GridConstraints(12, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        jPanelPrincipal.add(spacer5, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        jPanelPrincipal.add(spacer6, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer7 = new Spacer();
        jPanelPrincipal.add(spacer7, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer8 = new Spacer();
        jPanelPrincipal.add(spacer8, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer9 = new Spacer();
        jPanelPrincipal.add(spacer9, new GridConstraints(10, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        emailField = new JTextField();
        jPanelPrincipal.add(emailField, new GridConstraints(9, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), null, 0, false));
        passwordField2 = new JPasswordField();
        passwordField2.setMargin(new Insets(2, 6, 2, 6));
        jPanelPrincipal.add(passwordField2, new GridConstraints(7, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), null, 0, false));
        final Spacer spacer10 = new Spacer();
        jPanelPrincipal.add(spacer10, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        registUser.setLabelFor(usernameTextField);
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
        return jPanelPrincipal;
    }

}
