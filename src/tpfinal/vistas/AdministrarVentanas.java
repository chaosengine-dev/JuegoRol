package tpfinal.vistas;

import tpfinal.graficos.CanvasVentana;
import tpfinal.objetos.Enemy;
import tpfinal.objetos.Heroe;

import java.awt.*;

public abstract class AdministrarVentanas {

    private static VentanaJuego[] ventanasJuegos;
    private static VentanaJuego ventanaActual;
    private static CanvasVentana canvasVentana;
    private static int indice;

    public static void gestorVentanas(CanvasVentana canvasVentana){
        iniciarVentanas(canvasVentana);
        iniciarVentanaActual();
    }
    public static void iniciarVentanas(CanvasVentana canvasVentana) {
        indice = 0;
        ventanasJuegos = new VentanaJuego[7];
        ventanasJuegos[0] = new Presentacion(canvasVentana);
        ventanasJuegos[1] = null;
        ventanasJuegos[2] = new Pausa();
        ventanasJuegos[3] = new Nuevo(canvasVentana);
        ventanasJuegos[4] = null;
        ventanasJuegos[5] = new GameOver();
        ventanasJuegos[6] = new FinJuego();
    }
    public static void iniciarVentanaJuego(){
        ventanasJuegos[1] = new Juego();
    }

    public static void iniciarVentanaBatalla(Heroe heroe, Enemy enemigo){
        ventanasJuegos[4] = new Batalla(heroe, enemigo);
    }
    private static void iniciarVentanaActual() {
        ventanaActual = ventanasJuegos[0];
    }

    public static void actualizar(){
        if (ventanaActual == null){
            gestorVentanas(canvasVentana);
        }
        ventanaActual.actualizar();
    }

    public static void dibujar(Graphics grafico){
        if (ventanaActual == null){
            gestorVentanas(canvasVentana);
        }
        ventanaActual.dibujar(grafico);

    }

    public static void cambiarEstadoActual(int nuevoEstado){
        ventanaActual = ventanasJuegos[nuevoEstado];
        indice = nuevoEstado;
    }

    public static int getIndice(){
        return indice;
    }

}
