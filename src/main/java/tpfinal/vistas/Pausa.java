package tpfinal.vistas;

import tpfinal.graficos.SpritesSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Clase que dibuja la ventana de Pausa con información sobre los integrantes del grupo.
 */
public class Pausa implements VentanaJuego {
    private int ancho = 800;
    private  int alto = 600;
    private final Color COLOR_BARRA_ESTADO;
    private final Color COLOR_DETALLE;
    private final Rectangle BARRA_ESTADO;
    private final Rectangle DETALLE;
    private SpritesSheet hojaSprite;
    private BufferedImage imagen;
    private int posicion = 0;

    /**
     * El constructor lee la imagen de pausa y la asigna a un BufferedImage
     */
    public Pausa(){
        COLOR_BARRA_ESTADO = new Color(240,63,63);
        COLOR_DETALLE = new Color(206,217,193);
        BARRA_ESTADO = new Rectangle(0,0, ancho, 20);
        DETALLE = new Rectangle(0, BARRA_ESTADO.height, ancho, alto);
        this.hojaSprite = new SpritesSheet("Recursos/Pausa/fondopausa.png",alto, ancho, false);
        imagen = hojaSprite.obtenerSprite(0).getImagen();
    }

    /**
     * Muestra un mensaje que circula arriba de la pantalla.
     */
    @Override
    public void actualizar() {
        // Cartel que circula
        posicion++;
        if (posicion > 800){
            posicion = -300;
        }
    }

    /**
     * Dibuja la ventana, con la imagen que creo el constructor y el mensaje que se ira moviendo segun indique
     * la posicion que cambia el metodo actualizar.
     * @param grafico Objeto del tipo Graphics donde se dibuja nuestro juego.
     */
    @Override
    public void dibujar(Graphics grafico){
        grafico.setColor(COLOR_BARRA_ESTADO);
        grafico.fillRect(BARRA_ESTADO.x, BARRA_ESTADO.y, BARRA_ESTADO.width, BARRA_ESTADO.height);
        grafico.setColor(Color.WHITE);
        grafico.setFont( new Font( "Arial", Font.PLAIN, 12 ) );
        grafico.drawString("Juego pausado... Presione P nuevamente para volver al juego.", posicion, 15);
        grafico.setColor(COLOR_DETALLE);
        grafico.fillRect(DETALLE.x, DETALLE.y, DETALLE.width, DETALLE.height);
        grafico.drawImage(imagen, DETALLE.x, DETALLE.y, null);

    }

}
