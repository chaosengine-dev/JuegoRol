package tpfinal.mapas;

import tpfinal.objetos.Enemy;

import java.awt.*;
import java.util.ArrayList;

/**
 * Capa Enemigos que extiende de la clase Capa.
 * Esta clase tiene además un arryalist de Rectangles con las áreas de colision.
 * y un arraylist de Enemy con cada enemigo del mapa.
 */
public class CapaEnemigos extends Capa{

    private boolean[] colisiones;
    private ArrayList<Rectangle> areasColision = new ArrayList<>();
    private ArrayList<Enemy> enemigos;

    /**
     * El constructor llama al constructor de la clase Capa y parsea el archivo.
     * El constructor padre arma toda la estructura de la capa enemigos, dejando para este constructor
     * las colisiones.
     * @param ruta archivo txt con los datos del mapa de enemigos
     * @param ladoSprite tamaño de los sprites de los enemigos
     */
    public CapaEnemigos(String ruta, int ladoSprite) {
        super(ruta, ladoSprite);
        colisiones = extraerColisiones(partes[5]);
        enemigos = crearEnemigos();
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
     * En cada posicion de la matriz del mapa de Enemigos verifica si hay algun enemigo y si esta activo
     * es decir que no lo derrotamos anteriormente.
     * @param posicionXJugador posicion del jugador sobre el eje X
     * @param posicionYJugador posicion del jugador sobre el eje Y
     */
    private void actualizarAreasColision(final int posicionXJugador, final int posicionYJugador){
        if (!areasColision.isEmpty()){
            areasColision.clear();
        }
        for (int y = 0; y < this.alto; y++){
            for (int x = 0; x < this.ancho; x++){
                for (Enemy enemy: enemigos) {
                    if (enemy.getPosicionX() == x * LADO_SPRITE && enemy.getPosicionY() == y * LADO_SPRITE && enemy.isActiva()){
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
     * Crea un arraylist de enemigos recorriendo el mapa de enemigos leido en la clase Capa
     * @return Devuelve el arraylist de enemigos creados.
     */
    private ArrayList<Enemy> crearEnemigos(){
        ArrayList<Enemy> enemies = new ArrayList<>();
        int id = 0;
        for (int y = 0; y < alto; y++){
            for (int x = 0; x < ancho; x++){
                int indice = sprites[x + y * ancho];
                if (indice != 0){
                    Enemy enemy = new Enemy(id, x * LADO_SPRITE, y * LADO_SPRITE, indice);
                    enemies.add(enemy);
                    id++;
                }
            }
        }
        return enemies;
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
     * Compara el enemigo que se envia por parametro (enemigo vencido) con todos los enenigos, si coincide
     * se elimina del arraylist.
     * @param enemyCollision Enemigo vencido en batalla.
     */
    public void actualizarEnemigos(Enemy enemyCollision) {
        for (Enemy enemy: enemigos
        ) {
            if (enemy.getPosicionX() == enemyCollision.getPosicionX() && enemy.getPosicionY() == enemyCollision.getPosicionY()){
                enemigos.remove(enemy);
                sprites[(enemyCollision.getPosicionX()/ LADO_SPRITE) + (enemyCollision.getPosicionY() / LADO_SPRITE) * this.ancho] = 0;
                break;
            }

        }
    }
    public ArrayList<Rectangle> getAreasColision() {
        return areasColision;
    }
    public ArrayList<Enemy> getEnemigos() {
        return enemigos;
    }
    public void setEnemigos(ArrayList<Enemy> enemigos) {
        this.enemigos = enemigos;
    }

}
