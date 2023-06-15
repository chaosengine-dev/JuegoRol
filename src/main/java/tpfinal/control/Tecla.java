package tpfinal.control;

/**
 * Clase que detecta la ultima tecla presionada y corta la tecla anterior.
 * Se usa para evitar el desplazamiento en diagonal del personaje.
 */
public class Tecla {
    private boolean pulsada = false;
    private long ultimaPulsacion = System.nanoTime();
    public void teclaPulsada() {
        pulsada = true;
        ultimaPulsacion = System.nanoTime();
    }
    public void teclaLiberada() {
        pulsada = false;
    }
    public boolean estaPulsada() {
        return pulsada;
    }
    public long obtenerUltimaPulsacion() {
        return ultimaPulsacion;
    }
}
