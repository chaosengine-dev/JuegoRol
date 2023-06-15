package tpfinal.control;

import javax.print.attribute.standard.Media;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Esta clase maneja la capa de sonidos.
 */
public abstract class Musica {
    /**
     * Reproduce en un loop infinito el tema de fondo.
     */
    public static void fondo() {
        reproducirAudio("Recursos/Sonidos/onGameOne.wav", 0);
    }

    /**
     * Reproduce una sola vez un sonido para el click del mouse.
     */
    public static void clickBoton() {
        reproducirAudio("Recursos/sonidos/pressButton.wav", 1);
    }

    /**
     * Metodo que se encarga de leer el archivo de audio, inicializar un objeto del tipo Clip.
     * Y lo reproduce, definiendo si es en loop o una vez, y el volumen.
     * @param nombreSonido String con el archivo a reproducir.
     * @param loop 0 si es infinito o 1 si es una sola vez.
     */
    private static void reproducirAudio(String nombreSonido, int loop) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(nombreSonido).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            // Obtener el control de volumen
            FloatControl controlVolumen = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            // Reducir el volumen en decibelios (dB)
            float dB = 19.0f; // Puedes ajustar este valor para disminuir el volumen
            float gain = (float) (Math.log10(1.0f - (dB / 20.0f)) * 20.0f);
            controlVolumen.setValue(gain);
            if (loop == 0){
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.out.println("Error al reproducir el sonido.");
        }
    }
}
