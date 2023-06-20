package tpfinal.admin.adminpage;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import tpfinal.graficos.Icono;
import tpfinal.persistencia.LeerArchivosTxtPng;
import tpfinal.vistas.AdministrarVentanas;
import tpfinal.vistas.VentanaJuego;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

/**
 * Clase que crea la ventana del Administrador
 */
public class AdminPage extends JFrame implements VentanaJuego {
    private JPanel ventana;
    private JButton jugarButton;
    private JButton gestionarUsuariosButton;
    private JLabel titulo;
    private final JFrame frame;

    /**
     * El constructor le da forma a la ventana JFrame, setea los atributos de la misma.
     * Asigna la tipografia a los JLabel.
     * Le asigna a los botones del formulario un listener que va a detectar el click y ejecutar
     * los metodos correspondientes.
     */
    public AdminPage() {

        Font FUENTE_MEDIEVAL = LeerArchivosTxtPng.leerFuente("Recursos/Fuentes/eland.ttf");
        frame = new JFrame("ADMINISTRADOR");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(850, 720));
        frame.setResizable(true);
        frame.pack();
        ImageIcon icon = Icono.crearIcono();
        frame.setIconImage(icon.getImage());

        titulo.setFont(FUENTE_MEDIEVAL.deriveFont(Font.BOLD, 72));

        jugarButton.addActionListener(clickJugar());
        gestionarUsuariosButton.addActionListener(clickAdministrarUsuarios());

        frame.add(ventana);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Listener del boton Jugar.
     * Inicia el juego del usurio.
     *
     * @return ActionListener para el boton asociado.
     */
    private ActionListener clickJugar() {
        return e -> {
            frame.dispose();
            AdministrarVentanas.iniciarVentanaBienvenida();
            AdministrarVentanas.cambiarEstadoActual(0);
        };
    }

    /**
     * Listener del boton Administrar Usuarios.
     * Crea la ventana de administracion de usuarios y la activa.
     *
     * @return ActionListener para el boton asociado.
     */
    private ActionListener clickAdministrarUsuarios() {
        return e -> {
            frame.dispose();
            AdministrarVentanas.iniciarVentanaGestionUsuarios();
            AdministrarVentanas.cambiarEstadoActual(12);
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
        ventana = new JPanel();
        ventana.setLayout(new GridLayoutManager(5, 14, new Insets(0, 0, 0, 0), -1, -1));
        ventana.setBackground(new Color(-16777216));
        jugarButton = new JButton();
        jugarButton.setBackground(new Color(-16777216));
        jugarButton.setBorderPainted(false);
        jugarButton.setContentAreaFilled(false);
        jugarButton.setEnabled(true);
        jugarButton.setFocusPainted(false);
        Font jugarButtonFont = this.$$$getFont$$$("Enchanted Land", -1, 24, jugarButton.getFont());
        if (jugarButtonFont != null) jugarButton.setFont(jugarButtonFont);
        jugarButton.setForeground(new Color(-1));
        jugarButton.setHideActionText(false);
        jugarButton.setIcon(new ImageIcon(getClass().getResource("/Login/imagenes/Jugar.png")));
        jugarButton.setInheritsPopupMenu(false);
        jugarButton.setText("");
        ventana.add(jugarButton, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        gestionarUsuariosButton = new JButton();
        gestionarUsuariosButton.setBackground(new Color(-16777216));
        gestionarUsuariosButton.setBorderPainted(false);
        gestionarUsuariosButton.setContentAreaFilled(false);
        gestionarUsuariosButton.setFocusPainted(false);
        Font gestionarUsuariosButtonFont = this.$$$getFont$$$("Enchanted Land", -1, 24, gestionarUsuariosButton.getFont());
        if (gestionarUsuariosButtonFont != null) gestionarUsuariosButton.setFont(gestionarUsuariosButtonFont);
        gestionarUsuariosButton.setForeground(new Color(-1));
        gestionarUsuariosButton.setIcon(new ImageIcon(getClass().getResource("/Login/imagenes/Gestionar Usuarios.png")));
        gestionarUsuariosButton.setText("");
        ventana.add(gestionarUsuariosButton, new GridConstraints(3, 10, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        final Spacer spacer1 = new Spacer();
        ventana.add(spacer1, new GridConstraints(4, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        ventana.add(spacer2, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        ventana.add(spacer3, new GridConstraints(3, 13, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        ventana.add(spacer4, new GridConstraints(3, 8, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        ventana.add(spacer5, new GridConstraints(4, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, new Dimension(29, 11), null, 0, false));
        final Spacer spacer6 = new Spacer();
        ventana.add(spacer6, new GridConstraints(4, 7, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer7 = new Spacer();
        ventana.add(spacer7, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer8 = new Spacer();
        ventana.add(spacer8, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, new Dimension(47, 11), null, 0, false));
        titulo = new JLabel();
        titulo.setEnabled(true);
        Font tituloFont = this.$$$getFont$$$("Enchanted Land", -1, 72, titulo.getFont());
        if (tituloFont != null) titulo.setFont(tituloFont);
        titulo.setForeground(new Color(-1));
        titulo.setText("¿Qué desea hacer?");
        ventana.add(titulo, new GridConstraints(1, 0, 1, 14, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer9 = new Spacer();
        ventana.add(spacer9, new GridConstraints(0, 7, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer10 = new Spacer();
        ventana.add(spacer10, new GridConstraints(3, 9, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer11 = new Spacer();
        ventana.add(spacer11, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer12 = new Spacer();
        ventana.add(spacer12, new GridConstraints(3, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer13 = new Spacer();
        ventana.add(spacer13, new GridConstraints(3, 11, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setIcon(new ImageIcon(getClass().getResource("/Login/imagenes/portada2.png")));
        label1.setText("");
        ventana.add(label1, new GridConstraints(2, 0, 1, 14, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
