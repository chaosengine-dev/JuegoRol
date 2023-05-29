package tpfinal.objetos;

public class Objeto {

    private int id;
    private int posicionX;
    private int posicionY;

    public Objeto(int id, int posicionX, int posicionY){
        this.id = id;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }

    @Override
    public String toString() {
        return "Objeto{" +
                "id=" + id +
                "posicionX=" + posicionX +
                ", posicionY=" + posicionY +
                '}';
    }

}
