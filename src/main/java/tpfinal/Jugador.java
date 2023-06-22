package tpfinal;

import tpfinal.control.Controles;
import tpfinal.mapas.CapaEnemigos;
import tpfinal.mapas.CapaObjetos;
import tpfinal.mapas.CapaMapa;
import tpfinal.graficos.SpritesSheet;
import tpfinal.objetos.*;
import tpfinal.persistencia.PersistenciaJson;
import tpfinal.login.models.SavedGame;
import tpfinal.vistas.AdministrarVentanas;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Clase que define al jugador, sus movimientos y comportamientos, colisiones con objetos y enemigos.
 *
 */
public class Jugador {
    private int ancho = 800;
    private int alto = 600;
    private int centroVentanaX = ancho / 2;
    private int centroVentanaY = alto / 2;
    private int ladoSprite = 32;
    private int ladoSpriteGrande = 64;
    private SpritesSheet hojaSprite;
    private BufferedImage imagen;
    private int posicionX;
    private int posicionY;
    private int direccion;
    private boolean enMovimiento;
    private int animacion;
    private int estado;
    private double velocidad = 1;
    // BORDES DE COLISION DEL JUGADOR, ES UN RECTANGULO DESDE LA MITAD HASTA ABAJO Y UN POCO MAS ANGOSTO QUE EL SPRITE
    //region BORDES COLISION
    private final int ALTO_COLISION_JUGADOR = 16;
    private final int ANCHO_COLISION_JUGADOR = 16;
    private final Rectangle LIMITE_ARRIBA = new Rectangle(centroVentanaX - 8,
            centroVentanaY, ladoSprite / 2, 1);
    private final Rectangle LIMITE_ABAJO = new Rectangle(centroVentanaX - ANCHO_COLISION_JUGADOR /2,
            centroVentanaY + ALTO_COLISION_JUGADOR, ANCHO_COLISION_JUGADOR, 1);
    private final Rectangle LIMITE_IZQUIERDA = new Rectangle(centroVentanaX - ANCHO_COLISION_JUGADOR/2,
            centroVentanaY,  1, ANCHO_COLISION_JUGADOR);
    private final Rectangle LIMITE_DERECHA = new Rectangle(centroVentanaX + ANCHO_COLISION_JUGADOR/2,
            centroVentanaY, 1, ANCHO_COLISION_JUGADOR);
    //endregion
    private int tiempoRecuperacion = 0; // Tiempo que tarda en empezar a recuperar la resistencia.
    private boolean resistenciaFull = true; // Verifico que este recuperado al 100%
    private CapaMapa capaMapa;
    private CapaObjetos capaObjetos;
    private Enemy enemyEnColision;
    private Pocion pocionEnColision;
    private CapaEnemigos capaEnemigos;
    private int[] inventario;

    // Variables que luego debo pasar por parametro
    private int recuperacionMaxima = 300;
    private int resistenciaMaxima;
    private int experienciaMaxima;
    private int fuerzaMaxima;
    private boolean enBatalla = false;
    private int ventanaOrigen;
    private Heroe heroe;
    public Jugador(){

    }

    /**
     * Constructor de la clase Jugador.
     * @param posicionX Posicion en el eje X del jugador con respecto al mapa.
     * @param posicionY Posicion en el eje Y del jugador con respecto al mapa.
     * @param capaMapa Instancia de la clase CapaMapa, tiene todo el mapa del juego.
     * @param capaObjetos Instancia de la clase CapaObjetos que incluye posiones, llaves y cofre del final.
     * @param capaEnemigos Instancia de la clase CapaEnemigos donde se dibujan los Enemigos.
     * @param heroe Instancia del tipo de jugador, puede ser Guerrero, Elfo o Mago que implementan la interface Heroe.
     * @param ventanaOrigen int que tiene la ventana desde donde llamo a la batalla para luego poder volver a restaurarla.
     */
    public Jugador(int posicionX, int posicionY, CapaMapa capaMapa, CapaObjetos capaObjetos, CapaEnemigos capaEnemigos, Heroe heroe, int ventanaOrigen){
        this.ventanaOrigen = ventanaOrigen;
        this.inventario = new int[]{0,0,0,0,0,0};
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.capaMapa = capaMapa;
        this.capaObjetos = capaObjetos;
        this.capaEnemigos = capaEnemigos;
        this.direccion = 0;
        this.enMovimiento = false;
        this.heroe = heroe;

        experienciaMaxima = heroe.getExperienciaMaxima();
        fuerzaMaxima = heroe.getFuerzaMaxima();
        resistenciaMaxima = heroe.getResistenciaMaxima();

        if (heroe instanceof Guerrero){
            this.hojaSprite = new SpritesSheet("Recursos/Personajes/guerrero.png", ladoSprite, ladoSprite, false);
        }
        if (heroe instanceof Mago){
            this.hojaSprite = new SpritesSheet("Recursos/Personajes/mago.png", ladoSprite, ladoSprite, false);
        }
        if (heroe instanceof Elfo){
            this.hojaSprite = new SpritesSheet("Recursos/Personajes/elfo.png", ladoSprite, ladoSprite, false);
        }
        imagen = hojaSprite.obtenerSprite(1).getImagen();
    }

