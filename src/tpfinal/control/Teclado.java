package tpfinal.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class Teclado implements KeyListener {

    private Tecla arriba;
    private Tecla abajo;
    private Tecla izquierda;
    private Tecla derecha;
    private boolean recogiendo;
    private boolean corriendo;
    private boolean pausaActiva;
    private boolean entrarJuego;
    private boolean nuevoJuego;
    private boolean usarPocionUno;
    private boolean usarPocionDos;
    private boolean usarPocionTres;
    private boolean finBatalla;
    private int tipoPersonaje;
    private boolean saveGame;

    public Teclado(){
        arriba = new Tecla();
        abajo = new Tecla();
        izquierda = new Tecla();
        derecha = new Tecla();
        recogiendo = false;
        corriendo = false;
        pausaActiva = false;
        entrarJuego = false;
        nuevoJuego = false;
        usarPocionUno = false;
        usarPocionDos = false;
        usarPocionTres = false;
        finBatalla = false;
        saveGame = false;
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
            case KeyEvent.VK_E:
                recogiendo = true;
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
                try {
                    System.out.println("Gracias por jugar...");
                    System.exit(0);
                } catch (Exception err){
                    System.out.println("Gracias por jugar...");
                }

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
            case KeyEvent.VK_E:
                recogiendo = false;
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
        }
    }

    public void keyTyped(KeyEvent arg0) {
    }

    public Tecla getArriba() {
        return arriba;
    }

    public void setArriba(Tecla arriba) {
        this.arriba = arriba;
    }

    public Tecla getAbajo() {
        return abajo;
    }

    public void setAbajo(Tecla abajo) {
        this.abajo = abajo;
    }

    public Tecla getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(Tecla izquierda) {
        this.izquierda = izquierda;
    }

    public Tecla getDerecha() {
        return derecha;
    }

    public void setDerecha(Tecla derecha) {
        this.derecha = derecha;
    }

    public boolean isRecogiendo() {
        return recogiendo;
    }

    public void setRecogiendo(boolean recogiendo) {
        this.recogiendo = recogiendo;
    }

    public boolean isCorriendo() {
        return corriendo;
    }

    public void setCorriendo(boolean corriendo) {
        this.corriendo = corriendo;
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

    public void setEntrarJuego(boolean entrarJuego, int tipoPersonaje) {
        this.entrarJuego = entrarJuego;
        this.tipoPersonaje = tipoPersonaje;
    }

    public boolean isNuevoJuego() {
        return nuevoJuego;
    }

    public void setNuevoJuego(boolean nuevoJuego) {
        this.nuevoJuego = nuevoJuego;
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

    public int getTipoPersonaje() {
        return tipoPersonaje;
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
}
