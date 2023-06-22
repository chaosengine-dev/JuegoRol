package tpfinal.graficos;

import tpfinal.control.Controles;
import tpfinal.control.Mouse;
import tpfinal.persistencia.LeerArchivosTxtPng;
import tpfinal.vistas.AdministrarVentanas;

import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Clase que crea y maneja la ventana principal.
 */
public class CanvasVentana extends Canvas {
    private int alto;
    private int ancho;
    private Mouse mouse;
    private Point posicionVentana;
    private final Font FUENTE_MEDIEVAL = LeerArchivosTxtPng.leerFuente("Recursos/Fuentes/SupplyCenter.ttf");

    /**
     * Constructor de la clase. Crea la ventana, crea 2 listeners, uno para el teclado y otro para el mouse.
     * @param ancho int con el ancho de la ventana
     * @param alto int con el alto de la ventana
     */
    public CanvasVentana(int ancho, int alto) {
        this.alto = alto;
        this.ancho = ancho;
        this.mouse = new Mouse(this);
        setCursor(mouse.getCursor());
        setIgnoreRepaint(true);
        setPreferredSize(new Dimension(ancho, alto));
        addKeyListener(Controles.teclado);
        addMouseListener(mouse);
        setFocusable(true);
        requestFocus();
    }

    /**
     * Este metodo crea la estrategia de buffering. Crea el objeto Graphics donde se dibujara el buufer.
     * Llama a administrar ventana, que es una clase que va a ir indicando que ventana mostrar.
     * Este metodo se repite tantas veces como la iteracion del objeto JuegoRol lo indique.
     */
    public void dibujar(){
        BufferStrategy buffer = getBufferStrategy();
        if (buffer == null){
            createBufferStrategy(4);
            return;
        }
        Graphics graphics = buffer.getDrawGraphics();
        graphics.setFont(FUENTE_MEDIEVAL);
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0,0, ancho, alto);
        AdministrarVentanas.dibujar(graphics);
        Toolkit.getDefaultToolkit().sync();
        graphics.dispose();
        buffer.show();
    }

    public Mouse getRaton(){
        return this.mouse;
    }

    public Point getPosicionVentana() {
        return posicionVentana;
    }

    public void setPosicionVentana(Point posicionVentana) {
        this.posicionVentana = posicionVentana;
    }
}
