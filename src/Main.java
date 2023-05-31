import tpfinal.JuegoRol;

public class Main {

    public static void main(String[] arg) {

         // Instanciamos un objeto del Gestor Principal
        JuegoRol juegoRol = new JuegoRol(800, 600, "ENCHANTED LAND - TP FINAL");
        // Iniciamos el juego
        juegoRol.iniciarJuego();
        // Generamos el bucle donde logramos que se dibuje durante tantos fps
        try {
            juegoRol.iniciarBuclePrincipal();
        } catch (Exception exe) {
            System.out.println("Salir");
        }
    }
}