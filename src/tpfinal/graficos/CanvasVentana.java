package tpfinal.graficos;

import tpfinal.control.AdministrarControles;
import tpfinal.control.Raton;
import tpfinal.persistencia.LeerArchivos;
import tpfinal.vistas.AdministrarVentanas;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class CanvasVentana extends Canvas {

    private int alto;
    private int ancho;
    private Raton raton;
    private Point posicionVentana;

    // Public para poder usar la fuente en otra clase sin tener que leerla de nuevo
    public static Font FUENTE_MEDIEVAL = LeerArchivos.leerFuente("Recursos/Fuentes/SupplyCenter.ttf");

    public CanvasVentana(int ancho, int alto) {
        this.alto = alto;
        this.ancho = ancho;
        this.raton = new Raton(this);
        setCursor(raton.obtenerCursor());
        setIgnoreRepaint(true);
        setPreferredSize(new Dimension(ancho, alto));
        addKeyListener(AdministrarControles.teclado);
        addMouseListener(raton);
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

    public void actualizar(final CanvasVentana canvasVentana){
        raton.actualizar(canvasVentana);
    }

    public Raton getRaton(){
        return this.raton;
    }

    public Point getPosicionVentana() {
        return posicionVentana;
    }

    public void setPosicionVentana(Point posicionVentana) {
        this.posicionVentana = posicionVentana;
    }
}
