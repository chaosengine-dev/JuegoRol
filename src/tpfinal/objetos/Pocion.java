package tpfinal.objetos;

import java.awt.image.BufferedImage;

public class Pocion extends Objeto{

    private BufferedImage imagen;
    private final boolean activa;
    private final int tipo;
    public Pocion(int id, int posicionX, int posicionY, int tipo){
        super(id, posicionX, posicionY);
        this.activa = true;
        this.tipo = tipo;
    }

    public BufferedImage getImagen() {
        return imagen;
    }

    public void setImagen(BufferedImage imagen) {
        this.imagen = imagen;
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