    /**
     * Este metodo es llamado en la iteracion del juego y dibuja el mapa en relacion al jugador.
     * Si no está en batalla dibuja el mapa
     * Si está en batalla crea la ventana batalla y comienza la misma.
     * @param grafico es la imagen que se obtiene del Buffered Image del canvas y nos permite dibujar sobre
     *                el canvas creado en la clase JuegoRol.
     */
    public void dibujar(Graphics grafico){
        if (!enBatalla){
            final int centroX = (ancho / 2) - (ladoSprite /2);
            final int centroY = (alto / 2) - (ladoSprite /2);
            grafico.drawImage(imagen, centroX, centroY, null);
        } else {
            crearVentanaBatalla(ventanaOrigen);
            enBatalla = false;
            desactivarEnemigo();
        }
    }

    /**
     * Crea la ventana de Batallas enviando los datos del jugador y el enemigo, luego cambia el estado de la vista
     * a la ventana de Batalla.
     * @param ventanaOrigen int que indica desde que ventana llamo a la batalla para poder volver.
     */
    private void crearVentanaBatalla(int ventanaOrigen){
        AdministrarVentanas.iniciarVentanaBatalla(heroe, enemyEnColision, ventanaOrigen);
        AdministrarVentanas.cambiarEstadoActual(4);
    }

    /**
     * Se encarga de actualizar el estado del jugador siempre que no esté en Batalla.
     * Evalua Velocidad y Resistencia (Si está corriendo y durante cuanto tiempo corre).
     * Cambia el estado de la animacion (sprite del movimiento).
     * Determina la direccion del movimiento (izquierda, derecha, arriba, abajo).
     * Anima al jugador (cambiando el icono segun estado).
     * Usar las pociones, siempre que el listener del teclado detecte que se apreto 1, 2 o 3.
     * Llama a guardarPartida, siempre que el listener del teclado detecte que se apreta F2.
     */
    public void actualizar(){
        if (!enBatalla) {
            evaluarVelocidadYResistencia();
            cambiarAnimacionEstado();
            enMovimiento = false;
            determinarDireccion();
            animar();
            usarPocion();
            guardarPartida();
        }
    }

    /**
     * Si se presionó la tecla F2 hace la persistencia de la partida actual.
     * Crea el objeto de persistencia, obtiene el nombre del usuario para crear el json.
     * Crea el objeto a serializar con los datos de la partida actual.
     * Guarda el archivo con nuestro objeto.
     */
    private void guardarPartida(){
        if (Controles.teclado.isSaveGame()){
            Controles.teclado.setSaveGame(false);
            // Guardar juego en jackson
            PersistenciaJson serializar = new PersistenciaJson();
            String usuario = AdministrarVentanas.getUserRegistered();

            SavedGame savedGame = new SavedGame(usuario, (int)this.getPosicionX(),
                    (int)this.getPosicionY(), heroe.getTipo(), heroe.getFuerza(), heroe.getResistencia(),
                    heroe.getExperiencia(), inventario[0], inventario[1], inventario[2],
                    inventario[3] == 1,inventario[4] == 1, inventario[5] == 1,
                    capaObjetos.getPociones(), capaEnemigos.getEnemigos());

            String path = "Recursos/SavedGames/" + usuario + ".json";
            serializar.serializar(savedGame, path);

        }
    }

