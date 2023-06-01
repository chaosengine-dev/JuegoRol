package tpfinal.mapas;

import tpfinal.objetos.Enemy;
import tpfinal.objetos.Pocion;

import java.awt.*;
import java.util.ArrayList;

public class CapaEnemigos extends Capa{

    private boolean[] colisiones;
    private ArrayList<Rectangle> areasColision = new ArrayList<>();
    private final Point posicionJugador;
    private ArrayList<Enemy> enemigos = new ArrayList<>();
    public CapaEnemigos(String ruta, int ladoSprite) {
        super(ruta, ladoSprite);
        // Colisiones
        colisiones = extraerColisiones(partes[5]);

        String ubicacion = partes[6];
        String[] partesUbicacion = ubicacion.split(",");
        posicionJugador = new Point();
        posicionJugador.x = Integer.parseInt(partesUbicacion[0]);
        posicionJugador.y = Integer.parseInt(partesUbicacion[1]);

        enemigos = crearEnemigos();
    }
    public void actualizar(final int posicionX, final int posicionY){
        actualizarAreasColision(posicionX, posicionY);
    }

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

    public ArrayList<Enemy> getEnemigos() {
        return enemigos;
    }

    public void setEnemigos(ArrayList<Enemy> enemigos) {
        this.enemigos = enemigos;
    }

    public void actualizarObjetos(Enemy enemyCollision) {
        for (Enemy enemy: enemigos
        ) {
            if (enemy.getPosicionX() == enemyCollision.getPosicionX() && enemy.getPosicionY() == enemyCollision.getPosicionY()){
                enemigos.remove(enemy);
                sprites[(enemyCollision.getPosicionX()/ LADO_SPRITE) + (enemyCollision.getPosicionY() / LADO_SPRITE) * this.ancho] = 0;
                break;
            }

        }

    }
}
