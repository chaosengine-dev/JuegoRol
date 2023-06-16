package tpfinal.graficos;

import java.awt.image.BufferedImage;

/**
 * Clase que crea una BufferedImage a partir de una imagen que se le envia.
 * Las imagenes son obtenidas de una hoja de Sprites y son convertidas cada
 * una de ellas a pequeños objetos del tipo Sprite.
 */
public class Sprite {
    private final BufferedImage imagen;
    /**
     * Constructor de la clase Sprite.
     * @param imagen BufferedImage para trabajar en forma individual.
     */
    public Sprite(BufferedImage imagen) {
        this.imagen = imagen;
    }

    public BufferedImage getImagen(){
        return imagen;
    }
}
