package tpfinal.mapas;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Capa Mapa que extiende de la clase Capa.
 * Esta clase tiene además un arryalist de Rectangles con las áreas de colision.
 * Como el mapa interactua con el jugador tiene un objeto Point con la posicion del Jugador.
 */
public class CapaMapa extends Capa {
    private boolean[] colisiones;
    private ArrayList<Rectangle> areasColision = new ArrayList<>();
    private final Point posicionJugador;

    /**
     * El constructor llama al constructor de la clase Capa y parsea el archivo.
     * El constructor padre arma toda la estructura de la capa mapa, dejando para este constructor
     * las colisiones.
     * @param ruta archivo txt con los datos del mapa principal.
     * @param ladoSprite tamaño de los sprites del mapa.
     */
    public CapaMapa(final String ruta, int ladoSprite){
        super(ruta, ladoSprite);
        // Colisiones
        colisiones = extraerColisiones(partes[5]);

        String ubicacion = partes[6];
        String[] partesUbicacion = ubicacion.split(",");
        posicionJugador = new Point();
        posicionJugador.x = Integer.parseInt(partesUbicacion[0]);
        posicionJugador.y = Integer.parseInt(partesUbicacion[1]);

    }

    /**
     * Actualiza segun la posicion del jugador las areas de colision.
     * Como el mapa se va mostrando por partes, las areas de colision deben ir cambiando.
     * @param posicionX posicion sobre el eje X del jugador.
     * @param posicionY posicion sobre el eje Y del jugador.
     */
    public void actualizar(final int posicionX, final int posicionY){
        actualizarAreasColision(posicionX, posicionY);
    }

    /**
     * Este metodo actualiza las colisiones, si el arraylist tiene datos lo vacia para volver a empezar.
     * En cada posicion de la matriz del mapa principal verifica si hay algun objeto que no pueda atravesar.
     * @param posicionXJugador posicion del jugador sobre el eje X
     * @param posicionYJugador posicion del jugador sobre el eje Y
     */
    private void actualizarAreasColision(final int posicionXJugador, final int posicionYJugador){
        if (!areasColision.isEmpty()){
            areasColision.clear();
        }
        for (int y = 0; y < this.alto; y++){
            for (int x = 0; x < this.ancho; x++){
                int puntoX = x * LADO_SPRITE - posicionXJugador + MARGEN_X;
                int puntoY = y * LADO_SPRITE - posicionYJugador + MARGEN_Y;
                if (colisiones[x + y * this.ancho]){
                    final Rectangle areaColision = new Rectangle(puntoX, puntoY, LADO_SPRITE, LADO_SPRITE);
                    areasColision.add(areaColision);
                }
            }
        }
    }

    /**
     * Recorre el arreglo de sprites y los dibuja en pantalla.
     * Siempre dibuja el mapa en relacion a la ubicacion del jugador.
     * @param grafico Objeto graphics donde se dibuja nuestra imagen.
     * @param posicionXJugador posicion del jugador sobre el eje X
     * @param posicionYJugador posicion del jugador sobre el eje Y
     */
    public void dibujar(Graphics grafico, int posicionXJugador, int posicionYJugador){
        for (int y = 0; y < alto; y++){
            for (int x = 0; x < ancho; x++){
                int indice = sprites[x + y * ancho];
                BufferedImage imagen = paletas[indice].getImagen();
                int puntoX = x * LADO_SPRITE - posicionXJugador + MARGEN_X;
                int puntoY = y * LADO_SPRITE - posicionYJugador + MARGEN_Y;
                grafico.drawImage(imagen, puntoX, puntoY, null);
            }
        }

    }

    /**
     * Obtiene el ancho del mapa a mostrar a partir de la ubicacion del jugador y su tamaño
     * @param posicionX posicion del jugador sobre el eje X
     * @param posicionY posicion del jugador sobre el eje Y
     * @param anchoJugador tamaño en pixels del ancho del jugador, pero solo la mitad ya que no todo el jugador colisiona
     * @param altoJugador tamaño en pixels del alto del jugador, pero solo la mitad ya que no todo el jugador colisiona
     * @return Rectangulo con limites del mapa.
     */
    public Rectangle obtenerBordes(final int posicionX, final int posicionY, final int anchoJugador, final int altoJugador){
        int x = MARGEN_X - posicionX + anchoJugador;
        int y = MARGEN_Y - posicionY + altoJugador;
        int ancho = this.ancho * LADO_SPRITE - anchoJugador * 2;
        int alto = this.alto * LADO_SPRITE - altoJugador * 2;
        return new Rectangle(x,y, ancho, alto);
    }
    /**
     * Recorre el string que almaceno las colisiones en el constructor y crea un
     * arreglo de booleanos con true si encuentra un 1 o false si encuentra un 0.
     * @param textoColisiones string leido en el constructor de la clase padre Capa
     * @return Arreglo de boolean construido a partir del mapa de colisiones.
     */
    private boolean[] extraerColisiones(String textoColisiones){
        boolean[] colisiones = new boolean[textoColisiones.length()];
        for (int i = 0; i < textoColisiones.length(); i++){
            if (textoColisiones.charAt(i) == '0'){
                colisiones[i] = false;
            } else {
                colisiones[i] = true;
            }
        }
        return colisiones;
    }
    public ArrayList<Rectangle> getAreasColision() {
        return areasColision;
    }

    public Point getPosicionJugador() {
        return posicionJugador;
    }
}
