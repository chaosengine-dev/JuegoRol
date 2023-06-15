package tpfinal.vistas;

import tpfinal.control.Musica;
import tpfinal.graficos.CanvasVentana;
import tpfinal.graficos.SpritesSheet;
import tpfinal.persistencia.PersistenciaJson;
import tpfinal.login.models.SavedGame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Clase que crea la ventana de Presentacion, es un fondo con 2 botones, uno para crea un juego nuevo
 * y otro para carga una partida guardada.
 */
public class Presentacion implements VentanaJuego {
    private int ancho = 800;
    private  int alto = 600;
    private SpritesSheet hojaSprite;
    private BufferedImage imagen;
    private Rectangle botonEntrar;
    private Rectangle botonCargar;
    private CanvasVentana canvasVentana;

    /**
     * El constructor lee la imagen a mostrar y la guarda en un BufferedImage.
     * Crea los rectangles que indican la ubicacion de los botones para chequear el click del mouse.
     * @param canvasVentana
     */
    public Presentacion(CanvasVentana canvasVentana){

        this.hojaSprite = new SpritesSheet("Recursos/Presentacion/presentacion.png",alto, ancho, false);
        imagen = hojaSprite.obtenerSprite(0).getImagen();
        botonCargar = new Rectangle(210,500,120,40);
        botonEntrar = new Rectangle(440,500,120,40);
        this.canvasVentana = canvasVentana;
    }

    /**
     * Dibuja la imagen que fue guardada en el buffer por el constructor.
     * @param grafico
     */
    @Override
    public void dibujar(Graphics grafico){
        grafico.drawImage(imagen, 0, 0, null);
    }

    /**
     * Verifica en cada iteracion, la actividad del mouse.
     * Si hace click y en que boton lo hace para iniciar un juego nuevo o carga una partida guardada.
     */
    @Override
    public void actualizar(){

        if (canvasVentana.getRaton().isClick() ){

            Point ubicacionVentana = canvasVentana.getPosicionVentana();
            Rectangle mouseRelativo = new Rectangle(MouseInfo.getPointerInfo().getLocation().x - ubicacionVentana.x, MouseInfo.getPointerInfo().getLocation().y - ubicacionVentana.y, 1, 1);
            if (mouseRelativo.intersects(botonCargar)){
                // Cargar partida desde archivo y mostrar
                if (existeSavedGame()){
                    AdministrarVentanas.iniciarVentanaJuegoSalvado();
                    AdministrarVentanas.cambiarEstadoActual(10);
                } else {
                    // Si no existe una parte salvada creo juego nuevo
                    AdministrarVentanas.iniciarVentanaNuevo();
                    AdministrarVentanas.cambiarEstadoActual(3);
                }

            }
            if (mouseRelativo.intersects(botonEntrar)){
                AdministrarVentanas.iniciarVentanaNuevo();
                AdministrarVentanas.cambiarEstadoActual(3);
            }
            Musica.clickBoton();

        }
        canvasVentana.getRaton().resetClick();
    }

    /**
     * Verifica si el usuario tiene una partida guardada, si apreto el boton cargar y la partida existe
     * Comienza el juego desde donde lo dejo y devuelve true.
     * Si no hat partida guardada, advierte al usuario que falta la partida y crea un juego nuevo.
     * @return True si hay partida guardada, false si no existe.
     */
     private boolean existeSavedGame(){
         // Leer json
         PersistenciaJson leerSavedGame = new PersistenciaJson();
         String path = "Recursos/SavedGames/" + AdministrarVentanas.getUserRegistered() + ".json";
         SavedGame partida = leerSavedGame.deserializar(path, SavedGame.class);
         if (partida == null){
             JOptionPane.showMessageDialog(null, "No tiene partes guardadas, iniciando juego nuevo.");
             return false;
         }
         return true;
     }
}
