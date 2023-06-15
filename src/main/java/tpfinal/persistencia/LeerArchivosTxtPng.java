package tpfinal.persistencia;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Clase abstracta ya que no se instancia nunca
 * Administra toda la lectura de archivos de texto e imagenes
 */
public abstract class LeerArchivosTxtPng {

    /**
     * Crea un objeto Image a partir de un archivo png.
     * A partir de esta imagen obtiene un BufferedImage con fondo opaco, la dibuja y la devuelve
     * @param ruta Ubicacion de la imagen a leer.
     * @return BufferedImage obtenida desde el objeto Image leido en la ruta.
     */
    public static BufferedImage cargarImagenOpaca(String ruta){
        Image imagen = null;
        try {
            File file = new File(ruta);
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

    /**
     * Crea un objeto Image a partir de un archivo png.
     * A partir de esta imagen obtiene un BufferedImage con fondo traslucido, la dibuja y la devuelve
     * @param ruta Ubicacion de la imagen a leer.
     * @return BufferedImage obtenida desde el objeto Image leido en la ruta.
     */
    public static BufferedImage cargarImagenTransparente(String ruta){

        Image imagen = null;
        try {
            File file = new File(ruta);
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

    /**
     * Clase para leer los archivos de texto guardando lo que lee en un buffer y luego
     * convierte cada linea en un String concatenandolo a la anterior.
     * @param ruta Ubicacion del archivo de texto a leer
     * @return String con todo el contenido del archivo de texto.
     */
    public static String leerArchivoTexto(String ruta){
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
    /**
     * Clase para leer los archivos ttf (True Type Font).
     * Convierte lo que lee en un objeto del tipo Font y le asigna un tamaño predeterminado de 10.
     * @param ruta Ubicacion del archivo de ttf a leer
     * @return Un objeto del tipo Font con la fuente leida.
     */
    public static Font leerFuente(String ruta){
        Font fuente = null;
        try{
            InputStream entradaFuente = new FileInputStream(ruta);
            fuente = Font.createFont(Font.TRUETYPE_FONT, entradaFuente);
        } catch (IOException | FontFormatException e) {
            System.out.println(e.getMessage());
        }
        fuente = fuente.deriveFont(10f);
        return fuente;
    }
}
