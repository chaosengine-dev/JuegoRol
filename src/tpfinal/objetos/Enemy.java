package tpfinal.objetos;

import java.awt.image.BufferedImage;

public class Enemy extends Objeto {

    private boolean activa;
    private int tipo;
    public int salud;
    private int fuerza;
    private int resistencia;

    public Enemy(){

    }

    public Enemy(int id, int posicionX, int posicionY, int tipo) {
        super(id, posicionX, posicionY);
        this.tipo = tipo;
        this.activa = true;
        switch (tipo){
            case 1:
                salud = 200;
                fuerza = 400;
                resistencia = 100;
                break;
            case 2:
                salud = 250;
                fuerza = 500;
                resistencia = 200;
                break;
            case 3:
                salud = 300;
                fuerza = 800;
                resistencia = 300;
        }
    }

    public boolean isActiva() {
        return activa;
    }
    public int getTipo() {
        return tipo;
    }
    public int getSalud() {
        return salud;
    }
    public void setSalud(int salud) {
        this.salud = salud;
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
}
