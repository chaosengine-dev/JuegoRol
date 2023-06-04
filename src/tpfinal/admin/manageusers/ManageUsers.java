package tpfinal.admin.manageusers;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import tpfinal.vistas.VentanaJuego;

import java.awt.*;
import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;
import java.util.Locale;

public class ManageUsers extends JFrame implements VentanaJuego {
    private JLabel titulo;
    private JPanel ventana;
    private JButton siguienteButton;
    private JButton anteriorButton;
    private JButton user1;
    private JButton user2;
    private JButton user3;
    private JButton user4;

    public ManageUsers() {
        add(ventana);
        setSize(700, 500); //tamaño de la ventana
        setLocationRelativeTo(null); // centra la ventana en la pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // cierra el programa al cerrar la ventana
        setVisible(true); // hace visible la ventana

        setTitle("Manage Users"); // titulo de la ventana

        // muestro los datos de un archivo json en la ventana
        //mostrarUser("src/tpfinal/login/archivos/usuarios.json");

    }


    // metodo para mostrar la ventana
    public void mostrarVentana() {
        JFrame frame = new JFrame("ManageUsers");
        frame.setContentPane(new ManageUsers().ventana);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    // metodo para mostrar un archivo json en la ventana
   /* public void mostrarJson(String json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode jsonNode = new ObjectMapper().readTree(json);
            if (jsonNode.isArray()) {
                for (JsonNode node : jsonNode) {
                    String nombre = node.get("nombre").asText();
                    System.out.println(nombre);
                }
            }else{
                String nombre = jsonNode.get("nombre").asText();
                System.out.println(nombre);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }*/

    // metodo para mostrar los User en cada uno de los label de la ventana, recibiendo los datos de un archivo json
    public void mostrarUser(String json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode jsonNode = new ObjectMapper().readTree(json);
            if (jsonNode.isArray()) {
                for (JsonNode node : jsonNode) {
                    String nombre = node.get("nombre").asText();
                    String apellido = node.get("apellido").asText();
                    String correo = node.get("correo").asText();
                    String telefono = node.get("telefono").asText();
                    System.out.println(nombre);
                    System.out.println(apellido);
                    System.out.println(correo);
                    System.out.println(telefono);
                }
            } else {
                String nombre = jsonNode.get("nombre").asText();
                String apellido = jsonNode.get("apellido").asText();
                String correo = jsonNode.get("correo").asText();
                String telefono = jsonNode.get("telefono").asText();
                System.out.println(nombre);
                System.out.println(apellido);
                System.out.println(correo);
                System.out.println(telefono);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
        ventana.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(5, 5, new Insets(0, 0, 0, 0), -1, -1));
        ventana.setBackground(new Color(-16777216));
        titulo = new JLabel();
        Font tituloFont = this.$$$getFont$$$("Enchanted Land", -1, 20, titulo.getFont());
        if (tituloFont != null) titulo.setFont(tituloFont);
        titulo.setForeground(new Color(-1));
        titulo.setText("Usuarios");
        ventana.add(titulo, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        ventana.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        ventana.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        ventana.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(0, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        ventana.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        siguienteButton = new JButton();
        siguienteButton.setBorderPainted(false);
        siguienteButton.setContentAreaFilled(false);
        siguienteButton.setForeground(new Color(-1));
        siguienteButton.setIcon(new ImageIcon(getClass().getResource("/tpfinal/admin/manageusers/arrowright128.png")));
        siguienteButton.setText("");
        ventana.add(siguienteButton, new com.intellij.uiDesigner.core.GridConstraints(2, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        anteriorButton = new JButton();
        anteriorButton.setBorderPainted(false);
        anteriorButton.setContentAreaFilled(false);
        anteriorButton.setForeground(new Color(-1));
        anteriorButton.setIcon(new ImageIcon(getClass().getResource("/tpfinal/admin/manageusers/arrowleft128.png")));
        anteriorButton.setText("");
        ventana.add(anteriorButton, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        user1 = new JButton();
        user1.setContentAreaFilled(false);
        user1.setText("");
        ventana.add(user1, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        user2 = new JButton();
        user2.setContentAreaFilled(false);
        user2.setText("");
        ventana.add(user2, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        user3 = new JButton();
        user3.setContentAreaFilled(false);
        user3.setText("");
        ventana.add(user3, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        user4 = new JButton();
        user4.setContentAreaFilled(false);
        user4.setText("");
        ventana.add(user4, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
