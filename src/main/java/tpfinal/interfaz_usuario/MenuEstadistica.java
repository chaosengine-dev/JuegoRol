package tpfinal.interfaz_usuario;

import tpfinal.graficos.Sprite;
import tpfinal.graficos.SpritesSheet;
import tpfinal.Jugador;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Arma la barra de estadistica de la parte inferior de la pantalla.
 * Muestra las barras de estado del personaje.
 * Muestra el inventario de posiones y llaves.
 */
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
    private Graphics grafico;
    private Jugador jugador;

    /**
     * Constructor de la clase.
     * Arma los rectangulos del area de menu, el principal y los de las pociones.
     */
    public MenuEstadistica() {
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

    /**
     * Dibuja todos los elementos del menu, este metodo se ejecuta tantas veces por segundo como
     * indica el iterador principal
     * @param grafico Objeto Graphics donde se dibuja.
     * @param jugador Objeto jugador con la informacion del personaje.
     */
    public void dibujar(Graphics grafico, Jugador jugador){
        this.grafico = grafico;
        this.jugador = jugador;
        dibujarMenu();
        dibujarBarraEnergia();
        dibujarTextos();
        dibujarSectorInventario(boxInventario1);
        dibujarSectorInventario(boxInventario2);
        dibujarSectorInventario(boxInventario3);
        dibujarSectorInventario(boxInventario4);
        dibujarSectorInventario(boxInventario5);
        dibujarSectorInventario(boxInventario6);
        dibujarObjetos();

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

    /**
     * Dibuja el rectangulo inferior donde va el menu.
     */
    private void dibujarMenu(){
        grafico.setColor(new Color(77,77,77));
        grafico.fillRect(areaMenu.x, areaMenu.y, areaMenu.width, areaMenu.height);
        grafico.setColor(Color.white);
        grafico.fillRect(contenedorMenu.x, contenedorMenu.y, contenedorMenu.width, contenedorMenu.height);
    }

    /**
     * Muestra los textos fijos del menu.
     */
    private void dibujarTextos(){
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

    /**
     * Dibuja las 3 barras de estado utilizando la clase BarraEstadistica.
     */
    private void dibujarBarraEnergia(){
        BarraEstadistica.dibujar(areaMenu.x + 36, areaMenu.y + 4, colorVida, grafico, jugador.getFuerza(), jugador.getFuerzaMaxima());
        BarraEstadistica.dibujar(areaMenu.x + 36, areaMenu.y + 24, colorResistencia, grafico, jugador.getResistencia(), jugador.getResistenciaMaxima());
        BarraEstadistica.dibujar(areaMenu.x + 36, areaMenu.y + 44, colorExperiencia, grafico, jugador.getExperiencia(), jugador.getExperienciaMaxima());
    }

    /**
     * Dibuja cada recuadro del inventario
     * @param box Rectangle con la info del recuadro del inventario
     */
    private void dibujarSectorInventario(Rectangle box){
        grafico.setColor(new Color(102,100,100));
        grafico.fillRect(box.x, box.y, box.width, box.height);
        grafico.setColor(Color.WHITE);
        grafico.drawRect(box.x-1, box.y-1, box.width+1, box.height+1);
    }

    /**
     * Lee desde el archivo de sprites los objetos.
     * Los guarda en un arreglo.
     * Luego dibuja los iconos (3 pociones y 3 llaves) en sus respectivos recuadros.
     */
    private void dibujarObjetos(){
        SpritesSheet hoja = new SpritesSheet("Recursos/Objetos/pociones.png", 32, false);
        Sprite[] pociones = new Sprite[6];
        pociones[0] = hoja.obtenerSprite(1);
        pociones[1] = hoja.obtenerSprite(2);
        pociones[2] = hoja.obtenerSprite(3);
        pociones[3] = hoja.obtenerSprite(13);
        pociones[4] = hoja.obtenerSprite(14);
        pociones[5] = hoja.obtenerSprite(15);
        BufferedImage imagen = pociones[0].getImagen();
        grafico.drawImage(imagen, boxInventario1.x, boxInventario1.y, null);
        imagen = pociones[1].getImagen();
        grafico.drawImage(imagen, boxInventario2.x, boxInventario2.y, null);
        imagen = pociones[2].getImagen();
        grafico.drawImage(imagen, boxInventario3.x, boxInventario3.y, null);
        imagen = pociones[3].getImagen();
        grafico.drawImage(imagen, boxInventario4.x, boxInventario4.y, null);
        imagen = pociones[4].getImagen();
        grafico.drawImage(imagen, boxInventario5.x, boxInventario5.y, null);
        imagen = pociones[5].getImagen();
        grafico.drawImage(imagen, boxInventario6.x, boxInventario6.y, null);
    }
}
