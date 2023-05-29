package tpfinal.vistas;

import tpfinal.control.AdministrarControles;
import tpfinal.graficos.SpritesSheet;
import tpfinal.interfaz_usuario.MensajeBatalla;
import tpfinal.objetos.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Batalla implements VentanaJuego {
    private Heroe heroe;
    private Enemy enemy;
    private ArrayList<MensajeBatalla> mensajesBatalla;
    private String mensaje = "";
    private int fuerzaHeroe;
    private int fuerzaEnemigo;
    private Rectangle dibujoFuerzaHeroe;
    private Rectangle dibujoFuerzaEnemigo;
    private int posX;
    private int posY;
    private int indice = 1;
    private int iteracion = 0;
    private SpritesSheet hojaSprite;
    private BufferedImage mensajeEnemigo;
    private BufferedImage mensajeHeroe;
    private int saludMaximaEnemigo;

    public Batalla(Heroe heroe, Enemy enemigo){
        this.heroe = heroe;
        this.enemy = enemigo;
        saludMaximaEnemigo = enemigo.getSalud();

        mensajesBatalla = batalla();
        mensaje = mensajesBatalla.get(0).getMensaje();

        // Levanto la imagen que necesito del Heroe
        if (heroe instanceof Guerrero){
            hojaSprite = new SpritesSheet("Recursos/Batalla/guerrero.png",189, 527, false);
        }
        if (heroe instanceof Elfo){
            hojaSprite = new SpritesSheet("Recursos/Batalla/elfo.png",189, 527, false);
        }
        if (heroe instanceof Mago){
            hojaSprite = new SpritesSheet("Recursos/Batalla/mago.png",189, 527, false);
        }
        mensajeHeroe = hojaSprite.obtenerSprite(0).obtenerImagen();

        // Levanto la imagen que necesito del Enemigo
        switch (enemigo.getTipo()){
            case 1:
                hojaSprite = new SpritesSheet("Recursos/Batalla/enemy1.png",189, 527, false);
                break;
            case 2:
                hojaSprite = new SpritesSheet("Recursos/Batalla/enemy2.png",189, 527, false);
                break;
            case 3:
                hojaSprite = new SpritesSheet("Recursos/Batalla/enemy3.png",189, 527, false);

        }
        mensajeEnemigo = hojaSprite.obtenerSprite(0).obtenerImagen();
        dibujoFuerzaHeroe = new Rectangle(211, 196, ((heroe.getFuerza() * 200) / heroe.getFuerzaMaxima()) , 19);
        dibujoFuerzaEnemigo = new Rectangle(281, 441, ((saludMaximaEnemigo * 200) / saludMaximaEnemigo) , 19);
    }

    @Override
    public void actualizar() {
        iteracion++;
        if (iteracion > 100){
            iteracion = 0;
            if (indice < mensajesBatalla.size()){
                mensaje = mensajesBatalla.get(indice).getMensaje();
                posX = mensajesBatalla.get(indice).getPosX();
                posY = mensajesBatalla.get(indice).getPosY();
                fuerzaEnemigo = mensajesBatalla.get(indice).getFuerzaEnemigo();
                fuerzaHeroe = mensajesBatalla.get(indice).getFuerzaHeroe();
                dibujoFuerzaHeroe.width = ((fuerzaHeroe * 200) / heroe.getFuerzaMaxima());
                dibujoFuerzaEnemigo.width = ((fuerzaEnemigo * 200) / saludMaximaEnemigo);
                indice++;
                if (AdministrarControles.teclado.isFinBatalla()){
                    indice = mensajesBatalla.size() + 1;
                    AdministrarControles.teclado.setFinBatalla(false);
                }
            } else if (indice == mensajesBatalla.size()){
                mensaje = "Fin Batalla";
                indice++;
            } else {
                if (heroe.getFuerza() > 0){
                    // Volvemos al juego con Heroe como ganador
                    AdministrarVentanas.cambiarEstadoActual(1);
                } else {
                    // Game Over
                    AdministrarVentanas.cambiarEstadoActual(5);
                }
            }
        }
    }

    @Override
    public void dibujar(Graphics grafico){
        grafico.drawImage(mensajeHeroe, 50, 50, null);
        grafico.drawImage(mensajeEnemigo, 270, 290, null);
        grafico.setFont(new Font("Arial Black", Font.ITALIC, 20));
        grafico.setColor(Color.darkGray);
        grafico.drawString(mensaje, posX, posY);
        grafico.setColor(Color.WHITE);
        grafico.setFont(new Font("Arial", Font.BOLD, 26));
        // Grafico de energia
        grafico.drawRoundRect(210, 195, 200, 20, 5,5);
        grafico.drawString(" " + fuerzaHeroe, 420, 215);
        grafico.drawRoundRect(280, 440, 200, 20, 5,5);
        grafico.drawString(" " + fuerzaEnemigo, 490, 460);

        grafico.setColor(Color.RED);
        grafico.fillRoundRect(dibujoFuerzaHeroe.x, dibujoFuerzaHeroe.y, dibujoFuerzaHeroe.width, dibujoFuerzaHeroe.height, 5,5);
        grafico.fillRoundRect(dibujoFuerzaEnemigo.x, dibujoFuerzaEnemigo.y, dibujoFuerzaEnemigo.width, dibujoFuerzaEnemigo.height, 5,5);
    }

    private ArrayList<MensajeBatalla> batalla(){
        MensajeBatalla msg;
        ArrayList<MensajeBatalla> mensajesBatalla = new ArrayList<>();
        // Puedo crear 2 aaraylist que acompañen la mensajeria para guardar la salud de ambos e ir mostrando
        while (heroe.getFuerza() > 0 && enemy.getSalud() > 0){ // Verifico salud de ambos
            Random dado = new Random();
            int numero = dado.nextInt(6) + 1;
            if (numero % 2 == 0){
                msg = new MensajeBatalla("Turno del Heroe.", enemy.getSalud(), heroe.getFuerza(), 225, 100);
                mensajesBatalla.add(msg);
                // Ataca Heroe
                // Fuerza + Resistencia me da la potencioa del golpe
                double potenciaGolpe = (double)(heroe.getFuerza() /100) + (double)(heroe.getResistencia() / 100);

                // Segun la experiencia tiene mas posibilidad de acertar el golpe
                int aciertaGolpe = 0;
                if (heroe.getExperiencia() <= 200) {
                    aciertaGolpe = dado.nextInt(6) + 1;
                } else if (heroe.getExperiencia() > 300){
                    aciertaGolpe = dado.nextInt(10) + 1;
                } else {
                    aciertaGolpe = dado.nextInt(8) + 1;
                }
                // Al mayor experiencia mas grande es el dado y mas opciones superiores a 3 tenemos
                if (aciertaGolpe > 3 ){
                    enemy.setSalud(enemy.getSalud() - (int)potenciaGolpe);
                    msg = new MensajeBatalla("Golpe acertado: Enemigo " + enemy.getSalud(), enemy.getSalud(), heroe.getFuerza(), 225, 100);
                    mensajesBatalla.add(msg);
                } else {
                    msg = new MensajeBatalla("El heroe ha fallado.", enemy.getSalud(), heroe.getFuerza(), 225, 100);
                    mensajesBatalla.add(msg);
                }
            } else {
                // Ataca Enemigo
                msg = new MensajeBatalla("Turno enemigo.", enemy.getSalud(), heroe.getFuerza(), 290, 330);
                mensajesBatalla.add(msg);
                double potenciaGolpe = (double)(enemy.getFuerza() /100) + (double)(enemy.getSalud() / 100);

                // Segun la experiencia tiene mas posibilidad de acertar el golpe
                int aciertaGolpe = 0;
                if (enemy.getResistencia() <= 200) {
                    aciertaGolpe = dado.nextInt(6) + 1;
                } else if (enemy.getResistencia() > 300){
                    aciertaGolpe = dado.nextInt(10) + 1;
                } else {
                    aciertaGolpe = dado.nextInt(8) + 1;
                }
                // Al mayor experiencia mas grande es el dado y mas opciones superiores a 3 tenemos
                if (aciertaGolpe > 3 ){
                    heroe.setFuerza(heroe.getFuerza() - (int)potenciaGolpe);
                    msg = new MensajeBatalla("Golpe acertado: Heroe " + heroe.getFuerza(), enemy.getSalud(), heroe.getFuerza(), 290, 330);
                    mensajesBatalla.add(msg);
                } else {
                    msg = new MensajeBatalla("El enemigo ha fallado.", enemy.getSalud(), heroe.getFuerza(), 290, 330);
                    mensajesBatalla.add(msg);
                }
            }

        }
        return mensajesBatalla;
    }
}
