package tpfinal.vistas;

import tpfinal.Jugador;
import tpfinal.control.AdministrarControles;
import tpfinal.mapas.CapaAmbiente;
import tpfinal.mapas.CapaEnemigos;
import tpfinal.mapas.CapaObjetos;
import tpfinal.objetos.*;
import tpfinal.interfaz_usuario.MenuEstadistica;
import tpfinal.mapas.CapaMapa;

import java.awt.*;

public class IniciarJuegoNuevo implements VentanaJuego {

    private final int ladoSpriteChico = 32;
    private final int ladoSpriteGrande = 64;
    CapaMapa capaMapa;
    CapaAmbiente capaAmbiente;
    CapaObjetos capaObjetos;
    CapaEnemigos capaEnemigos;
    MenuEstadistica menuEstadistica;
    Jugador jugador;

    public IniciarJuegoNuevo(){

        capaMapa = new CapaMapa("Recursos/Mapas/mapa.txt", ladoSpriteChico);
        capaAmbiente = new CapaAmbiente("Recursos/Objetos/ambiente.txt", ladoSpriteGrande);
        capaObjetos = new CapaObjetos("Recursos/Objetos/pociones.txt", ladoSpriteChico);
        capaEnemigos = new CapaEnemigos("Recursos/Objetos/enemigos.txt", ladoSpriteGrande);

        int tipoPersonaje = AdministrarControles.teclado.getTipoPersonaje();
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

        }
        jugador = new Jugador(capaMapa.getPosicionJugador().x, capaMapa.getPosicionJugador().y, capaMapa, capaObjetos, capaEnemigos,  heroe, 1);
        menuEstadistica = new MenuEstadistica(jugador);
    }

    @Override
    public void actualizar() {
        jugador.actualizar();
        capaMapa.actualizar((int) jugador.getPosicionX(), (int) jugador.getPosicionY());
        capaObjetos.actualizar((int) jugador.getPosicionX(), (int) jugador.getPosicionY());
        capaEnemigos.actualizar((int) jugador.getPosicionX(), (int) jugador.getPosicionY());
    }

    @Override
    public void dibujar(Graphics grafico) {
        capaMapa.dibujar(grafico, (int) jugador.getPosicionX(), (int) jugador.getPosicionY());
        capaObjetos.dibujar(grafico, (int) jugador.getPosicionX(), (int) jugador.getPosicionY(), ladoSpriteChico);
        capaEnemigos.dibujar(grafico, (int) jugador.getPosicionX(), (int) jugador.getPosicionY(), ladoSpriteChico);
        capaAmbiente.dibujar(grafico, (int) jugador.getPosicionX(), (int) jugador.getPosicionY(), ladoSpriteGrande);
        menuEstadistica.dibujar(grafico, jugador);
        jugador.dibujar(grafico);
    }


}
