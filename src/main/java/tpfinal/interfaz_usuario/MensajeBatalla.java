package tpfinal.interfaz_usuario;

/**
 * Clase que crea cada mensaje de una batalla para luego
 * ir mostrando por pantalla.
 * Guarda el texto del mensaje, la posicion para mostrarlo y la fuerza restante
 * del enemigo y del personaje.
 */
public class MensajeBatalla {
    private String mensaje;
    private int fuerzaEnemigo;
    private int fuerzaHeroe;
    private int posX;
    private int posY;

    /**
     * Constructor de la clase, crea el mensaje resultado de cada turno de la batalla.
     * @param mensaje String con el texto a mostrar.
     * @param fuerzaEnemigo int con el valor fuerza del enemigo.
     * @param fuerzaHeroe int con el valor fuerza del personaje.
     * @param posX int con la ubicacion del mensaje sobre el eje X
     * @param posY int con la ubicacion del mensaje sobre el eje Y
     */
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
