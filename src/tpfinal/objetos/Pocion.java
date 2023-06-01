package tpfinal.objetos;

import java.awt.image.BufferedImage;

public class Pocion extends Objeto{

    private boolean activa;
    private int tipo;

    public Pocion(){

    }
    public Pocion(int id, int posicionX, int posicionY, int tipo){
        super(id, posicionX, posicionY);
        this.activa = true;
        this.tipo = tipo;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public int getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "Pocion{" +
                "{" + super.toString() +
                ", activa=" + activa +
                '}';
    }
}
