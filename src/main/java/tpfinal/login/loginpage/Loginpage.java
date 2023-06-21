package tpfinal.login.loginpage;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import tpfinal.graficos.Icono;
import tpfinal.login.models.User;
import tpfinal.login.registration.Registration;
import tpfinal.persistencia.LeerArchivosTxtPng;
import tpfinal.persistencia.UsuarioRepositorio;
import tpfinal.vistas.AdministrarVentanas;
import tpfinal.vistas.VentanaJuego;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.Objects;

/**
 * Clase que crea la ventana de login.
 */
public class Loginpage implements VentanaJuego {
    private JPasswordField passwordUsuario;
    private JTextField nombreUsuario;
    private JButton botonLogin;
    private JLabel imagenFondo;
    private JPanel panelPpal;
    private JLabel userlogin;
    private JLabel passlogin;
    private JButton botonCancelar;
    private final JFrame jFramePpal;
    private UsuarioRepositorio userList = new UsuarioRepositorio();


    /**
     * El constructor le da forma a la ventana JFrame, setea los atributos de la misma.
     * Asigna la tipografia a los JLabel.
     * Le asigna a los botones del formulario un listener que va a detectar el click y ejecutar
     * los metodos correspondientes.
     */
    public Loginpage() {
        jFramePpal = new JFrame("INGRESO DE USUARIO");
        jFramePpal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFramePpal.setPreferredSize(new Dimension(850, 720));
        jFramePpal.setResizable(true);
        jFramePpal.pack();
        jFramePpal.setLocationRelativeTo(null);
        jFramePpal.setVisible(true);
        ImageIcon icon = Icono.crearIcono();
        jFramePpal.setIconImage(icon.getImage());

        Font FUENTE_MEDIEVAL = LeerArchivosTxtPng.leerFuente("Recursos/Fuentes/eland.ttf");
        userlogin.setFont(FUENTE_MEDIEVAL.deriveFont(Font.BOLD, 26));
        passlogin.setFont(FUENTE_MEDIEVAL.deriveFont(Font.BOLD, 26));

        botonLogin.addActionListener(clickBotonEntrar());
        botonCancelar.addActionListener(clickBotonCancelar());

        jFramePpal.add(panelPpal);
        jFramePpal.pack();
        jFramePpal.setLocationRelativeTo(null);
        jFramePpal.setVisible(true);
    }

