package tpfinal;

import tpfinal.control.AdministrarControles;
import tpfinal.mapas.CapaEnemigos;
import tpfinal.mapas.CapaObjetos;
import tpfinal.mapas.CapaMapa;
import tpfinal.graficos.SpritesSheet;
import tpfinal.objetos.*;
import tpfinal.vistas.AdministrarVentanas;

import java.awt.*;
import java.awt.image.BufferedImage;

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

    // * BORDES DE COLISION DEL JUGADOR, ES UN RECTANGULO DESDE LA MITAD HASTA ABAJO Y UN POCO MAS ANGOSTO QUE EL SPRITE
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

    //private int resistencia = 600; // fps son 60, luego tendriamos 10 segundo donde puede correr sin cansarse.
    //private int fuerza = 380;
    //private int experiencia = 50;

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

    private Heroe heroe;
    public Jugador(){

    }
    public Jugador(int posicionX, int posicionY, CapaMapa capaMapa, CapaObjetos capaObjetos, CapaEnemigos capaEnemigos, Heroe heroe){
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
        imagen = hojaSprite.obtenerSprite(1).obtenerImagen();
    }

    public void dibujar(Graphics grafico){
        if (!enBatalla){
            final int centroX = (ancho / 2) - (ladoSprite /2);
            final int centroY = (alto / 2) - (ladoSprite /2);
            grafico.drawImage(imagen, centroX, centroY, null);
        } else {
            crearVentanaBatalla();
            enBatalla = false;
            desactivarEnemigo();
        }
    }
    private void crearVentanaBatalla(){
        AdministrarVentanas.iniciarVentanaBatalla(heroe, enemyEnColision);
        AdministrarVentanas.cambiarEstadoActual(4);
    }

    public void actualizar(){
        if (!enBatalla) {
            evaluarVelocidadYResistencia();
            cambiarAnimacionEstado();
            enMovimiento = false;
            determinarDireccion();
            animar();
            usarPocion();
        }
    }
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
                if (AdministrarControles.teclado.getIzquierda().obtenerUltimaPulsacion() > AdministrarControles.teclado.getArriba()
                        .obtenerUltimaPulsacion()) {
                    mover(direccionX, 0);
                } else {
                    mover(0, direccionY);
                }
            }
            // izquierda y abajo
            if (direccionX == -1 && direccionY == 1) {
                if (AdministrarControles.teclado.getIzquierda().obtenerUltimaPulsacion() > AdministrarControles.teclado.getAbajo()
                        .obtenerUltimaPulsacion()) {
                    mover(direccionX, 0);
                } else {
                    mover(0, direccionY);
                }
            }
            // derecha y arriba
            if (direccionX == 1 && direccionY == -1) {
                if (AdministrarControles.teclado.getDerecha().obtenerUltimaPulsacion() > AdministrarControles.teclado.getArriba()
                        .obtenerUltimaPulsacion()) {
                    mover(direccionX, 0);
                } else {
                    mover(0, direccionY);
                }
            }
            // derecha y abajo
            if (direccionX == 1 && direccionY == 1) {
                if (AdministrarControles.teclado.getDerecha().obtenerUltimaPulsacion() > AdministrarControles.teclado.getAbajo()
                        .obtenerUltimaPulsacion()) {
                    mover(direccionX, 0);
                } else {
                    mover(0, direccionY);
                }
            }
        }
    }
    private void evaluarVelocidadYResistencia(){ // Si tiene resistencia va a poder aumentar la velocidad
        if (AdministrarControles.teclado.isCorriendo() && heroe.getResistencia() > 0){
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
        if (AdministrarControles.teclado.getIzquierda().estaPulsada()) {
            direccionX = -1;
        } else if (AdministrarControles.teclado.getDerecha().estaPulsada()) {
            direccionX = 1;
        }
        return direccionX;
    }
    private int indicarDireccionY() {
        int direccionY = 0;
        if (AdministrarControles.teclado.getArriba().estaPulsada() ) {
            direccionY = -1;

        } else if (AdministrarControles.teclado.getAbajo().estaPulsada() ) {
            direccionY = 1;
        }
        return direccionY;
    }
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
            if (AdministrarControles.teclado.isCorriendo() && heroe.getResistencia() > 0){
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
    private void cambiarDireccion(final int velocidadX, final int velocidadY) {
        // En direccion guardo un entero que luego me indica que version del sprite dibujo en pantalla
        if (velocidadX == -1) {
            direccion = 1;
        } else if (velocidadX == 1) {
            direccion = 2;
        }

        if (velocidadY == -1) {
            direccion = 3;
        } else if (velocidadY == 1) {
            direccion = 0;
        }
    }
    private void animar(){
        if (!enMovimiento) {
            estado = 0;
            animacion = 0;
        }
        imagen = hojaSprite.obtenerSprite(estado, direccion ).obtenerImagen();
    }
    // Colisiones con la capa Pociones
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

    // Incorporar Pocion al Inventario
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

    private void desactivarPocion(){
        capaObjetos.actualizarObjetos(pocionEnColision);
    }
    private void desactivarEnemigo(){
        capaEnemigos.actualizarObjetos(enemyEnColision);
    }
    
    // En colision con los bordes del mapa
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

    private void usarPocion(){
        if (AdministrarControles.teclado.isUsarPocionUno() && inventario[0] > 0) {
            if (heroe.getExperiencia() < experienciaMaxima){
                heroe.setExperiencia(heroe.getExperiencia() + 20);
                inventario[0]--;
                if (heroe.getExperiencia() > experienciaMaxima){
                    heroe.setExperiencia(experienciaMaxima);
                }
            }
            AdministrarControles.teclado.setUsarPocionUno(false);
        }
        if (AdministrarControles.teclado.isUsarPocionDos() && inventario[1] > 0) {
            if (heroe.getFuerza() < fuerzaMaxima){
                heroe.setFuerza(heroe.getFuerza() + 30);
                inventario[1]--;
                if (heroe.getFuerza() > fuerzaMaxima){
                    heroe.setFuerza(fuerzaMaxima);
                }
            }
            AdministrarControles.teclado.setUsarPocionDos(false);
        }
        if (AdministrarControles.teclado.isUsarPocionTres() && inventario[2] > 0) {
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
            AdministrarControles.teclado.setUsarPocionTres(false);
        }
    }
    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }
    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }
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

    public int getRecuperacionMaxima() {
        return recuperacionMaxima;
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
}
