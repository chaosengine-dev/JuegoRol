package tpfinal.mapas;

import tpfinal.objetos.Pocion;

import java.awt.*;
import java.util.ArrayList;

public class CapaObjetos extends Capa{
    private boolean[] colisiones;
    private ArrayList<Rectangle> areasColision = new ArrayList<>();
    private final Point posicionJugador;
    private ArrayList<Pocion> pociones = new ArrayList<>();

    public CapaObjetos(String ruta, int ladoSprite){
        super(ruta, ladoSprite);

        // Colisiones
        colisiones = extraerColisiones(partes[5]);

        String ubicacion = partes[6];
        String[] partesUbicacion = ubicacion.split(",");
        posicionJugador = new Point();
        posicionJugador.x = Integer.parseInt(partesUbicacion[0]);
        posicionJugador.y = Integer.parseInt(partesUbicacion[1]);

        pociones = crearObjetos();

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

    public ArrayList<Pocion> getPociones() {
        return pociones;
    }

    public void setPociones(ArrayList<Pocion> pociones) {
        this.pociones = pociones;
    }

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


}
