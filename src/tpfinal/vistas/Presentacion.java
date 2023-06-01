package tpfinal.vistas;

import tpfinal.control.AdministrarControles;
import tpfinal.control.Musica;
import tpfinal.graficos.CanvasVentana;
import tpfinal.graficos.SpritesSheet;
import tpfinal.login.models.User;
import tpfinal.persistencia.PersistenciaJson;
import tpfinal.persistencia.SavedGame;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Presentacion implements VentanaJuego {
    private int ancho = 800;
    private  int alto = 600;
    private SpritesSheet hojaSprite;
    private BufferedImage imagen;
    private Rectangle botonEntrar;
    private Rectangle botonCargar;
    private CanvasVentana canvasVentana;
    private String user;

    public Presentacion(CanvasVentana canvasVentana){

        this.hojaSprite = new SpritesSheet("Recursos/Presentacion/presentacion.png",alto, ancho, false);
        imagen = hojaSprite.obtenerSprite(0).obtenerImagen();
        botonCargar = new Rectangle(210,500,120,40);
        botonEntrar = new Rectangle(440,500,120,40);
        this.canvasVentana = canvasVentana;
    }

    @Override
    public void dibujar(Graphics grafico){
        grafico.drawImage(imagen, 0, 0, null);
    }

    @Override
    public void actualizar(){

        if (canvasVentana.getRaton().isClick() ){

            Point ubicacionVentana = canvasVentana.getPosicionVentana();
            Rectangle mouseRelativo = new Rectangle(MouseInfo.getPointerInfo().getLocation().x - ubicacionVentana.x, MouseInfo.getPointerInfo().getLocation().y - ubicacionVentana.y, 1, 1);
            if (mouseRelativo.intersects(botonCargar)){
                // TODO: Cargar partida desde archivo y mostrar
                if (existeSavedGame()){
                    AdministrarVentanas.iniciarVentanaJuegoSalvado();
                    AdministrarVentanas.cambiarEstadoActual(10);
                } else {
                    // Si no existe una parte salvada creo juego nuevo
                    AdministrarVentanas.iniciarVentanaNuevo();
                    AdministrarVentanas.cambiarEstadoActual(3);
                }

            }
            if (mouseRelativo.intersects(botonEntrar)){
                // TODO: Hacer opcion si hacemos click en el boton nuevo juego.
                AdministrarVentanas.iniciarVentanaNuevo();
                AdministrarVentanas.cambiarEstadoActual(3);
            }
            Musica.clickBoton();

        }
        canvasVentana.getRaton().resetClick();
    }

     private boolean existeSavedGame(){
         // Leer json
         PersistenciaJson leerSavedGame = new PersistenciaJson();
         String path = "Recursos/SavedGames/" + AdministrarVentanas.getUserRegistered() + ".json";
         SavedGame partida = leerSavedGame.deserializar(path, SavedGame.class);
         if (partida == null){
             JOptionPane.showMessageDialog(null, "No tiene partes guardadas, iniciando juego nuevo.");
             return false;
         }
         return true;
     }
}
