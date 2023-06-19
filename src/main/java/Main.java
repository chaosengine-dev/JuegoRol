import tpfinal.JuegoRol;

public class Main {

    public static void main(String[] arg) {

        //Usando el patron de diseño Singleton creamos una instancia unica del objeto JuegoRol
        JuegoRol juegoRol = JuegoRol.getJuegoRol(800, 600, "ENCHANTED LAND - TP FINAL");
        // Iniciamos el juego
        juegoRol.iniciarJuego();
        // Generamos el bucle donde logramos que se dibuje durante tantos fps
        juegoRol.iniciarBuclePrincipal();
    }
}