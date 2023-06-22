package tpfinal.login.models;

import tpfinal.objetos.Enemy;
import tpfinal.objetos.Pocion;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que modela una partida guardada.
 * Tiene toda la información necesaria para continuar nuestro juego.
 * Implementa Serializable ya que se va a persistir.
 */
public class SavedGame implements Serializable {
    private static final long serialVersionUID = 1234567123456L;
    private String usuario;
    private int posicionJugadorX;
    private int posicionJugadorY;
    private int tipo;
    private int fuerza;
    private int resistencia;
    private int experiencia;
    private int cantidadPocion1;
    private int cantidadPocion2;
    private int cantidadPocion3;
    private boolean key1;
    private boolean key2;
    private boolean key3;
    private ArrayList<Pocion> pociones = new ArrayList<>();
    private ArrayList<Enemy> enemigos = new ArrayList<>();

    public SavedGame(){

    }

    /**
     * Constructor de la clase
     * @param usuario Usuario logueado.
     * @param posicionJugadorX Posicion del jugador en el eje X.
     * @param posicionJugadorY Posicion del jugador en el eje Y.
     * @param tipo Tipo de Heroe.
     * @param fuerza Valor de la fuerza del jugador.
     * @param resistencia Valor de la resistencia del jugador.
     * @param experiencia Valor de la experiencia del jugador.
     * @param cantidadPocion1 Cantidad de pociones del inventario 1.
     * @param cantidadPocion2 Cantidad de pociones del inventario 2.
     * @param cantidadPocion3 Cantidad de pociones del inventario 3.
     * @param key1 true si tiene la llave 1 o false si no.
     * @param key2 true si tiene la llave 2 o false si no.
     * @param key3 true si tiene la llave 3 o false si no.
     * @param pociones ArrayList con las pociones en el mapa.
     * @param enemigos ArrayList con los enemigos en el mapa.
     */
    public SavedGame(String usuario, int posicionJugadorX, int posicionJugadorY,
                     int tipo, int fuerza, int resistencia, int experiencia,
                     int cantidadPocion1, int cantidadPocion2, int cantidadPocion3,
                     boolean key1, boolean key2, boolean key3,
                     ArrayList<Pocion> pociones, ArrayList<Enemy> enemigos) {

        this.usuario = usuario;
        this.tipo = tipo;
        this.fuerza = fuerza;
        this.resistencia = resistencia;
        this.experiencia = experiencia;
        this.posicionJugadorX = posicionJugadorX;
        this.posicionJugadorY = posicionJugadorY;
        this.cantidadPocion1 = cantidadPocion1;
        this.cantidadPocion2 = cantidadPocion2;
        this.cantidadPocion3 = cantidadPocion3;
        this.key1 = key1;
        this.key2 = key2;
        this.key3 = key3;
        this.pociones = pociones;
        this.enemigos = enemigos;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getPosicionJugadorX() {
        return posicionJugadorX;
    }

    public void setPosicionJugadorX(int posicionJugadorX) {
        this.posicionJugadorX = posicionJugadorX;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public int getPosicionJugadorY() {
        return posicionJugadorY;
    }

    public void setPosicionJugadorY(int posicionJugadorY) {
        this.posicionJugadorY = posicionJugadorY;
    }

    public int getCantidadPocion1() {
        return cantidadPocion1;
    }

    public void setCantidadPocion1(int cantidadPocion1) {
        this.cantidadPocion1 = cantidadPocion1;
    }

    public int getCantidadPocion2() {
        return cantidadPocion2;
    }

    public void setCantidadPocion2(int cantidadPocion2) {
        this.cantidadPocion2 = cantidadPocion2;
    }

    public int getCantidadPocion3() {
        return cantidadPocion3;
    }

    public void setCantidadPocion3(int cantidadPocion3) {
        this.cantidadPocion3 = cantidadPocion3;
    }

    public boolean isKey1() {
        return key1;
    }

    public void setKey1(boolean key1) {
        this.key1 = key1;
    }

    public boolean isKey2() {
        return key2;
    }

    public void setKey2(boolean key2) {
        this.key2 = key2;
    }

    public boolean isKey3() {
        return key3;
    }

    public void setKey3(boolean key3) {
        this.key3 = key3;
    }

    public ArrayList<Pocion> getPociones() {
        return pociones;
    }

    public void setPociones(ArrayList<Pocion> pociones) {
        this.pociones = pociones;
    }

    public ArrayList<Enemy> getEnemigos() {
        return enemigos;
    }

    public void setEnemigos(ArrayList<Enemy> enemigos) {
        this.enemigos = enemigos;
    }

    @Override
    public String toString() {
        return "SavedGame{" +
                "usuario=" + usuario +
                ", posicionJugadorX=" + posicionJugadorX +
                ", posicionJugadorY=" + posicionJugadorY +
                ", tipo=" + tipo +
                ", fuerza=" + fuerza +
                ", resistencia=" + resistencia +
                ", experiencia=" + experiencia +
                ", cantidadPocion1=" + cantidadPocion1 +
                ", cantidadPocion2=" + cantidadPocion2 +
                ", cantidadPocion3=" + cantidadPocion3 +
                ", key1=" + key1 +
                ", key2=" + key2 +
                ", key3=" + key3 +
                ", pociones=" + pociones +
                ", enemigos=" + enemigos +
                '}';
    }
}

