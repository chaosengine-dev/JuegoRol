package tpfinal.interfaz_usuario;

import java.awt.*;

/**
 * Dibuja las barras de estadistica del jugador.
 * Es abstracta porque no necesito instancias de la clase.
 */
public abstract class BarraEstadistica {
    private static final int alto = 12;
    private static final int largo = 100;

    /**
     * Dibuja la barra en la posicion y con el color indicado y del tamaño calculado por el estado
     * actual del personaje sobre el estado maximo.
     * @param x int que indica la posicion sobre el eje x.
     * @param y int que indica la posicion sobre el eje y.
     * @param color indica el color de la barra.
     * @param grafico Objeto graphics donde se dibuja.
     * @param estado int con el valor de ese estado del personaje.
     * @param estadoFull int con el valor maximo de ese estado del personaje.
     */
    public static void dibujar(int x, int y, Color color, Graphics grafico, int estado, int estadoFull) {
        grafico.setColor(Color.WHITE);
        grafico.drawString(""+ estado, largo + 42, y + 12);
        grafico.drawRoundRect(x, y , largo + 1, alto + 1 , 10, 10);
        float porcentajeEstado = (float)(estado * 100) / estadoFull;
        float mostrar = (porcentajeEstado * largo) / 100;
        grafico.setColor(color);
        grafico.fillRoundRect(x + 1, y + 1, (int)mostrar, alto, 10, 10);
    }
}
