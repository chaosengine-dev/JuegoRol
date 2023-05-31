package tpfinal.vistas;

import tpfinal.graficos.CanvasVentana;
import tpfinal.login.loginpage.Loginpage;
import tpfinal.login.registration.Registration;
import tpfinal.login.welcome.Welcomepage;
import tpfinal.objetos.Enemy;
import tpfinal.objetos.Heroe;

import java.awt.*;

public abstract class AdministrarVentanas {

    private static VentanaJuego[] ventanasJuegos;
    private static VentanaJuego ventanaActual;
    private static CanvasVentana canvasVentanaGlobal;
    private static int indice;
    private static String userRegistered;


    public static void gestorVentanas(CanvasVentana canvasVentana){
        canvasVentanaGlobal = canvasVentana;
        iniciarVentanas(canvasVentana);
        iniciarVentanaActual();
    }
    public static void iniciarVentanas(CanvasVentana canvasVentana) {
        indice = 0;
        ventanasJuegos = new VentanaJuego[10];
        ventanasJuegos[0] = new Presentacion(canvasVentana);
        ventanasJuegos[1] = null;
        ventanasJuegos[2] = new Pausa();
        ventanasJuegos[3] = new Nuevo(canvasVentana);
        ventanasJuegos[4] = null;
        ventanasJuegos[5] = new GameOver();
        ventanasJuegos[6] = new FinJuego();
        ventanasJuegos[7] = new Welcomepage();
        ventanasJuegos[8] = null;
        ventanasJuegos[9] = null;
    }

    public static void iniciarVentanaJuego(){
        ventanasJuegos[1] = new Juego();
    }

    public static void iniciarVentanaBatalla(Heroe heroe, Enemy enemigo){
        ventanasJuegos[4] = new Batalla(heroe, enemigo);
    }
    public static void iniciarVentanaBienvenida(){
        ventanasJuegos[0] = new Presentacion(canvasVentanaGlobal);
    }
    public static void iniciarVentanaRegistro(){
        ventanasJuegos[8] = new Registration();
    }
    public static void iniciarVentanaLogin(){
        ventanasJuegos[9] = new Loginpage();
    }
    private static void iniciarVentanaActual() {
        ventanaActual = ventanasJuegos[7];
    }

    public static void actualizar(){
        if (ventanaActual == null){
            gestorVentanas(canvasVentanaGlobal);
        }
        ventanaActual.actualizar();
    }

    public static void dibujar(Graphics grafico){
        if (ventanaActual == null){
            gestorVentanas(canvasVentanaGlobal);
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

    public static void setUserRegistered(String userRegistered) {
        AdministrarVentanas.userRegistered = userRegistered;
        ventanasJuegos[0].setUser(userRegistered);

    }
}
