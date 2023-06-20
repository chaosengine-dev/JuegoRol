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


}