    /**
     * Listener del boton Entrar, va a leer los textfield de user y password.
     * Verifica las credenciales, si son correctas inicia sesion en el juego.
     * En caso contrario vuelve a la ventana anterior.
     *
     * @return ActionListener para el boton asociado.
     */
    private ActionListener clickBotonEntrar() {
        return e -> {
            String user = nombreUsuario.getText();
            String pass = passwordUsuario.getText();
            boolean validCredentials = checkCredentials(user, pass);
            if (validCredentials) {
                jFramePpal.dispose();
                if (checkAdmin(user)) {
                    AdministrarVentanas.iniciarVentanaAdmin();
                    AdministrarVentanas.setUserRegistered(user);
                    AdministrarVentanas.cambiarEstadoActual(11);
                } else {
                    AdministrarVentanas.iniciarVentanaBienvenida();
                    AdministrarVentanas.setUserRegistered(user);
                    AdministrarVentanas.cambiarEstadoActual(0);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecta.");
            }
        };
    }

    /**
     * Listener del boton Cancelar.
     * Vuelve a la ventana anterior.
     *
     * @return ActionListener para el boton asociado.
     */
    private ActionListener clickBotonCancelar() {
        return e -> {
            jFramePpal.dispose();
            AdministrarVentanas.cambiarEstadoActual(0);
        };
    }

    //FUNCION DE COMPROBAR USUARIO

    /**
     * Verifica si el usuario y contraseña pertenecen a algún usuario del archivo json.
     *
     * @param username Nombre de usuario
     * @param password Contraseña de usuario
     * @return True si las credenciales son correctas, false si no lo son.
     */
    private boolean checkCredentials(String username, String password) {
        UsuarioRepositorio newLogin = new UsuarioRepositorio();
        newLogin.cargar();

        // Verificar si el username existe en el repositorio de usuarios
        boolean usernameExists = existeUsername(username);

        if (usernameExists) {
            // Verificar las credenciales para el username existente
            boolean validCredentials = verificarCredenciales(username, password);
            if (validCredentials) {
                return true; // Credenciales válidas
            } else {
                System.out.println("Credenciales incorrectas para el usuario: " + username);
            }
        } else {
            System.out.println("El usuario no existe: " + username);
        }

        return false; // Credenciales inválidas
    }

    /**
     * Verifica si existe el nombre de usuario en el json y devuelve true en caso que exista
     * o false en caso contrario.
     *
     * @param username Nombre de usuario buscado.
     * @return true si existe el usuario, false en caso contrario.
     */
    public boolean existeUsername(String username) {
        UsuarioRepositorio userRepo = new UsuarioRepositorio();
        userRepo.cargar();
        for (User user : this.userList.listar()) {
            if (Objects.equals(user.getUsername(), username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica si el conjunto username y password pertenencen a un usuario del json.
     *
     * @param username Nombre de usuario buscado.
     * @param password Password del usuario buscado.
     * @return true si el conjunto es valido, false en caso contrario.
     */
    public boolean verificarCredenciales(String username, String password) {
        UsuarioRepositorio userRepo = new UsuarioRepositorio();
        userRepo.cargar();
        for (User user : this.userList.listar()) {
            if (Objects.equals(user.getUsername(), username) && Objects.equals(user.getPassword(), password)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica si existe el usuario es administrador y devuelve true en caso que lo sea
     * o false en caso contrario.
     *
     * @param username Nombre de usuario buscado.
     * @return true si el usuario es administrador, false en caso contrario.
     */
    public boolean isAdmin(String username) {
        UsuarioRepositorio userRepo = new UsuarioRepositorio();
        userRepo.cargar();
        for (User user : this.userList.listar()) {
            if (Objects.equals(user.getUsername(), username)) {
                return user.getisAdmin();
            }
        }
        return false;
    }

    // FUNCION PARA COMPROBAR SI EL USUARIO ES ADMIN

    /**
     * Verifica si el usuario logueado es administrador.
     *
     * @param username Nombre de usuario logueado.
     * @return True si es administrador, false si no lo es.
     */
    private boolean checkAdmin(String username) {
        UsuarioRepositorio newLogin = new UsuarioRepositorio();
        newLogin.cargar();
        if (isAdmin(username)) {
            return true;//es admin
        }
        return false;//no es admin
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
        panelPpal = new JPanel();
        panelPpal.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(8, 9, new Insets(0, 0, 0, 0), -1, -1));
        panelPpal.setBackground(new Color(-16777216));
        userlogin = new JLabel();
        Font userloginFont = this.$$$getFont$$$(null, -1, -1, userlogin.getFont());
        if (userloginFont != null) userlogin.setFont(userloginFont);
        userlogin.setForeground(new Color(-1));
        userlogin.setText("USUARIO");
        panelPpal.add(userlogin, new com.intellij.uiDesigner.core.GridConstraints(3, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        passwordUsuario = new JPasswordField();
        panelPpal.add(passwordUsuario, new com.intellij.uiDesigner.core.GridConstraints(5, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        nombreUsuario = new JTextField();
        panelPpal.add(nombreUsuario, new com.intellij.uiDesigner.core.GridConstraints(3, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panelPpal.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(4, 6, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        panelPpal.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(4, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        panelPpal.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(1, 7, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        panelPpal.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer5 = new com.intellij.uiDesigner.core.Spacer();
        panelPpal.add(spacer5, new com.intellij.uiDesigner.core.GridConstraints(2, 7, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer6 = new com.intellij.uiDesigner.core.Spacer();
        panelPpal.add(spacer6, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        imagenFondo = new JLabel();
        imagenFondo.setIcon(new ImageIcon(getClass().getResource("/Login/imagenes/portada.png")));
        imagenFondo.setText("");
        panelPpal.add(imagenFondo, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        final com.intellij.uiDesigner.core.Spacer spacer7 = new com.intellij.uiDesigner.core.Spacer();
        panelPpal.add(spacer7, new com.intellij.uiDesigner.core.GridConstraints(1, 8, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer8 = new com.intellij.uiDesigner.core.Spacer();
        panelPpal.add(spacer8, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer9 = new com.intellij.uiDesigner.core.Spacer();
        panelPpal.add(spacer9, new com.intellij.uiDesigner.core.GridConstraints(7, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer10 = new com.intellij.uiDesigner.core.Spacer();
        panelPpal.add(spacer10, new com.intellij.uiDesigner.core.GridConstraints(0, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        passlogin = new JLabel();
        Font passloginFont = this.$$$getFont$$$(null, -1, -1, passlogin.getFont());
        if (passloginFont != null) passlogin.setFont(passloginFont);
        passlogin.setForeground(new Color(-1));
        passlogin.setText("CONTRASEÑA");
        panelPpal.add(passlogin, new com.intellij.uiDesigner.core.GridConstraints(5, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer11 = new com.intellij.uiDesigner.core.Spacer();
        panelPpal.add(spacer11, new com.intellij.uiDesigner.core.GridConstraints(6, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        botonLogin = new JButton();
        botonLogin.setBorderPainted(false);
        botonLogin.setContentAreaFilled(false);
        botonLogin.setFocusPainted(false);
        botonLogin.setIcon(new ImageIcon(getClass().getResource("/Login/imagenes/botonLogin.png")));
        botonLogin.setText("");
        panelPpal.add(botonLogin, new com.intellij.uiDesigner.core.GridConstraints(6, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        botonCancelar = new JButton();
        botonCancelar.setActionCommand("");
        botonCancelar.setBorderPainted(false);
        botonCancelar.setContentAreaFilled(false);
        botonCancelar.setFocusPainted(false);
        botonCancelar.setIcon(new ImageIcon(getClass().getResource("/Login/imagenes/botonCancelar.png")));
        botonCancelar.setLabel("");
        botonCancelar.setText("");
        panelPpal.add(botonCancelar, new com.intellij.uiDesigner.core.GridConstraints(6, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 2, false));
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
        return panelPpal;
    }

}
