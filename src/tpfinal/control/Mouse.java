package tpfinal.control;

import tpfinal.graficos.CanvasVentana;
import tpfinal.persistencia.LeerArchivos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Mouse extends MouseAdapter {
    private Cursor cursor;
    private Point posicionCursor;
    private boolean click;
    public Mouse(){

    }
    public Mouse(CanvasVentana canvasVentana){
        Toolkit configuracion = Toolkit.getDefaultToolkit();
        BufferedImage iconoCursor = LeerArchivos.cargarImagenTransparente("Recursos/Cursor/cursor.png");
        Point punto = new Point(0,0);
        click = false;
        cursor = configuracion.createCustomCursor(iconoCursor, punto, "cursor");
        posicionCursor = new Point();
        actualizarPosicion(canvasVentana);
    }

    public Cursor obtenerCursor(){
        return cursor;
    }
    public void actualizar(CanvasVentana canvasVentana){
        actualizarPosicion(canvasVentana);
    }
    public Point retornarPuntoPosicion(){
        return posicionCursor;
    }
    public Rectangle retornarRectanguloContacto(){
        Rectangle rect = new Rectangle(posicionCursor.x, posicionCursor.y, 1,1);
        return rect;
    }
    @Override
    public void mouseClicked(MouseEvent evento){
        if (!click){
            click = true;
        }
    }

    public boolean isClick() {
        return click;
    }

    public void resetClick() {
        this.click = false;

    }

    private void actualizarPosicion(CanvasVentana canvasVentana){
        final Point posicionInicial = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(posicionInicial, canvasVentana);
        posicionInicial.setLocation(posicionInicial);
    }

}
