package tpfinal.graficos;

import tpfinal.persistencia.LeerArchivosTxtPng;

import java.awt.image.BufferedImage;

/**
 * Clase que lee una hoja de Sprites y la convierte en un arreglo de sprite.
 * Puede leer hojas traslucidas como hojas opacas, la diferencia es en el tipo de fondo de la imagen.
 * Tiene 2 constructores, uno para sprites cuadrados y otro para sprites rectangulares.
 */
public class SpritesSheet {
    final private int anchoHojaPixeles;
    final private int altoHojaPixeles;
    final private int anchoHojaSprites;
    final private int altoHojaSprites;
    final private int anchoSprites;
    final private int altoSprites;
    final private Sprite[] sprites;

    /**
     * Constructor para sprites cuadrados, se encarga de leer el archivo.
     * Construye el arreglo de la dimension exacta y lo llena de sprites cuadrados.
     * @param ruta String con la ubicacion del archivo.
     * @param spriteSize int con el tamaño del sprite
     * @param hojaOpaca boolean que indica si es traslucida (false) o opaca (true)
     */
    public SpritesSheet(final String ruta, final int spriteSize, final boolean hojaOpaca){
        final BufferedImage imagen;
        if (hojaOpaca){
            imagen = LeerArchivosTxtPng.cargarImagenOpaca(ruta);
        } else {
            imagen = LeerArchivosTxtPng.cargarImagenTransparente(ruta);
        }
        anchoHojaPixeles = imagen.getWidth();
        altoHojaPixeles = imagen.getHeight();

        anchoSprites = spriteSize;
        altoSprites = spriteSize;

        anchoHojaSprites = anchoHojaPixeles / anchoSprites;
        altoHojaSprites = altoHojaPixeles / altoSprites;

        sprites = new Sprite[anchoHojaSprites * altoHojaSprites];
        generarSpritesDesdeImagen(imagen);
    }
    /**
     * Constructor para sprites rectangulares, se encarga de leer el archivo.
     * Construye el arreglo de la dimension exacta y lo llena de sprites rectangulares.
     * @param ruta String con la ubicacion del archivo.
     * @param altoSprites int con el alto del sprite
     * @param anchoSprites int con el ancho del sprite
     * @param hojaOpaca boolean que indica si es traslucida (false) o opaca (true)
     */
    public SpritesSheet(final String ruta, final int altoSprites, final int anchoSprites, final boolean hojaOpaca){
        final BufferedImage imagen;
        if (hojaOpaca){
            imagen = LeerArchivosTxtPng.cargarImagenOpaca(ruta);
        } else {
            imagen = LeerArchivosTxtPng.cargarImagenTransparente(ruta);
        }
        anchoHojaPixeles = imagen.getWidth();
        altoHojaPixeles = imagen.getHeight();

        this.anchoSprites = anchoSprites;
        this.altoSprites = altoSprites;

        anchoHojaSprites = anchoHojaPixeles / anchoSprites;
        altoHojaSprites = altoHojaPixeles / altoSprites;

        sprites = new Sprite[anchoHojaSprites * altoHojaSprites];
        generarSpritesDesdeImagen(imagen);
    }

    /**
     * Metodo que guarda en el arreglo de Sprites cada sprite leido.
     * Recorro con y hasta el alto, recorro en x hasta el ancho y guardo en la posicion
     * del arreglo el objeto que creo con la clase Sprite.
     * @param imagen BufferedImage construido en el constructor de la clase SpriteSheet.
     */
    private void generarSpritesDesdeImagen(BufferedImage imagen){
        for (int y = 0; y < altoHojaSprites; y++){
            for (int x = 0; x < anchoHojaSprites; x++){
                final int posicionX = x * anchoSprites;
                final int posicionY = y * altoSprites;
                sprites[x + y * anchoHojaSprites] = new Sprite(imagen.
                        getSubimage(posicionX, posicionY, anchoSprites, altoSprites));
            }
        }
    }
    public Sprite obtenerSprite(int indice){
        return sprites[indice];
    }
    public Sprite obtenerSprite(int x,int y){
        return sprites[x + y * anchoHojaSprites];
    }
}
