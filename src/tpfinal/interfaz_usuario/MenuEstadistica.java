package tpfinal.interfaz_usuario;

import tpfinal.graficos.Sprite;
import tpfinal.graficos.SpritesSheet;
import tpfinal.Jugador;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class MenuEstadistica {
    private int anchoJuego = 800;
    private int altoJuego = 600;
    private Rectangle areaMenu;
    private Rectangle contenedorMenu;
    private Rectangle boxInventario1;
    private Rectangle boxInventario2;
    private Rectangle boxInventario3;
    private Rectangle boxInventario4;
    private Rectangle boxInventario5;
    private Rectangle boxInventario6;
    private Color colorVida = new Color(12,49,93);
    private Color colorResistencia = new Color(212,7,11);
    private Color colorExperiencia = new Color(101, 212, 7);

    public MenuEstadistica(Jugador jugador) {
        int altoMenu = 64;
        int ladoBoxInventario = 40;
        areaMenu = new Rectangle(0, altoJuego - altoMenu, anchoJuego, altoMenu);
        contenedorMenu = new Rectangle(areaMenu.x, areaMenu.y - 1, areaMenu.width, 1);
        boxInventario1 = new Rectangle(areaMenu.x + 200, areaMenu.y + 5, ladoBoxInventario, ladoBoxInventario );
        boxInventario2 = new Rectangle(boxInventario1.x + 50, areaMenu.y + 5, ladoBoxInventario, ladoBoxInventario );
        boxInventario3 = new Rectangle(boxInventario2.x + 50, areaMenu.y + 5, ladoBoxInventario, ladoBoxInventario );
        boxInventario4 = new Rectangle(boxInventario3.x + 50, areaMenu.y + 5, ladoBoxInventario, ladoBoxInventario );
        boxInventario5 = new Rectangle(boxInventario4.x + 50, areaMenu.y + 5, ladoBoxInventario, ladoBoxInventario );
        boxInventario6 = new Rectangle(boxInventario5.x + 50, areaMenu.y + 5, ladoBoxInventario, ladoBoxInventario );
    }

    public void dibujar(Graphics grafico, Jugador jugador){
        dibujarMenu(grafico);
        dibujarBarraEnergia(grafico, jugador);
        dibujarTextos(grafico);
        dibujarSectorInventario(grafico, boxInventario1);
        dibujarSectorInventario(grafico, boxInventario2);
        dibujarSectorInventario(grafico, boxInventario3);
        dibujarSectorInventario(grafico, boxInventario4);
        dibujarSectorInventario(grafico, boxInventario5);
        dibujarSectorInventario(grafico, boxInventario6);
        dibujarObjetos(grafico);

        String texto = "" + jugador.getInventario()[0];
        grafico.drawString(texto, boxInventario1.x + 25, boxInventario1.y + 33);
        texto = "" + jugador.getInventario()[1];
        grafico.drawString(texto, boxInventario2.x + 25, boxInventario2.y + 33);
        texto = "" + jugador.getInventario()[2];
        grafico.drawString(texto, boxInventario3.x + 25, boxInventario3.y + 33);
        texto = "" + jugador.getInventario()[3];
        grafico.drawString(texto, boxInventario4.x + 25, boxInventario4.y + 33);
        texto = "" + jugador.getInventario()[4];
        grafico.drawString(texto, boxInventario5.x + 25, boxInventario5.y + 33);
        texto = "" + jugador.getInventario()[5];
        grafico.drawString(texto, boxInventario6.x + 25, boxInventario6.y + 33);
    }

    private void dibujarMenu(Graphics grafico){
        grafico.setColor(new Color(77,77,77));
        grafico.fillRect(areaMenu.x, areaMenu.y, areaMenu.width, areaMenu.height);
        grafico.setColor(Color.white);
        grafico.fillRect(contenedorMenu.x, contenedorMenu.y, contenedorMenu.width, contenedorMenu.height);
    }
    private void dibujarTextos(Graphics grafico){
        grafico.setColor(Color.WHITE);
        grafico.drawString("PWR", areaMenu.x + 1, areaMenu.y + 16);
        grafico.drawString("RES", areaMenu.x + 1, areaMenu.y + 36);
        grafico.drawString("EXP", areaMenu.x + 1, areaMenu.y + 56);
        grafico.drawString("1", (boxInventario1.x + boxInventario1.width / 2),  areaMenu.y + 60);
        grafico.drawString("2", (boxInventario2.x + boxInventario2.width / 2),  areaMenu.y + 60);
        grafico.drawString("3", (boxInventario3.x + boxInventario3.width / 2),  areaMenu.y + 60);
        grafico.drawString("4", (boxInventario4.x + boxInventario4.width / 2),  areaMenu.y + 60);
        grafico.drawString("5", (boxInventario5.x + boxInventario5.width / 2),  areaMenu.y + 60);
        grafico.drawString("6", (boxInventario6.x + boxInventario6.width / 2),  areaMenu.y + 60);
    }

    private void dibujarBarraEnergia(Graphics grafico, Jugador jugador){
        BarraEstadistica.dibujar(areaMenu.x + 36, areaMenu.y + 4, colorVida, grafico, jugador.getFuerza(), jugador.getFuerzaMaxima());
        BarraEstadistica.dibujar(areaMenu.x + 36, areaMenu.y + 24, colorResistencia, grafico, jugador.getResistencia(), jugador.getResistenciaMaxima());
        BarraEstadistica.dibujar(areaMenu.x + 36, areaMenu.y + 44, colorExperiencia, grafico, jugador.getExperiencia(), jugador.getExperienciaMaxima());
    }
    private void dibujarSectorInventario(Graphics grafico, Rectangle box){
        grafico.setColor(new Color(102,100,100));
        grafico.fillRect(box.x, box.y, box.width, box.height);
        grafico.setColor(Color.WHITE);
        grafico.drawRect(box.x-1, box.y-1, box.width+1, box.height+1);
    }
    private void dibujarObjetos(Graphics grafico){
        SpritesSheet hoja = new SpritesSheet("Recursos/Objetos/pociones.png", 32, false);
        Sprite[] pociones = new Sprite[6];
        pociones[0] = hoja.obtenerSprite(1);
        pociones[1] = hoja.obtenerSprite(2);
        pociones[2] = hoja.obtenerSprite(3);
        pociones[3] = hoja.obtenerSprite(13);
        pociones[4] = hoja.obtenerSprite(14);
        pociones[5] = hoja.obtenerSprite(15);
        BufferedImage imagen = pociones[0].obtenerImagen();
        grafico.drawImage(imagen, boxInventario1.x, boxInventario1.y, null);
        imagen = pociones[1].obtenerImagen();
        grafico.drawImage(imagen, boxInventario2.x, boxInventario2.y, null);
        imagen = pociones[2].obtenerImagen();
        grafico.drawImage(imagen, boxInventario3.x, boxInventario3.y, null);
        imagen = pociones[3].obtenerImagen();
        grafico.drawImage(imagen, boxInventario4.x, boxInventario4.y, null);
        imagen = pociones[4].obtenerImagen();
        grafico.drawImage(imagen, boxInventario5.x, boxInventario5.y, null);
        imagen = pociones[5].obtenerImagen();
        grafico.drawImage(imagen, boxInventario6.x, boxInventario6.y, null);
    }
}