    /**
     * Este metodo cambia la animacion del jugador, cada jugador tiene 12 imagenes segun el movimiento y la posicion.
     * Animacion se incrementa hasta 30 y vuelve a 0, durante los primeros 15 valores es un estado, durante los otros 15 valores es otro estado.
     * Estado es la imagen que se va a mostrar de la hoja de sprites del jugador.
     * Este metodo se ejecuta tantas veces en un segundo como veces se ejecuta el actualizar.
     */
    private void cambiarAnimacionEstado(){
        if (animacion < 30) {
            animacion++;
        } else {
            animacion = 0;
        }

        if (animacion < 15) {
            estado = 1;
        } else {
            estado = 2;
        }
    }

    /**
     * Determina la direccion del jugador si es arriba, abajo, izquierda y derecha.
     * Además, rompe el movimiento en diagonal.
     */
    private void determinarDireccion() {
        final int direccionX = indicarDireccionX();
        final int direccionY = indicarDireccionY();

        // Esta quieto
        if (direccionX == 0 && direccionY == 0) {
            return;
        }

        // Con estos if buscamos romper el movimiento en diagonal.
        if ((direccionX != 0 && direccionY == 0) || (direccionX == 0 && direccionY != 0)) {
            mover(direccionX, direccionY);
        } else {
            // izquierda y arriba
            if (direccionX == -1 && direccionY == -1) {
                if (Controles.teclado.getIzquierda().obtenerUltimaPulsacion() > Controles.teclado.getArriba()
                        .obtenerUltimaPulsacion()) {
                    mover(direccionX, 0);
                } else {
                    mover(0, direccionY);
                }
            }
            // izquierda y abajo
            if (direccionX == -1 && direccionY == 1) {
                if (Controles.teclado.getIzquierda().obtenerUltimaPulsacion() > Controles.teclado.getAbajo()
                        .obtenerUltimaPulsacion()) {
                    mover(direccionX, 0);
                } else {
                    mover(0, direccionY);
                }
            }
            // derecha y arriba
            if (direccionX == 1 && direccionY == -1) {
                if (Controles.teclado.getDerecha().obtenerUltimaPulsacion() > Controles.teclado.getArriba()
                        .obtenerUltimaPulsacion()) {
                    mover(direccionX, 0);
                } else {
                    mover(0, direccionY);
                }
            }
            // derecha y abajo
            if (direccionX == 1 && direccionY == 1) {
                if (Controles.teclado.getDerecha().obtenerUltimaPulsacion() > Controles.teclado.getAbajo()
                        .obtenerUltimaPulsacion()) {
                    mover(direccionX, 0);
                } else {
                    mover(0, direccionY);
                }
            }
        }
    }

    /**
     * Se fija si el jugador esta corriendo, entonces aumenta la velocidad y empieza a consumir resistencia.
     * Cuando la resistencia llega a 0 ya no puede correr mas, en ese momento la velocidad pasa a 1 y comienza a recuperar resistencia.
     */
    private void evaluarVelocidadYResistencia(){ // Si tiene resistencia va a poder aumentar la velocidad
        if (Controles.teclado.isCorriendo() && heroe.getResistencia() > 0){
            velocidad = 2;
            resistenciaFull = false;
            tiempoRecuperacion = 0;
        } else {
            velocidad = 1;
            if (!resistenciaFull && tiempoRecuperacion < recuperacionMaxima){
                tiempoRecuperacion++;
            }
            if (tiempoRecuperacion == recuperacionMaxima && heroe.getResistencia() < resistenciaMaxima){
                heroe.setResistencia(heroe.getResistencia() + 1);
            }
        }
    }
    private int indicarDireccionX() {
        int direccionX = 0;
        if (Controles.teclado.getIzquierda().estaPulsada()) {
            direccionX = -1;
        } else if (Controles.teclado.getDerecha().estaPulsada()) {
            direccionX = 1;
        }
        return direccionX;
    }
    private int indicarDireccionY() {
        int direccionY = 0;
        if (Controles.teclado.getArriba().estaPulsada() ) {
            direccionY = -1;

        } else if (Controles.teclado.getAbajo().estaPulsada() ) {
            direccionY = 1;
        }
        return direccionY;
    }

