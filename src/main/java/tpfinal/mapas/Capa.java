package tpfinal.mapas;

import tpfinal.graficos.Sprite;
import tpfinal.graficos.SpritesSheet;
import tpfinal.persistencia.LeerArchivosTxtPng;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Clase padre de las capas de Mapa, Objetos, Enemigos y Ambiente.
 */
public abstract class Capa {

    protected int ancho;

    protected int alto;
    protected String[] partes;
    protected final Sprite[] paletas;
    protected int[] sprites;
    protected final int ANCHO_JUEGO = 800;
    protected final int ALTO_JUEGO = 600;
    protected final int LADO_SPRITE = 32;
    protected final int MARGEN_X = (ANCHO_JUEGO / 2) - (LADO_SPRITE / 2);
    protected final int MARGEN_Y = (ALTO_JUEGO / 2) - (LADO_SPRITE / 2);

    /**
     * Constructor de la clase padre Capa.
     * Lee el archivo de texto y luego lo divide en un arreglo de Strings.
     * Identificando las partes del archivo y su informacion.
     * Ancho y alto del mapa, hoja de sprites usada, sprites usados en cada posicion y si colisiona o no.
     * @param ruta String desde donde se lee el archivo con la informacion de la capa.
     * @param ladoSprite int con el valor del lado del sprite, siempre usamos sprites cuadrados.
     */
    public Capa(String ruta, int ladoSprite){
        String contenidoFile = LeerArchivosTxtPng.leerArchivoTexto(ruta);
        partes = contenidoFile.split("&");
        ancho = Integer.parseInt(partes[0]);
        alto = Integer.parseInt(partes[1]);

        // Leo la hoja usada
        String hojaUtilizada = partes[2];

        // Leo la paleta de sprites
        String paletaEntera = partes[3];
        String[] partesPaletas = paletaEntera.split("#");

        // Iniciamos el array contando la cantidad de paletas usadas
        paletas = asignarSprites(partesPaletas, hojaUtilizada, ladoSprite);

        // Leo el mapa
        String spritesCompleto = partes[4];
        String[] spritesFila = spritesCompleto.split(" ");

        sprites = extraerSprites(spritesFila);
    }

    /**
     * Recorre el arreglo de sprites, obtiene el indice de imagen y lo busca en la hoja.
     * Luego lo muestra en pantalla.
     * @param grafico Objeto Graphics donde se dibuja el juego.
     * @param posicionXJugador posicion del jugador con respecto al eje X
     * @param posicionYJugador posicion del jugador con respecto al eje Y
     * @param ladoSprite int con el tamaño del sprite a mostrar.
     */
    public void dibujar(Graphics grafico, int posicionXJugador, int posicionYJugador, int ladoSprite){
        for (int y = 0; y < alto; y++){
            for (int x = 0; x < ancho; x++){
                int indice = sprites[x + y * ancho];
                BufferedImage imagen = paletas[indice].getImagen();
                int puntoX = x * ladoSprite - posicionXJugador + MARGEN_X;
                int puntoY = y * ladoSprite - posicionYJugador + MARGEN_Y;
                grafico.drawImage(imagen, puntoX, puntoY, null);
            }
        }
    }

    /**
     * Metodo que lee la hoja de sprites y segun el valor que tenga en la poscion 0 referencia del sprite
     * y valor en la posicion 2, numero de sprite de la hoja de sprites.
     * Luego con el primer valor lo uso de indice y el segundo obtengo el sprite deseado
     * guardandolo en un arreglo de sprites, que luego se retorna.
     * @param partesPaleta String[], Contiene la referencia entre el mapa y el sprite en la hoja de sprite.
     * @param hojaSprite String, Archivo para obtener las imagenes.
     * @param ladoSprite int, Tamaño del lado del sprite.
     * @return Sprite[], arreglo de imagenes sprites.
     */
    private Sprite[] asignarSprites(final String[] partesPaleta, final String hojaSprite, int ladoSprite){
        Sprite[] paletas = new Sprite[partesPaleta.length];
        SpritesSheet hoja = new SpritesSheet("Recursos/Sprites/" + hojaSprite, ladoSprite, false);
        for (int i = 0; i < partesPaleta.length; i++){
            String spriteTemporal = partesPaleta[i];
            String[] partesSprite = spriteTemporal.split(":");
            int indicePaleta = Integer.parseInt(partesSprite[0]);
            int indiceSprite = Integer.parseInt(partesSprite[2]);
            paletas[indicePaleta] =   hoja.obtenerSprite(indiceSprite);
        }
        return paletas;
    }

    /**
     * Recorre la cadenaSprites, arreglo que contiene todos los sprites del mapa leido desde el archivo de texto.
     * Crea un ArrayList con todos los enteros que va leyendo.
     * Por la estructura del archivo hay un string que tiene 4 digitos, ese string lo corta en 2 y 2 y los agrega
     * por separado.
     * @param cadenaSprites String[], arreglo con los numeros como String leidas del archivo de texto.
     * @return int[], arreglo de int convertidos a partir de las cadenas leidas.
     */
    private int[] extraerSprites(String[] cadenaSprites){
        ArrayList<Integer> sprites = new ArrayList<>();
        for (int i = 0; i < cadenaSprites.length; i++ ){
            if (cadenaSprites[i].length() == 2){
                sprites.add(Integer.parseInt(cadenaSprites[i]));
            } else {
                String one = "";
                String two = "";
                one += cadenaSprites[i].charAt(0);
                one += cadenaSprites[i].charAt(1);
                two += cadenaSprites[i].charAt(2);
                two += cadenaSprites[i].charAt(3);
                sprites.add(Integer.parseInt(one));
                sprites.add(Integer.parseInt(two));
            }
        }

        int[] vectorSprites = new int[sprites.size()];
        for (int i = 0; i < sprites.size(); i++){
            vectorSprites[i] = sprites.get(i);
        }
        return vectorSprites;
    }

    public int[] getSprites() {
        return sprites;
    }

    public void setSprites(int[] sprites) {
        this.sprites = sprites;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }
}
