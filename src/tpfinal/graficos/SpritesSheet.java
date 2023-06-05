package tpfinal.graficos;

import tpfinal.persistencia.LeerArchivos;

import java.awt.image.BufferedImage;

public class SpritesSheet {
    final private int anchoHojaPixeles;
    final private int altoHojaPixeles;
    final private int anchoHojaSprites;
    final private int altoHojaSprites;
    final private int anchoSprites;
    final private int altoSprites;
    final private Sprite[] sprites;

    public SpritesSheet(final String ruta, final int spriteSize, final boolean hojaOpaca){
        final BufferedImage imagen;
        if (hojaOpaca){
            imagen = LeerArchivos.cargarImagenOpaca(ruta);
        } else {
            imagen = LeerArchivos.cargarImagenTransparente(ruta);
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
    public SpritesSheet(final String ruta, final int altoSprites, final int anchoSprites, final boolean hojaOpaca){
        final BufferedImage imagen;
        if (hojaOpaca){
            imagen = LeerArchivos.cargarImagenOpaca(ruta);
        } else {
            imagen = LeerArchivos.cargarImagenTransparente(ruta);
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
    private void generarSpritesDesdeImagen(final BufferedImage imagen){
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
