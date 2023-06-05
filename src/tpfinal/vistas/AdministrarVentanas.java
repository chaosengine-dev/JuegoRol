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
        ventanasJuegos = new VentanaJuego[11];
        /* Solo construimos las ventanas que no necesitan informacion extra durante la ejecucion
        las otras se construyen durante la ejecucion usando los metodos de abajo.
            0 - Ventana Cargar o Jugar
            1 - Opcion Jugar de la Ventana 0
            2 - Pausa
            3 - Nuevo Personaje
            4 - Batalla
            5 - Game Over
            6 - Fin Juego Ganador
            7 - Formulario Bienvenida
            8 - Registro
            9 - Login
            10 - Iniciar Juego Guardado - Opcion Cargar de Ventana 0
         */
        ventanasJuegos[2] = new Pausa();
        ventanasJuegos[5] = new GameOver();
        ventanasJuegos[6] = new FinJuego();
        ventanasJuegos[7] = new Welcomepage();
    }

    public static void iniciarVentanaBienvenida(){
        ventanasJuegos[0] = new Presentacion(canvasVentanaGlobal);
    }
    public static void iniciarVentanaJuego(){
        if (ventanasJuegos[1] == null){
            ventanasJuegos[1] = new IniciarJuegoNuevo();
        }
    }
    public static void iniciarVentanaNuevo(){
        ventanasJuegos[3] = new Nuevo(canvasVentanaGlobal);
    }
    public static void iniciarVentanaBatalla(Heroe heroe, Enemy enemigo, int ventanaOrigen){
        ventanasJuegos[4] = new Batalla(heroe, enemigo, ventanaOrigen);
    }
    public static void iniciarVentanaRegistro(){
        ventanasJuegos[8] = new Registration();
    }
    public static void iniciarVentanaLogin(){
        ventanasJuegos[9] = new Loginpage();
    }
    public static void iniciarVentanaJuegoSalvado(){
        ventanasJuegos[10] = new IniciarJuegoSalvado();
    }

    // Indico que ventana arranca en vista
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
    public static void setUserRegistered(String user) {
        userRegistered = user;
    }
    public static String getUserRegistered() {
        return userRegistered;
    }

    public static VentanaJuego[] getVentanasJuegos() {
        return ventanasJuegos;
    }
}
