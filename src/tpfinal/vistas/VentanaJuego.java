package tpfinal.vistas;

import java.awt.*;

public interface VentanaJuego {
    void actualizar();
    void dibujar(Graphics grafico);

    default void setUser(String user){

    };
}
