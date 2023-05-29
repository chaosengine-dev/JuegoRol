package tpfinal.interfaz_usuario;

public class MensajeBatalla {
    public String mensaje;
    public int fuerzaEnemigo;
    public int fuerzaHeroe;
    public int posX;
    public int posY;


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

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getFuerzaEnemigo() {
        return fuerzaEnemigo;
    }

    public void setFuerzaEnemigo(int fuerzaEnemigo) {
        this.fuerzaEnemigo = fuerzaEnemigo;
    }

    public int getFuerzaHeroe() {
        return fuerzaHeroe;
    }

    public void setFuerzaHeroe(int fuerzaHeroe) {
        this.fuerzaHeroe = fuerzaHeroe;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
