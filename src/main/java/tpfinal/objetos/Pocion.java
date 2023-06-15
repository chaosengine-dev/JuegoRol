package tpfinal.objetos;

/**
 * Clase que crea las pociones, llaves y cobres, extiende de la clase Objeto.
 * Esta clase agrega el atributo activa, un boolean que informa si ya fue agregada al inventario.
 * La clase padre tiene la posicion en el mapa (coordenadas X e Y) y un id identificatorio.
 */
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
