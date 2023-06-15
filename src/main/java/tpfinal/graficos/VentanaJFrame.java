package tpfinal.graficos;

import javax.swing.*;
import java.awt.*;

/**
 * Clase que crea el JFrame principal que luego agregara los items al canvas del juego.
 */
public class VentanaJFrame extends JFrame {

    private String nombre;
    private final ImageIcon icon;

    /**
     * Constructor de la clase, asigna el nombre a la ventana, el icono y configura la ventana.
     * @param nombre String que indica el nombre de la ventana
     * @param canvasVentana Canvas principal del juego.
     */
    public VentanaJFrame(String nombre, CanvasVentana canvasVentana){
        this.nombre = nombre;
        this.icon = Icono.crearIcono();
        configurarVentana(canvasVentana);
        canvasVentana.setPosicionVentana(posicionVentana());
    }

    /**
     * Configura la ventana, la arma y la muestra
     * @param canvasVentana Canvas principal del juego.
     */
    private void configurarVentana(CanvasVentana canvasVentana) {
        setTitle(nombre);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setIconImage(icon.getImage());
        setLayout(new BorderLayout());
        setUndecorated(true);
        add(canvasVentana, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public Point posicionVentana(){
        return this.getLocationOnScreen();
    }
}
