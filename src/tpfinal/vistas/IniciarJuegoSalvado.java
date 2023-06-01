package tpfinal.vistas;

import tpfinal.Jugador;
import tpfinal.control.AdministrarControles;
import tpfinal.interfaz_usuario.MenuEstadistica;
import tpfinal.mapas.CapaAmbiente;
import tpfinal.mapas.CapaEnemigos;
import tpfinal.mapas.CapaMapa;
import tpfinal.mapas.CapaObjetos;
import tpfinal.objetos.*;
import tpfinal.persistencia.PersistenciaJson;
import tpfinal.persistencia.SavedGame;

import java.awt.*;

public class IniciarJuegoSalvado implements VentanaJuego {

    private final int ladoSpriteChico = 32;
    private final int ladoSpriteGrande = 64;
    CapaMapa capaMapa;
    CapaAmbiente capaAmbiente;
    CapaObjetos capaObjetos;
    CapaEnemigos capaEnemigos;
    MenuEstadistica menuEstadistica;
    Jugador jugador;

    public IniciarJuegoSalvado(){

        capaMapa = new CapaMapa("Recursos/Mapas/mapa.txt", ladoSpriteChico);
        capaAmbiente = new CapaAmbiente("Recursos/Objetos/ambiente.txt", ladoSpriteGrande);
        capaObjetos = new CapaObjetos("Recursos/Objetos/pociones.txt", ladoSpriteChico);
        capaEnemigos = new CapaEnemigos("Recursos/Objetos/enemigos.txt", ladoSpriteGrande);

        // Leer json
        PersistenciaJson leerSavedGame = new PersistenciaJson();
        String path = "Recursos/SavedGames/" + AdministrarVentanas.getUserRegistered() + ".json";
        SavedGame partida = leerSavedGame.deserializar(path, SavedGame.class);


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

        /*
                for (int y = 0; y < capaObjetos.getAlto(); y++) {
            for (int x = 0; x < capaObjetos.getAncho(); x++) {
                int indice = sprites[x + y * capaObjetos.getAncho()];
            }
        }



        for (Pocion pocion: capaObjetos.getPociones()) {
            boolean exist = false;
            for (Pocion pocionActiva: partida.getPociones()) {
                if (pocionActiva.getId() == pocion.getId()){
                    exist = true;
                    break;
                }
            }
            if (!exist){
                pocion.setActiva(false);
                capaObjetos.getPociones().set(capaObjetos.getPociones().indexOf(pocion), pocion);
                sprites[(pocion.getPosicionX()/ ladoSpriteChico) + (pocion.getPosicionY() / ladoSpriteChico) * capaObjetos.getAncho()] = 0;
            }
        }*/


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
