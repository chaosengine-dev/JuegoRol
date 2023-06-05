package tpfinal.graficos;

import tpfinal.control.Controles;
import tpfinal.control.Mouse;
import tpfinal.persistencia.LeerArchivos;
import tpfinal.vistas.AdministrarVentanas;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class CanvasVentana extends Canvas {
    private int alto;
    private int ancho;
    private Mouse mouse;
    private Point posicionVentana;

    // Public para poder usar la fuente en otra clase sin tener que leerla de nuevo
    public static Font FUENTE_MEDIEVAL = LeerArchivos.leerFuente("Recursos/Fuentes/SupplyCenter.ttf");

    public CanvasVentana(int ancho, int alto) {
        this.alto = alto;
        this.ancho = ancho;
        this.mouse = new Mouse(this);
        setCursor(mouse.obtenerCursor());
        setIgnoreRepaint(true);
        setPreferredSize(new Dimension(ancho, alto));
        addKeyListener(Controles.teclado);
        addMouseListener(mouse);
        setFocusable(true);
        requestFocus();
    }

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

    public void actualizar(CanvasVentana canvasVentana){
        mouse.actualizar(canvasVentana);
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
