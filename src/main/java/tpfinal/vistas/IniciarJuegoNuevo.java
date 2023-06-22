package tpfinal.vistas;

import tpfinal.Jugador;
import tpfinal.control.Controles;
import tpfinal.mapas.CapaAmbiente;
import tpfinal.mapas.CapaEnemigos;
import tpfinal.mapas.CapaObjetos;
import tpfinal.objetos.*;
import tpfinal.interfaz_usuario.MenuEstadistica;
import tpfinal.mapas.CapaMapa;

import java.awt.*;

/**
 * Clase para iniciar un juego nuevo.
 */
public class IniciarJuegoNuevo implements VentanaJuego {

    private final int ladoSpriteChico = 32;
    private final int ladoSpriteGrande = 64;
    private CapaMapa capaMapa;
    private CapaAmbiente capaAmbiente;
    private CapaObjetos capaObjetos;
    private CapaEnemigos capaEnemigos;
    private MenuEstadistica menuEstadistica;
    private Jugador jugador;

    /**
     * Crea un juego nuevo, lee los archivos de texto de Mapa, Ambiente, Pociones y Enemigos.
     * Inicializa el jugador y el menu de estadísticas.
     */
    public IniciarJuegoNuevo(int tipoPersonaje){

        capaMapa = new CapaMapa("Recursos/Mapas/mapa.txt", ladoSpriteChico);
        capaAmbiente = new CapaAmbiente("Recursos/Objetos/ambiente.txt", ladoSpriteGrande);
        capaObjetos = new CapaObjetos("Recursos/Objetos/pociones.txt", ladoSpriteChico);
        capaEnemigos = new CapaEnemigos("Recursos/Objetos/enemigos.txt", ladoSpriteGrande);

        Heroe heroe;

        switch (tipoPersonaje){
            case 1:
                heroe = new Elfo();
                break;
            case 2:
                heroe = new Mago();
                break;
            default:
                heroe = new Guerrero();
                break;
        }
        jugador = new Jugador(capaMapa.getPosicionJugador().x, capaMapa.getPosicionJugador().y, capaMapa, capaObjetos, capaEnemigos,  heroe, 1);
        menuEstadistica = new MenuEstadistica();

    }

    /**
     * Llama a los metodos actualizar de las cuatro clases principales del juego.
     * Jugador, Mapa, Objetos y Enemigos.
     */
    @Override
    public void actualizar() {
        jugador.actualizar();
        capaMapa.actualizar((int) jugador.getPosicionX(), (int) jugador.getPosicionY());
        capaObjetos.actualizar((int) jugador.getPosicionX(), (int) jugador.getPosicionY());
        capaEnemigos.actualizar((int) jugador.getPosicionX(), (int) jugador.getPosicionY());
    }

    /**
     * Llama a los metodos dibujar de las seis clases que arman la vista principal del juego.
     * Jugador, Mapa, Ambiente, Objetos, Enemigos y Menu de Estadísticas.
     */
    @Override
    public void dibujar(Graphics grafico) {
        capaMapa.dibujar(grafico, (int) jugador.getPosicionX(), (int) jugador.getPosicionY(), ladoSpriteChico);
        capaObjetos.dibujar(grafico, (int) jugador.getPosicionX(), (int) jugador.getPosicionY(), ladoSpriteChico);
        capaEnemigos.dibujar(grafico, (int) jugador.getPosicionX(), (int) jugador.getPosicionY(), ladoSpriteChico);
        jugador.dibujar(grafico);
        capaAmbiente.dibujar(grafico, (int) jugador.getPosicionX(), (int) jugador.getPosicionY(), ladoSpriteGrande);
        menuEstadistica.dibujar(grafico, jugador);
    }


}
