package tpfinal.interfaz_usuario;

import java.awt.*;

public abstract class BarraEstadistica {
    private static final int alto = 12;
    private static final int largo = 100;

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
