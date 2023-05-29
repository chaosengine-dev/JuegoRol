package tpfinal.graficos;

import tpfinal.persistencia.LeerArchivos;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class VentanaJFrame extends JFrame {

    private String nombre;
    private final ImageIcon icon;

    public VentanaJFrame(final String nombre, final CanvasVentana canvasVentana){
        this.nombre = nombre;
        BufferedImage imagen = LeerArchivos.cargarImagenTransparente("Recursos/Mapas/logo.png");
        this.icon = new ImageIcon(imagen);
        configurarVentana(canvasVentana);
        canvasVentana.setPosicionVentana(posicionVentana());
    }

    private void configurarVentana(final CanvasVentana canvasVentana) {
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
