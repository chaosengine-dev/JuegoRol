package tpfinal.mapas;

import tpfinal.graficos.Sprite;
import tpfinal.graficos.SpritesSheet;
import tpfinal.persistencia.LeerArchivos;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

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
    public Capa(String ruta, int ladoSprite){
        String contenidoFile = LeerArchivos.leerArchivoTexto(ruta);
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

    public void dibujar(Graphics grafico, int posicionXJugador, int posicionYJugador, int ladoSprite){
        for (int y = 0; y < alto; y++){
            for (int x = 0; x < ancho; x++){
                int indice = sprites[x + y * ancho];
                BufferedImage imagen = paletas[indice].obtenerImagen();
                int puntoX = x * ladoSprite - posicionXJugador + MARGEN_X;
                int puntoY = y * ladoSprite - posicionYJugador + MARGEN_Y;
                grafico.drawImage(imagen, puntoX, puntoY, null);
            }
        }
    }
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
}
