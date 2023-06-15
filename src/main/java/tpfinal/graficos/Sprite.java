package tpfinal.graficos;

import java.awt.image.BufferedImage;

/**
 * Clase que crea una BufferedImage a partir de una imagen que se le envia.
 * Las imagenes son obtenidas de una hoja de Sprites y son convertidas cada
 * una de ellas a pequeños objetos del tipo Sprite.
 */
public class Sprite {
    private final BufferedImage imagen;
    private final int ancho;
    private final int alto;

    /**
     * Constructor de la clase Sprite, crea un Buffered image con su informacion de alto y ancho
     * @param imagen BufferedImage para trabajar en forma individual.
     */
    public Sprite(BufferedImage imagen) {
        this.imagen = imagen;
        ancho = imagen.getWidth();
        alto = imagen.getHeight();
    }

    public BufferedImage getImagen(){
        return imagen;
    }
}
