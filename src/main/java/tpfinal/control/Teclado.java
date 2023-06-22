package tpfinal.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Clase que administra el comportamiento del teclado.
 * Implementa la interfaz KeyListener que nos obliga a implementar keyPressed, keyReleased y keyTyped
 * Usamos solo las 2 primeras.
 * keyPressed, cuando es presionada una tecla
 * keyReleased, cuando se suelta la tecla.
 */
public class Teclado implements KeyListener {
    private Tecla arriba;
    private Tecla abajo;
    private Tecla izquierda;
    private Tecla derecha;
    private boolean corriendo;
    private boolean pausaActiva;
    private boolean entrarJuego;
    private boolean nuevoJuego;
    private boolean usarPocionUno;
    private boolean usarPocionDos;
    private boolean usarPocionTres;
    private boolean finBatalla;
    private boolean saveGame;
    private boolean finJuego;

    public Teclado(){
        arriba = new Tecla();
        abajo = new Tecla();
        izquierda = new Tecla();
        derecha = new Tecla();
        corriendo = false;
        pausaActiva = false;
        entrarJuego = false;
        nuevoJuego = false;
        usarPocionUno = false;
        usarPocionDos = false;
        usarPocionTres = false;
        finBatalla = false;
        saveGame = false;
        finJuego = false;
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                arriba.teclaPulsada();
                break;
            case KeyEvent.VK_S:
                abajo.teclaPulsada();
                break;
            case KeyEvent.VK_A:
                izquierda.teclaPulsada();
                break;
            case KeyEvent.VK_D:
                derecha.teclaPulsada();
                break;
            case KeyEvent.VK_SHIFT:
                corriendo = true;
                break;
            case KeyEvent.VK_P:
                pausaActiva = !pausaActiva;
                break;
            case KeyEvent.VK_ENTER:
                entrarJuego = true;
                break;
            case KeyEvent.VK_N:
                nuevoJuego = true;
                break;
            case KeyEvent.VK_1:
                usarPocionUno = true;
                break;
            case KeyEvent.VK_2:
                usarPocionDos = true;
                break;
            case KeyEvent.VK_3:
                usarPocionTres = true;
                break;
            case KeyEvent.VK_F1:
                finBatalla = true;
                break;
            case KeyEvent.VK_F2:
                saveGame = true;
                break;
            case KeyEvent.VK_ESCAPE:
                finJuego = true;
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                arriba.teclaLiberada();
                break;
            case KeyEvent.VK_S:
                abajo.teclaLiberada();
                break;
            case KeyEvent.VK_A:
                izquierda.teclaLiberada();
                break;
            case KeyEvent.VK_D:
                derecha.teclaLiberada();
                break;
            case KeyEvent.VK_SHIFT:
                corriendo = false;
                break;
            case KeyEvent.VK_ENTER:
                entrarJuego = false;
                break;
            case KeyEvent.VK_N:
                nuevoJuego = false;
                break;
            case KeyEvent.VK_1:
                usarPocionUno = false;
                break;
            case KeyEvent.VK_2:
                usarPocionDos = false;
                break;
            case KeyEvent.VK_3:
                usarPocionTres = false;
                break;
            case KeyEvent.VK_F2:
                saveGame = false;
                break;
            case KeyEvent.VK_F1:
                finBatalla = false;
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
    }

    //#region GETTERS Y SETTERS
    public Tecla getArriba() {
        return arriba;
    }
    public Tecla getAbajo() {
        return abajo;
    }
    public Tecla getIzquierda() {
        return izquierda;
    }
    public Tecla getDerecha() {
        return derecha;
    }
    public boolean isCorriendo() {
        return corriendo;
    }
    public boolean isPausaActiva() {
        return pausaActiva;
    }
    public void setPausaActiva(boolean pausaActiva) {
        this.pausaActiva = pausaActiva;
    }
    public boolean isEntrarJuego() {
        return entrarJuego;
    }
    public boolean isNuevoJuego() {
        return nuevoJuego;
    }
    public boolean isUsarPocionUno() {
        return usarPocionUno;
    }
    public void setUsarPocionUno(boolean usarPocionUno) {
        this.usarPocionUno = usarPocionUno;
    }
    public boolean isUsarPocionDos() {
        return usarPocionDos;
    }
    public void setUsarPocionDos(boolean usarPocionDos) {
        this.usarPocionDos = usarPocionDos;
    }
    public boolean isUsarPocionTres() {
        return usarPocionTres;
    }
    public void setUsarPocionTres(boolean usarPocionTres) {
        this.usarPocionTres = usarPocionTres;
    }
    public boolean isFinBatalla() {
        return finBatalla;
    }
    public void setFinBatalla(boolean finBatalla) {
        this.finBatalla = finBatalla;
    }
    public boolean isSaveGame() {
        return saveGame;
    }
    public void setSaveGame(boolean saveGame) {
        this.saveGame = saveGame;
    }
    public boolean isFinJuego() {
        return finJuego;
    }

    //endregion
}
