package tpfinal.graficos;

import tpfinal.persistencia.LeerArchivosTxtPng;

import javax.swing.*;
import java.awt.image.BufferedImage;

/**
 * Crea el icono de las ventanas.
 */
public abstract class Icono {

    public static ImageIcon crearIcono(){
        BufferedImage imagen = LeerArchivosTxtPng.cargarImagenTransparente("Recursos/Mapas/logo.png");
        ImageIcon icon = new ImageIcon(imagen);
        return icon;
    }

}
