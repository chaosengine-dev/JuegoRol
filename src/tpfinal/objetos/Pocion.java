package tpfinal.objetos;

import java.awt.image.BufferedImage;

public class Pocion extends Objeto{

    private final boolean activa;
    private final int tipo;
    public Pocion(int id, int posicionX, int posicionY, int tipo){
        super(id, posicionX, posicionY);
        this.activa = true;
        this.tipo = tipo;
    }

    public boolean isActiva() {
        return activa;
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
