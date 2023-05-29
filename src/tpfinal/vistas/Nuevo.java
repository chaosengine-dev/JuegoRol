package tpfinal.vistas;

import tpfinal.control.AdministrarControles;
import tpfinal.control.Musica;
import tpfinal.graficos.CanvasVentana;
import tpfinal.graficos.SpritesSheet;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static javax.swing.text.StyleConstants.getBackground;
import static javax.swing.text.StyleConstants.getForeground;


public class Nuevo implements VentanaJuego{
    private SpritesSheet hojaSprite;
    private BufferedImage flechaIzquierda;
    private BufferedImage flechaDerecha;
    private BufferedImage botonCrear;
    private BufferedImage caraGuerrero;
    private BufferedImage caraElfo;
    private BufferedImage caraMago;
    private BufferedImage datosGuerrero;
    private BufferedImage datosElfo;
    private BufferedImage datosMago;

    private BufferedImage personajeActual;
    private BufferedImage datosActual;
    private Rectangle botonJugar;
    private Rectangle botonFlechaIzquierda;
    private Rectangle botonFlechaDerecha;
    private CanvasVentana canvasVentana;
    int playerSelected = 0;
    public Nuevo(CanvasVentana canvasVentana){
        this.hojaSprite = new SpritesSheet("Recursos/NewGame/arrowleft128.png",18, 47, false);
        flechaIzquierda = hojaSprite.obtenerSprite(0).obtenerImagen();
        hojaSprite = new SpritesSheet("Recursos/NewGame/arrowright128.png",18, 47, false);
        flechaDerecha = hojaSprite.obtenerSprite(0).obtenerImagen();
        hojaSprite = new SpritesSheet("Recursos/NewGame/crear.png",128, 320, false);
        botonCrear = hojaSprite.obtenerSprite(0).obtenerImagen();
        hojaSprite = new SpritesSheet("Recursos/NewGame/player1.png",159, 147, false);
        caraGuerrero = hojaSprite.obtenerSprite(0).obtenerImagen();
        hojaSprite = new SpritesSheet("Recursos/NewGame/player2.png",159, 147, false);
        caraElfo = hojaSprite.obtenerSprite(0).obtenerImagen();
        hojaSprite = new SpritesSheet("Recursos/NewGame/player3bis.png",159, 147, false);
        caraMago = hojaSprite.obtenerSprite(0).obtenerImagen();
        hojaSprite = new SpritesSheet("Recursos/NewGame/guerrero.png",128, 256, false);
        datosGuerrero = hojaSprite.obtenerSprite(0).obtenerImagen();
        hojaSprite = new SpritesSheet("Recursos/NewGame/elfo.png",128, 256, false);
        datosElfo = hojaSprite.obtenerSprite(0).obtenerImagen();
        hojaSprite = new SpritesSheet("Recursos/NewGame/mago.png",128, 256, false);
        datosMago = hojaSprite.obtenerSprite(0).obtenerImagen();

        personajeActual = caraGuerrero;
        datosActual = datosGuerrero;
        playerSelected = 0;

        botonFlechaIzquierda = new Rectangle(235,95,47,18);
        botonFlechaDerecha = new Rectangle(517,95,47,18);
        botonJugar = new Rectangle(350,390,120,50);

        this.canvasVentana = canvasVentana;
    }
    @Override
    public void actualizar() {
        if (canvasVentana.getRaton().isClick() ){

            Point ubicacionVentana = canvasVentana.getPosicionVentana();
            Rectangle mouseRelativo = new Rectangle(MouseInfo.getPointerInfo().getLocation().x - ubicacionVentana.x, MouseInfo.getPointerInfo().getLocation().y - ubicacionVentana.y, 1, 1);
            if (mouseRelativo.intersects(botonFlechaIzquierda)){
                // TODO: Cambiar Jugador
                if (playerSelected > 0){
                    playerSelected--;
                } else {
                    playerSelected = 2;
                }
            }
            if (mouseRelativo.intersects(botonFlechaDerecha)){
                // TODO: Cambiar Jugador
                if (playerSelected < 2){
                    playerSelected++;
                } else {
                    playerSelected = 0;
                }
            }
            if (mouseRelativo.intersects(botonJugar)){
                // TODO: Jugar nuevo juego
                AdministrarControles.teclado.setEntrarJuego(true, playerSelected);
            }
            cambiarPersonaje(playerSelected);
            Musica.clickBoton();

        }
        canvasVentana.getRaton().resetClick();
    }

    private void cambiarPersonaje(int playerSelected){
        switch (playerSelected){
            case 0:
                personajeActual = caraGuerrero;
                datosActual = datosGuerrero;
                break;
            case 1:
                personajeActual = caraElfo;
                datosActual = datosElfo;
                break;
            case 2:
                personajeActual = caraMago;
                datosActual = datosMago;
        }
    }

    @Override
    public void dibujar(Graphics grafico) {
        grafico.drawImage(personajeActual, 326, 30, null );
        grafico.drawImage(datosActual, 272, 200, null );
        grafico.drawImage(flechaIzquierda, 235, 95, null);
        grafico.drawImage(flechaDerecha, 517, 95, null);
        grafico.drawImage(botonCrear, 300, 350, null);

        // Rectangulos Caras Jugadores
        grafico.setColor(Color.WHITE);
        grafico.drawRect(326,30, 147, 159);


    }

    public int getPlayerSelected() {
        return playerSelected;
    }
}
