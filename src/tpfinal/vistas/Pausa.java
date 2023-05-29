package tpfinal.vistas;

import tpfinal.graficos.SpritesSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

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


    public Pausa(){
        COLOR_BARRA_ESTADO = new Color(240,63,63);
        COLOR_DETALLE = new Color(206,217,193);
        BARRA_ESTADO = new Rectangle(0,0, ancho, 20);
        DETALLE = new Rectangle(0, BARRA_ESTADO.height, ancho, alto);
        this.hojaSprite = new SpritesSheet("Recursos/Pausa/fondopausa.png",alto, ancho, false);
        imagen = hojaSprite.obtenerSprite(0).obtenerImagen();
    }

    @Override
    public void actualizar() {
        // Cartel que circula
        posicion++;
        if (posicion > 800){
            posicion = -300;
        }
    }

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
