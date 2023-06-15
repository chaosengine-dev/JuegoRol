package tpfinal.control;

import tpfinal.graficos.CanvasVentana;
import tpfinal.persistencia.LeerArchivosTxtPng;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * Clase que maneja el mouse extiende de la clase MouseAdapter que nos proporciona metodos para saber
 * cuando el mouse es clickeado.
 */
public class Mouse extends MouseAdapter {
    private Cursor cursor;
    private Point posicionCursor;
    private boolean click;
    public Mouse(){

    }

    /**
     * Constructor de la clase Mouse.
     * Crea la imagen del icono a partir del archivo.
     * Le asigna la posicion 0 de X y 0 de Y y actualiza la ventana.
     * @param canvasVentana es la ventana principal donde se dibuja todo el juego.
     */
    public Mouse(CanvasVentana canvasVentana){
        Toolkit configuracion = Toolkit.getDefaultToolkit();
        BufferedImage iconoCursor = LeerArchivosTxtPng.cargarImagenTransparente("Recursos/Cursor/cursor.png");
        Point punto = new Point(0,0);
        click = false;
        cursor = configuracion.createCustomCursor(iconoCursor, punto, "cursor");
        posicionCursor = new Point();
        actualizar(canvasVentana);
    }

    /**
     * Actualiza la posicion del mouse tantas veces como se refresque el canvas.
     * @param canvasVentana Ventana principal donde se dibuja el juego.
     */
    public void actualizar(CanvasVentana canvasVentana){
        final Point posicionInicial = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(posicionInicial, canvasVentana);
        posicionInicial.setLocation(posicionInicial);
    }

    @Override
    public void mouseClicked(MouseEvent evento){
        if (!click){
            click = true;
        }
    }
    public Cursor getCursor(){
        return cursor;
    }
    public boolean isClick() {
        return click;
    }
    public void resetClick() {
        this.click = false;

    }

}
