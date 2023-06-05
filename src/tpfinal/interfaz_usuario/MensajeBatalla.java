package tpfinal.interfaz_usuario;

public class MensajeBatalla {
    private String mensaje;
    private int fuerzaEnemigo;
    private int fuerzaHeroe;
    private int posX;
    private int posY;

    public MensajeBatalla(String mensaje,  int fuerzaEnemigo, int fuerzaHeroe, int posX, int posY) {
        this.mensaje = mensaje;
        this.fuerzaEnemigo = fuerzaEnemigo;
        this.fuerzaHeroe = fuerzaHeroe;
        this.posX = posX;
        this.posY = posY;
    }

    public String getMensaje() {
        return mensaje;
    }
    public int getFuerzaEnemigo() {
        return fuerzaEnemigo;
    }
    public int getFuerzaHeroe() {
        return fuerzaHeroe;
    }
    public int getPosX() {
        return posX;
    }
    public int getPosY() {
        return posY;
    }
}
