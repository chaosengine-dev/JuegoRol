package tpfinal.control;

import javax.print.attribute.standard.Media;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Musica {
    public static void fondo() {
        reproducirAudio("Recursos/Sonidos/onGameOne.wav", 0);
    }
    public static void clickBoton() {
        reproducirAudio("Recursos/sonidos/pressButton.wav", 1);
    }
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
