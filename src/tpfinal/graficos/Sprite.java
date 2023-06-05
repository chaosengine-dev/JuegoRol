package tpfinal.graficos;

import java.awt.image.BufferedImage;

public class Sprite {
    private final BufferedImage imagen;
    private final int ancho;
    private final int alto;
    public Sprite(final BufferedImage imagen) {
        this.imagen = imagen;
        ancho = imagen.getWidth();
        alto = imagen.getHeight();
    }
    public BufferedImage obtenerImagen(){
        return imagen;
    }
}