    /**
     * Determina el movimiento del jugador. Verifica si colisiona con una pociom, llave, cofre o enemigo.
     * @param direccionX Direccion del jugador en el eje X (izquierda o derecha)
     * @param direccionY Direccion del jugador en el eje Y (Arriba o abajo)
     */
    private void mover(final int direccionX, final int direccionY) {
        enMovimiento = true;
        cambiarDireccion(direccionX, direccionY);

        if (!fueraMapa(direccionX, direccionY)){
            if (direccionX == -1 && !enColisionIzquierda(direccionX)) {
                posicionX += direccionX * velocidad;
            }
            if (direccionX == 1 && !enColisionDerecha(direccionX)) {
                posicionX += direccionX * velocidad;
            }
            if (direccionY == -1 && !enColisionArriba(direccionY)) {
                posicionY += direccionY * velocidad;
            }
            if (direccionY == 1 && !enColisionAbajo(direccionY)) {
                posicionY += direccionY * velocidad;
            }
            if (Controles.teclado.isCorriendo() && heroe.getResistencia() > 0){
                heroe.setResistencia(heroe.getResistencia() - 1);
                heroe.setResistencia(heroe.getResistencia() - 1);
            }
            if (enColisionPocion()){
                if (pocionEnColision.getTipo() == 7){
                    System.out.println("choque con caja");
                    // Cobre pueden pasar 2 cosas que tenga las tres llaves o que me falte alguna y no pase nada.
                    if (inventario[3] == 1 && inventario[4] == 1 && inventario[5] == 1){
                        AdministrarVentanas.cambiarEstadoActual(6);
                    }
                } else {
                    // Resto objetos
                    incorporarPocionInventario();
                    desactivarPocion();
                }
            }
            if (enColisionEnemigos()){
                enBatalla = true;
            }
        }

    }

    /**
     * Verifica hacia donde se mueve el personaje para cambiar el sprite adecuado al movimiento.
     * @param direccionX Direccion del jugador en el eje X (izquierda o derecha)
     * @param direccionY Direccion del jugador en el eje Y (Arriba o abajo)
     */
    private void cambiarDireccion(final int direccionX, final int direccionY) {
        // En direccion guardo un entero que luego me indica que version del sprite dibujo en pantalla
        if (direccionX == -1) {
            direccion = 1;
        } else if (direccionX == 1) {
            direccion = 2;
        }

        if (direccionY == -1) {
            direccion = 3;
        } else if (direccionY == 1) {
            direccion = 0;
        }
    }

    /**
     * Cambia el sprite segun el estado y la direccion. Los sprites son como una matriz, el estado y la direccion indican
     * fila y columna de esa matriz.
     */
    private void animar(){
        if (!enMovimiento) {
            estado = 0;
            animacion = 0;
        }
        imagen = hojaSprite.obtenerSprite(estado, direccion ).getImagen();
    }
    // Colisiones con la capa Pociones
    /**
     * Verifica si el personaje esta en colision con Pociones, llaves o cofre para agregarlas al inventario.
     * @return boolean indicando si colisiona (true) o si no (false)
     */
    private boolean enColisionPocion(){
        for (int i = 0; i < capaObjetos.getAreasColision().size(); i++){
            final Rectangle area = capaObjetos.getAreasColision().get(i);
            int origenX = area.x;
            int origenY = area.y;
            final Rectangle areaPocion = new Rectangle(origenX, origenY, ladoSprite, ladoSprite);
            if (LIMITE_ARRIBA.intersects(areaPocion) || LIMITE_ABAJO.intersects(areaPocion) || LIMITE_IZQUIERDA.intersects(areaPocion) || LIMITE_DERECHA.intersects(areaPocion)){
                pocionEnColision = capaObjetos.getPociones().get(i);
                return true;
            }
        }
        return false;
    }

