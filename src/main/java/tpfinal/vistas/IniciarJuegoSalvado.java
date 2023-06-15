package tpfinal.vistas;

import tpfinal.Jugador;
import tpfinal.interfaz_usuario.MenuEstadistica;
import tpfinal.mapas.CapaAmbiente;
import tpfinal.mapas.CapaEnemigos;
import tpfinal.mapas.CapaMapa;
import tpfinal.mapas.CapaObjetos;
import tpfinal.objetos.*;
import tpfinal.persistencia.PersistenciaJson;
import tpfinal.login.models.SavedGame;

import java.awt.*;

/**
 * Clase para iniciar un juego desde una partida guardada.
 */
public class IniciarJuegoSalvado implements VentanaJuego {

    private final int ladoSpriteChico = 32;
    private final int ladoSpriteGrande = 64;
    private CapaMapa capaMapa;
    private CapaAmbiente capaAmbiente;
    private CapaObjetos capaObjetos;
    private CapaEnemigos capaEnemigos;
    private MenuEstadistica menuEstadistica;
    private Jugador jugador;

    /**
     * Crea un juego desde una partida guardada, lee los archivos de texto de Mapa, Ambiente, Pociones y Enemigos.
     * Lee el archivo json de la partida y actualiza estados del jugador, del inventario,
     * pociones y llaves recolectadas y enemigos vencidos eliminando tanto las pociones, llaves y enemigos
     * del mapa.
     */
    public IniciarJuegoSalvado(){

        capaMapa = new CapaMapa("Recursos/Mapas/mapa.txt", ladoSpriteChico);
        capaAmbiente = new CapaAmbiente("Recursos/Objetos/ambiente.txt", ladoSpriteGrande);
        capaObjetos = new CapaObjetos("Recursos/Objetos/pociones.txt", ladoSpriteChico);
        capaEnemigos = new CapaEnemigos("Recursos/Objetos/enemigos.txt", ladoSpriteGrande);

        // Leer json
        PersistenciaJson leerSavedGame = new PersistenciaJson();
        String path = "Recursos/SavedGames/" + AdministrarVentanas.getUserRegistered() + ".json";
        SavedGame partida = leerSavedGame.deserializar(path, SavedGame.class);
        // Ya se verificó que existe el archivo, no puede fallar.

        int tipoPersonaje = partida.getTipo();

        // Setear objetos y enemigos con la info leida
        capaObjetos.setPociones(partida.getPociones());
        capaEnemigos.setEnemigos(partida.getEnemigos());

        //Eliminar dibujos de pociones y enemigos ya agarradas

        int[] spritesPociones = capaObjetos.getSprites();
        int[] spritesEnemigos = capaEnemigos.getSprites();

        // Inicio a 0 todos los sprites
        for (int i = 0; i < spritesPociones.length; i++){
            if (spritesPociones[i] > 0){
                spritesPociones[i] = 0;
            }
        }
        for (int i = 0; i < spritesEnemigos.length; i++){
            if (spritesEnemigos[i] > 0){
                spritesEnemigos[i] = 0;
            }
        }

        // Cargo la pocion y los enemigos que tengo en la parte salvada
        for (Pocion pocion: partida.getPociones()) {
            int indice = pocion.getPosicionX() / ladoSpriteChico + pocion.getPosicionY() / ladoSpriteChico * capaAmbiente.getAncho();
            spritesPociones[indice] = pocion.getTipo();
        }
        for (Enemy enemigo: partida.getEnemigos()){
            int indice = enemigo.getPosicionX() / ladoSpriteChico + enemigo.getPosicionY() / ladoSpriteChico * capaEnemigos.getAncho();
            spritesEnemigos[indice] = enemigo.getTipo();
        }
        capaObjetos.setSprites(spritesPociones);
        capaEnemigos.setSprites(spritesEnemigos);

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
        jugador = new Jugador(partida.getPosicionJugadorX(), partida.getPosicionJugadorY(), capaMapa, capaObjetos, capaEnemigos,  heroe, 10);
        // Setear estado de pociones y llaves
        int[] inventario = new int[6];
        inventario[0] = partida.getCantidadPocion1();
        inventario[1] = partida.getCantidadPocion2();
        inventario[2] = partida.getCantidadPocion3();
        inventario[3] = partida.isKey1()? 1 : 0;
        inventario[4] = partida.isKey2()? 1 : 0;
        inventario[5] = partida.isKey3()? 1 : 0;
        jugador.setInventario(inventario);

        menuEstadistica = new MenuEstadistica(jugador);
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
        capaMapa.dibujar(grafico, (int) jugador.getPosicionX(), (int) jugador.getPosicionY());
        capaObjetos.dibujar(grafico, (int) jugador.getPosicionX(), (int) jugador.getPosicionY(), ladoSpriteChico);
        capaEnemigos.dibujar(grafico, (int) jugador.getPosicionX(), (int) jugador.getPosicionY(), ladoSpriteChico);
        capaAmbiente.dibujar(grafico, (int) jugador.getPosicionX(), (int) jugador.getPosicionY(), ladoSpriteGrande);
        menuEstadistica.dibujar(grafico, jugador);
        jugador.dibujar(grafico);
    }


}
