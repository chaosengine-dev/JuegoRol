package tpfinal.vistas;

import tpfinal.admin.adminpage.AdminPage;
import tpfinal.admin.manageusers.ManageUsers;
import tpfinal.graficos.CanvasVentana;
import tpfinal.login.loginpage.Loginpage;
import tpfinal.login.registration.Registration;
import tpfinal.login.welcome.Welcomepage;
import tpfinal.objetos.Enemy;
import tpfinal.objetos.Heroe;

import java.awt.*;

/**
 * Clase que gestiona las ventanas activas
 * No hay instancias de esta clase por eso es abstracta.
 * Tenemos un Arreglo del tipo VentanaJuego que es una interface asi podemos meter
 * distintos tipos de ventanas en el mismo arreglo.
 */
public abstract class AdministrarVentanas {

    private static VentanaJuego[] ventanasJuegos;
    private static VentanaJuego ventanaActual;
    private static CanvasVentana canvasVentanaGlobal;
    private static int indice;
    private static String userRegistered;

    /**
     * Inicia el gestor de ventanas con el canvas principal.
     * Inicia las ventanas, agregandolas al arreglo.
     * Muestra la primera ventana.
     * @param canvasVentana Canvas principal generado en la clase JuegoRol
     */
    public static void gestorVentanas(CanvasVentana canvasVentana){
        canvasVentanaGlobal = canvasVentana;
        iniciarVentanas();
        iniciarJuego();
    }

    /**
     * Crea el arreglo de ventanas disponibles durante la ejecucion del juego.
     * Solo construimos las ventanas que no necesitan información extra durante la ejecucion
     * las otras se construyen durante la ejecucion usando los metodos de abajo.
     *             0 - Ventana Cargar o Jugar
     *             1 - Opcion Jugar de la Ventana 0
     *             2 - Pausa
     *             3 - Nuevo Personaje
     *             4 - Batalla
     *             5 - Game Over
     *             6 - Fin Juego Ganador
     *             7 - Formularia Bienvenida
     *             8 - Registro
     *             9 - Login
     *             10 - Iniciar Juego Guardado - Opcion Cargar de Ventana 0
     *             11 - Ventana Admin
     *             12 - Gestionar Usuarios
     */
    private static void iniciarVentanas() {
        indice = 0;
        ventanasJuegos = new VentanaJuego[13];
        ventanasJuegos[0] = null;
        ventanasJuegos[1] = null;
        ventanasJuegos[2] = new Pausa();
        ventanasJuegos[5] = new GameOver();
        ventanasJuegos[6] = new FinJuego();
        ventanasJuegos[7] = new Welcomepage();
        ventanasJuegos[8] = null;
        ventanasJuegos[9] = null;
        ventanasJuegos[10] = null;
        ventanasJuegos[11] = null;
        ventanasJuegos[12] = null;
    }

    /**
     * Indica que la ventana de inicio del juego es la que está en la posicion 7 del arreglo.
     * La ventana de Bienvenida, clase WelcomePage.
     */
    private static void iniciarJuego() {
        ventanaActual = ventanasJuegos[7];
    }
    /**
     * Crea la ventana de Presentacion posterior al logue o registro en el juego.
     */
    public static void iniciarVentanaBienvenida(){
        ventanasJuegos[0] = new Presentacion(canvasVentanaGlobal);
    }
    /**
     * Crea la ventana para iniciar un juego nuevo, donde se elige el personaje.
     */
    public static void iniciarVentanaJuego(int playerSelected){
        if (ventanasJuegos[1] == null){
            ventanasJuegos[1] = new IniciarJuegoNuevo(playerSelected);
        }
    }
    /**
     * Crea la ventana con un juego nuevo, luego de elegir el personaje en la ventana JuegoNuevo.
     */
    public static void iniciarVentanaNuevo(){
        ventanasJuegos[3] = new Nuevo(canvasVentanaGlobal);
    }
    /**
     * Crea la ventana de Batalla
     * @param heroe Datos del Heroe (jugador) para obtener su información.
     * @param enemigo Datos del Enemigo en la batalla.
     * @param ventanaOrigen Ventana que llama a la Batalla, puede ser una VentanaNueva o una VentanaJuegoSalvado
     */
    public static void iniciarVentanaBatalla(Heroe heroe, Enemy enemigo, int ventanaOrigen){
        ventanasJuegos[4] = new Batalla(heroe, enemigo, ventanaOrigen);
    }
    /**
     * Crea la ventana de Registro de usuario
     */
    public static void iniciarVentanaRegistro(){
        ventanasJuegos[8] = new Registration();
    }
    /**
     * Crea la ventana para iniciar con cuenta de usuario
     */
    public static void iniciarVentanaLogin(){
        ventanasJuegos[9] = new Loginpage();
    }
    /**
     * Inicia la ventana de una partida guardada.
     */
    public static void iniciarVentanaJuegoSalvado(){
        ventanasJuegos[10] = new IniciarJuegoSalvado();
    }
    /**
     * Inicia la ventana de Administrador, donde se puede elegir Jugar o Administrar Usuarios
     */
    public static void iniciarVentanaAdmin(){ventanasJuegos[11] = new AdminPage();}
    /**
     * Crea la ventana de administrador de usuarios.
     */
    public static void iniciarVentanaGestionUsuarios(){ventanasJuegos[12] = new ManageUsers();}
    /**
     * Si no hay ventana actual, es decir no se creó el arreglo de ventanas y se asignó una ventana
     * se crea el arreglo llamando al gestorVentanas.
     * Si no llama al actualizar de la ventanaActual.
     */
    public static void actualizar(){
        if (ventanaActual == null){
            gestorVentanas(canvasVentanaGlobal);
        }
        ventanaActual.actualizar();
    }
    /**
     * Si no hay ventana actual, es decir no se creó el arreglo de ventanas y se asignó una ventana
     * se crea el arreglo llamando al gestorVentanas.
     * Si no llama al dibujar de la ventanaActual.
     * @param grafico Objeto del tipo Graphics donde dibujamos.
     */
    public static void dibujar(Graphics grafico){
        if (ventanaActual == null){
            gestorVentanas(canvasVentanaGlobal);
        }
        ventanaActual.dibujar(grafico);

    }
    /**
     * Se encarga de intercambiar el estado de la ventana.
     * Recibe el índice del arreglo que debe estar activa.
     * @param nuevoEstado int con el indice del arreglo de la ventana a mostrar.
     */
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
}
