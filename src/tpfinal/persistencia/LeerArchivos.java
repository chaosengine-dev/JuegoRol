package tpfinal.persistencia;

import tpfinal.vistas.Juego;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class LeerArchivos {
    private LeerArchivos(){

    }
    public static BufferedImage cargarImagenOpaca(final String ruta){
        Image imagen = null;
        try {
            File file = new File(ruta);
            //imagen = ImageIO.read(Juego.class.getResource(ruta));
            imagen = ImageIO.read(file);

        } catch (IOException e){
            System.out.println(e);
        }

        GraphicsConfiguration graphicsConfiguration = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();

        BufferedImage imagenCompatible = graphicsConfiguration
                .createCompatibleImage(imagen.getWidth(null),
                        imagen.getHeight(null),
                        Transparency.OPAQUE);

        Graphics grafico = imagenCompatible.getGraphics();
        grafico.drawImage(imagen, 0,0, null);
        grafico.dispose();
        return imagenCompatible;
    }

    public static BufferedImage cargarImagenTransparente(final String ruta){

        Image imagen = null;
        try {
            File file = new File(ruta);
            //imagen = ImageIO.read(Juego.class.getResource(ruta));
            imagen = ImageIO.read(file);

        } catch (IOException e){
            System.out.println(e);
        }

        GraphicsConfiguration graphicsConfiguration = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();

        BufferedImage imagenCompatible = graphicsConfiguration
                .createCompatibleImage(imagen.getWidth(null),
                        imagen.getHeight(null),
                        Transparency.TRANSLUCENT);

        Graphics grafico = imagenCompatible.getGraphics();
        grafico.drawImage(imagen, 0,0, null);
        grafico.dispose();
        return imagenCompatible;
    }

    // Leer mapa desde archivo txt
    public static String leerArchivoTexto(final String ruta){
        String contenido = "";
        String linea = "";
        try{
            InputStream archivo = new FileInputStream(ruta);
            BufferedReader reader = new BufferedReader(new InputStreamReader(archivo));

            while ((linea = reader.readLine()) != null){
                contenido += linea;
            }

            if (archivo != null){
                archivo.close();
            }
            if (reader != null){
                reader.close();
            }

        } catch (IOException e){
            e.printStackTrace();
        }
        return contenido;
    }

    public static Font leerFuente(String ruta){
        Font fuente = null;
        try{
            //InputStream entradaFuente = Juego.class.getResourceAsStream(ruta);
            InputStream entradaFuente = new FileInputStream(ruta);
            fuente = Font.createFont(Font.TRUETYPE_FONT, entradaFuente);
        } catch (IOException | FontFormatException e) {
            System.out.println(e.getMessage());
        }
        fuente = fuente.deriveFont(10f);
        return fuente;
    }
}
