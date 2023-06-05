package tpfinal.mapas;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class CapaMapa extends Capa {
    private boolean[] colisiones;
    private ArrayList<Rectangle> areasColision = new ArrayList<>();
    private final Point posicionJugador;

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

    public void actualizar(final int posicionX, final int posicionY){
        actualizarAreasColision(posicionX, posicionY);
    }

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
    public void dibujar(Graphics grafico, int posicionXJugador, int posicionYJugador){
        for (int y = 0; y < alto; y++){
            for (int x = 0; x < ancho; x++){
                int indice = sprites[x + y * ancho];
                BufferedImage imagen = paletas[indice].obtenerImagen();
                int puntoX = x * LADO_SPRITE - posicionXJugador + MARGEN_X;
                int puntoY = y * LADO_SPRITE - posicionYJugador + MARGEN_Y;
                grafico.drawImage(imagen, puntoX, puntoY, null);
            }
        }

    }
    public Rectangle obtenerBordes(final int posicionX, final int posicionY, final int anchoJugador, final int altoJugador){
        int x = MARGEN_X - posicionX + anchoJugador;
        int y = MARGEN_Y - posicionY + altoJugador;
        int ancho = this.ancho * LADO_SPRITE - anchoJugador * 2;
        int alto = this.alto * LADO_SPRITE - altoJugador * 2;
        return new Rectangle(x,y, ancho, alto);
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
}