    // Colisiones con la capa Enemigos
    /**
     * Verifica si el personaje esta en colision con Enemigos para iniciar una batalla.
     * @return boolean indicando si colisiona (true) o si no (false)
     */
    private boolean enColisionEnemigos(){
        for (int i = 0; i < capaEnemigos.getAreasColision().size(); i++){
            final Rectangle area = capaEnemigos.getAreasColision().get(i);
            int origenX = area.x;
            int origenY = area.y;
            final Rectangle areaEnemigo = new Rectangle(origenX, origenY, ladoSpriteGrande, ladoSpriteGrande);
            if (LIMITE_ARRIBA.intersects(areaEnemigo) || LIMITE_ABAJO.intersects(areaEnemigo) || LIMITE_IZQUIERDA.intersects(areaEnemigo) || LIMITE_DERECHA.intersects(areaEnemigo)){
                enemyEnColision = capaEnemigos.getEnemigos().get(i);
                return true;
            }
        }
        return false;
    }

    // Colisiones con la capa Mapa
    /**
     * Verifica si el personaje esta colisiondo con objetos del mapa por los cuales no pueda avanzar.     *
     * @param direccionY int que indica si va hacia arriba o abajo
     * @return boolean indicando si colisiona (true) o si no (false)
     */
    private boolean enColisionArriba(int direccionY){
        for (int i = 0; i < capaMapa.getAreasColision().size(); i++){
            final Rectangle area = capaMapa.getAreasColision().get(i);
            int origenX = area.x;
            int origenY = area.y + direccionY * (int)velocidad + 3 * (int)velocidad;
            final Rectangle areaFutura = new Rectangle(origenX, origenY, ladoSprite, ladoSprite);
            if (LIMITE_ARRIBA.intersects(areaFutura)){
                return true;
            }
        }
        return false;
    }
    /**
     * Verifica si el personaje esta colisiondo con objetos del mapa por los cuales no pueda avanzar.     *
     * @param direccionY int que indica si va hacia arriba o abajo
     * @return boolean indicando si colisiona (true) o si no (false)
     */
    private boolean enColisionAbajo(int direccionY){
        for (int i = 0; i < capaMapa.getAreasColision().size(); i++){
            final Rectangle area = capaMapa.getAreasColision().get(i);
            int origenX = area.x;
            int origenY = area.y + direccionY * (int)velocidad - 3 * (int)velocidad;
            final Rectangle areaFutura = new Rectangle(origenX, origenY, ladoSprite, ladoSprite);
            if (LIMITE_ABAJO.intersects(areaFutura)){
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica si el personaje esta colisiondo con objetos del mapa por los cuales no pueda avanzar.     *
     * @param direccionX int que indica si va a la izquierda o derecha
     * @return boolean indicando si colisiona (true) o si no (false)
     */
    private boolean enColisionIzquierda(int direccionX){
        for (int i = 0; i < capaMapa.getAreasColision().size(); i++){
            final Rectangle area = capaMapa.getAreasColision().get(i);
            int origenX = area.x + direccionX * (int)velocidad + 3 * (int)velocidad;
            int origenY = area.y;
            final Rectangle areaFutura = new Rectangle(origenX, origenY, ladoSprite, ladoSprite);
            if (LIMITE_IZQUIERDA.intersects(areaFutura)){
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica si el personaje esta colisiondo con objetos del mapa por los cuales no pueda avanzar.     *
     * @param direccionX int que indica si va a la izquierda o derecha
     * @return boolean indicando si colisiona (true) o si no (false)
     */
    private boolean enColisionDerecha(int direccionX){
        for (int i = 0; i < capaMapa.getAreasColision().size(); i++){
            final Rectangle area = capaMapa.getAreasColision().get(i);
            int origenX = area.x + direccionX * (int)velocidad - 3 * (int)velocidad;
            int origenY = area.y;
            final Rectangle areaFutura = new Rectangle(origenX, origenY, ladoSprite, ladoSprite);
            if (LIMITE_DERECHA.intersects(areaFutura)){
                return true;
            }
        }
        return false;
    }

    /**
     * Incorpora al inventario correspondiente la pocion con la que colisionamos.
     */
    private void incorporarPocionInventario(){
        // Ir aumentando el arreglo de pociones.
        switch (pocionEnColision.getTipo()){
            case 1:
                inventario[0]++;
                break;
            case 2:
                inventario[1]++;
                break;
            case 3:
                inventario[2]++;
                break;
            case 4:
                inventario[3]++;
                break;
            case 5:
                inventario[4]++;
                break;
            case 6:
                inventario[5]++;
        }
    }

    /**
     * Desactivamos el objeto con el que colisionamos.
     */
    private void desactivarPocion(){
        capaObjetos.actualizarObjetos(pocionEnColision);
    }

    /**
     * Desactivamos el enemigo con el que colisionamos y vencimos.
     */
    private void desactivarEnemigo(){
        capaEnemigos.actualizarEnemigos(enemyEnColision);
    }
    
    // En colision con los bordes del mapa

    /**
     * Verifica si el jugador en una posicion futura puede estar fuera del mapa y cancela el movimiento
     * @param direccionX Direccion del jugador en el eje X (izquierda o derecha)
     * @param direccionY Direccion del jugador en el eje Y (Arriba o abajo)
     * @return boolean que va a indicar si va a estar fuera del mapa (true) o no (false)
     */
    private boolean fueraMapa(final int direccionX, final int direccionY) {

        int posicionFuturaX = (int) posicionX + direccionX * (int) velocidad;
        int posicionFuturaY = (int) posicionY + direccionY * (int) velocidad;

        final Rectangle bordesMapa = capaMapa.obtenerBordes(posicionFuturaX, posicionFuturaY, ANCHO_COLISION_JUGADOR, ALTO_COLISION_JUGADOR);

        final boolean fuera;

        if (LIMITE_ARRIBA.intersects(bordesMapa) || LIMITE_ABAJO.intersects(bordesMapa)
                || LIMITE_IZQUIERDA.intersects(bordesMapa) || LIMITE_DERECHA.intersects(bordesMapa)) {
            fuera = false;
        } else {
            fuera = true;
        }

        return fuera;
    }

    /**
     * Este metodo usa las pociones disponibles segun la tecla presionada.
     * Modifica el inventario y los estados de Fuerza y Salud del personaje.
     */
    private void usarPocion(){
        if (Controles.teclado.isUsarPocionUno() && inventario[0] > 0) {
            if (heroe.getExperiencia() < experienciaMaxima){
                heroe.setExperiencia(heroe.getExperiencia() + 20);
                inventario[0]--;
                if (heroe.getExperiencia() > experienciaMaxima){
                    heroe.setExperiencia(experienciaMaxima);
                }
            }
            Controles.teclado.setUsarPocionUno(false);
        }
        if (Controles.teclado.isUsarPocionDos() && inventario[1] > 0) {
            if (heroe.getFuerza() < fuerzaMaxima){
                heroe.setFuerza(heroe.getFuerza() + 30);
                inventario[1]--;
                if (heroe.getFuerza() > fuerzaMaxima){
                    heroe.setFuerza(fuerzaMaxima);
                }
            }
            Controles.teclado.setUsarPocionDos(false);
        }
        if (Controles.teclado.isUsarPocionTres() && inventario[2] > 0) {
            boolean seUso = false;
            if (heroe.getExperiencia() < experienciaMaxima){
                heroe.setExperiencia(heroe.getExperiencia() + 20);
                seUso = true;
                if (heroe.getExperiencia() > experienciaMaxima){
                    heroe.setExperiencia(experienciaMaxima);
                }
            }
            if (heroe.getFuerza() < fuerzaMaxima){
                heroe.setFuerza(heroe.getFuerza() + 50);
                seUso = true;
                if (heroe.getFuerza() > fuerzaMaxima){
                    heroe.setFuerza(fuerzaMaxima);
                }
            }
            if (seUso) {
                inventario[2]--;
            }
            Controles.teclado.setUsarPocionTres(false);
        }
    }

    //region GETTERS Y SETTERS
    public double getPosicionX() {
        return posicionX;
    }
    public double getPosicionY() {
        return posicionY;
    }
    public int getResistencia() {
        return heroe.getResistencia();
    }
    public int getFuerza() {
        return heroe.getFuerza();
    }
    public int getExperiencia() {
        return heroe.getExperiencia();
    }
    public int[] getInventario() {
        return inventario;
    }
    public void setInventario(int[] inventario) {
        this.inventario = inventario;
    }
    public int getResistenciaMaxima() {
        return resistenciaMaxima;
    }
    public int getExperienciaMaxima() {
        return experienciaMaxima;
    }
    public int getFuerzaMaxima() {
        return fuerzaMaxima;
    }
    //endregion
}
