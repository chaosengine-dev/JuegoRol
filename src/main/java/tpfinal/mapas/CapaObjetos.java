package tpfinal.mapas;

import tpfinal.objetos.Pocion;

import java.awt.*;
import java.util.ArrayList;

/**
 * Capa Objetos que extiende de la clase Capa.
 * Esta clase tiene además un arryalist de Rectangles con las áreas de colision.
 * y un arraylist de Pocion (Objetos como pociones, llaves y cofres) con cada objeto del mapa.
 */
public class CapaObjetos extends Capa{
    private boolean[] colisiones;
    private ArrayList<Rectangle> areasColision = new ArrayList<>();
    private ArrayList<Pocion> pociones;

    /**
     * El constructor llama al constructor de la clase Capa y parsea el archivo.
     * El constructor padre arma toda la estructura de la capa de Objetos, dejando para este constructor
     * las colisiones.
     * @param ruta archivo txt con los datos del mapa de objetos.
     * @param ladoSprite tamaño de los sprites de los objetos.
     */
    public CapaObjetos(String ruta, int ladoSprite){
        super(ruta, ladoSprite);

        // Colisiones
        colisiones = extraerColisiones(partes[5]);
        pociones = crearObjetos();

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
     * En cada posicion de la matriz del mapa de Objetos verifica si hay algun objeto y si esta activo
     * es decir que no lo agregamos al inventario anteriormente.
     * @param posicionXJugador posicion del jugador sobre el eje X
     * @param posicionYJugador posicion del jugador sobre el eje Y
     */
    private void actualizarAreasColision(final int posicionXJugador, final int posicionYJugador){
        if (!areasColision.isEmpty()){
            areasColision.clear();
        }
        for (int y = 0; y < this.alto; y++){
            for (int x = 0; x < this.ancho; x++){
                for (Pocion pocion: pociones) {
                    if (pocion.getPosicionX() == x * 32 && pocion.getPosicionY() == y * 32 && pocion.isActiva()){
                        int puntoX = x * LADO_SPRITE - posicionXJugador + MARGEN_X;
                        int puntoY = y * LADO_SPRITE - posicionYJugador + MARGEN_Y;
                        if (colisiones[x + y * this.ancho]){
                            final Rectangle areaColision = new Rectangle(puntoX, puntoY, LADO_SPRITE, LADO_SPRITE);
                            areasColision.add(areaColision);
                        }
                        break;
                    }
                }
            }
        }
    }
    /**
     * Crea un arraylist de objetos (Pociones, llaves y cofres) recorriendo el mapa de objetos leido en la clase Capa
     * @return Devuelve el arraylist de objetos creados.
     */
    private ArrayList<Pocion> crearObjetos(){
        ArrayList<Pocion> pociones = new ArrayList<>();
        int id = 0;
        for (int y = 0; y < alto; y++){
            for (int x = 0; x < ancho; x++){
                int indice = sprites[x + y * ancho];
                if (indice == 1){
                    Pocion pocion = new Pocion(id, x * LADO_SPRITE, y * LADO_SPRITE, indice);
                    pociones.add(pocion);
                    id++;
                }
                if (indice == 2){
                    Pocion pocion = new Pocion(id, x * LADO_SPRITE, y * LADO_SPRITE,  indice);
                    pociones.add(pocion);
                    id++;
                }
                if (indice == 3){
                    Pocion pocion = new Pocion(id, x * LADO_SPRITE, y * LADO_SPRITE,  indice);
                    pociones.add(pocion);
                    id++;
                }
                if (indice == 4){
                    Pocion pocion = new Pocion(id, x * LADO_SPRITE, y * LADO_SPRITE,  indice);
                    pociones.add(pocion);
                    id++;
                }
                if (indice == 5){
                    Pocion pocion = new Pocion(id, x * LADO_SPRITE, y * LADO_SPRITE,  indice);
                    pociones.add(pocion);
                    id++;
                }
                if (indice == 6){
                    Pocion pocion = new Pocion(id, x * LADO_SPRITE, y * LADO_SPRITE,  indice);
                    pociones.add(pocion);
                    id++;
                }
                if (indice == 7){
                    Pocion pocion = new Pocion(id, x * LADO_SPRITE, y * LADO_SPRITE,  indice);
                    pociones.add(pocion);
                    id++;
                }
            }
        }
        return pociones;
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
    /**
     * Compara el Objeto que se envia por parametro (Pocion, Llave o cofre) con todos los objetos, si coincide
     * se elimina del arraylist.
     * @param pocionColision Objeto que se obtiene del mapa y se agrega al inventario.
     */
    public void actualizarObjetos(Pocion pocionColision) {
        for (Pocion pocion: pociones
        ) {
            if (pocion.getPosicionX() == pocionColision.getPosicionX() && pocion.getPosicionY() == pocionColision.getPosicionY()){
                pociones.remove(pocion);
                sprites[(pocionColision.getPosicionX()/ LADO_SPRITE) + (pocionColision.getPosicionY() / LADO_SPRITE) * this.ancho] = 0;
                break;
            }
        }
    }

    public ArrayList<Rectangle> getAreasColision() {
        return areasColision;
    }
    public ArrayList<Pocion> getPociones() {
        return pociones;
    }
    public void setPociones(ArrayList<Pocion> pociones) {
        this.pociones = pociones;
    }
}
