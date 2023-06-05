package tpfinal.graficos;

import tpfinal.persistencia.LeerArchivos;

import javax.swing.*;
import java.awt.image.BufferedImage;

public abstract class Icono {

    public static ImageIcon crearIcono(){
        BufferedImage imagen = LeerArchivos.cargarImagenTransparente("Recursos/Mapas/logo.png");
        ImageIcon icon = new ImageIcon(imagen);
        return icon;
    }

}
