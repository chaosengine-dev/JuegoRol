package tpfinal;

import tpfinal.control.AdministrarControles;
import tpfinal.control.Musica;
import tpfinal.graficos.CanvasVentana;
import tpfinal.graficos.VentanaJFrame;
import tpfinal.login.welcome.Welcomepage;
import tpfinal.vistas.AdministrarVentanas;

public class JuegoRol {
    private boolean enFuncionamiento = false;
    private static int ancho;
    private static int alto;
    private String nombre;
    private CanvasVentana canvasVentana;
    private VentanaJFrame ventanaJFrame;

    public JuegoRol(int ancho, int alto, String nombre){
        Musica.fondo();
        this.alto = alto;
        this.ancho = ancho;
        this.nombre = nombre;

    }
    public void iniciarJuego() {
        enFuncionamiento = true;
        canvasVentana = new CanvasVentana(ancho, alto);
        ventanaJFrame = new VentanaJFrame(nombre, canvasVentana);
        AdministrarVentanas.gestorVentanas(canvasVentana);
    }
    public void iniciarBuclePrincipal() {

        // nano segundos en un segundo, son 1000 millones.
        final int NS_POR_SEGUNDO = 1000000000;
        // la cantidad de veces que se va a redibujar la pantalla, cuanto mas chico mas lento.
        final int APS_OBJETIVO = 130;
        // Simplemente es la cantidad de nanosegundo que hay entre una actualizacion y otra.
        final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO / APS_OBJETIVO;

        // Tomo el tiempo actual en nanosegundos.
        long referenciaActualizacion = System.nanoTime();

        double tiempoTranscurrido;
        double delta = 0; // Cantidad de nanosegundos transcurridos desde una actualizacion.

        while (enFuncionamiento){
            // Capturo el momento cuando entro a l ciclo
            final long inicioBucle = System.nanoTime();
            // Calculo el tiempo transcurrido entre que empieza el bucle y termina, la primera vez solo calcula con
            // el momento en el que se inicio la variabel referenciaActualizacion
            tiempoTranscurrido = (double) (inicioBucle - referenciaActualizacion);
            // volvemos a tomar un tiempo de referencia para poder comparar en el ciclo siguiente.
            referenciaActualizacion = inicioBucle;
            // delta sumnara una numero muy chico y cuando llegue a uno o mas llama al metodo actualizar.
            // Con esta formula se logra que el juego se actualiza unas 130 veces que es el OBJETIVO.
            delta += tiempoTranscurrido / NS_POR_ACTUALIZACION;
            while (delta >= 1){
                actualizar();
                delta--;
            }
            dibujar();

        }
        /*double retraso = 1;
        int velocidad = 4;

        while (enFuncionamiento){
            retraso = retraso + Math.random();
            while (retraso >= velocidad) {
                actualizar();
                retraso = 1;
                System.out.println("retraso");
            }
            System.out.println("dibujo");
            dibujar();
        }*/
    }
    private void dibujar() {
        canvasVentana.dibujar();
    }
    private void actualizar() {
        if (AdministrarControles.teclado.isEntrarJuego() && AdministrarVentanas.getIndice() == 0) {
            // Cargar juego grabado
            AdministrarControles.teclado.setPausaActiva(false);
            AdministrarVentanas.cambiarEstadoActual(1);
        }
        if (AdministrarControles.teclado.isPausaActiva() && AdministrarVentanas.getIndice() == 1){
            AdministrarVentanas.cambiarEstadoActual(2);
        }
        if (!AdministrarControles.teclado.isPausaActiva() && AdministrarVentanas.getIndice() == 2){
            AdministrarVentanas.cambiarEstadoActual(1);
        }
        if (AdministrarControles.teclado.isNuevoJuego() && AdministrarVentanas.getIndice() == 0){
            AdministrarVentanas.cambiarEstadoActual(3);
        }
        if (AdministrarControles.teclado.isEntrarJuego() && AdministrarVentanas.getIndice() == 3) {
            AdministrarControles.teclado.setPausaActiva(false);
            // Crear juego nuevo
            AdministrarVentanas.iniciarVentanaJuego(); // inicio la ventana juego teniendo el player elegido
            AdministrarVentanas.cambiarEstadoActual(1);
        }
        AdministrarVentanas.actualizar();

    }

    public CanvasVentana getCanvasVentana() {
        return canvasVentana;
    }

    public VentanaJFrame getVentanaJFrame() {
        return ventanaJFrame;
    }
}
