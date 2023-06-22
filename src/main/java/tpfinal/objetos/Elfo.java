package tpfinal.objetos;

/**
 * Clase que describe un tipo de Personaje implementa la interface Heroe.
 * Tiene sus atributos de fuerza, experiencia y resistencia.
 */
public class Elfo implements Heroe{
    private int tipo = 1;
    private int fuerzaMaxima = 600;
    private int resistenciaMaxima = 1000;
    private int experienciaMaxima = 250;
    private int fuerzaInicial = 350;
    private int resistenciaInicial = 1000;
    private int experienciaInicial = 100;

    public Elfo(){

    }
    public int getFuerzaMaxima() {
        return fuerzaMaxima;
    }
    public int getResistenciaMaxima() {
        return resistenciaMaxima;
    }
    public int getExperienciaMaxima() {
        return experienciaMaxima;
    }
    @Override
    public int getFuerza() {
        return fuerzaInicial;
    }
    @Override
    public void setFuerza(int fuerza) {
        this.fuerzaInicial = fuerza;
    }
    @Override
    public int getExperiencia() {
        return experienciaInicial;
    }
    @Override
    public void setExperiencia(int experiencia) {
        this.experienciaInicial = experiencia;
    }
    @Override
    public int getResistencia() {
        return resistenciaInicial;
    }
    @Override
    public void setResistencia(int resistencia) {
        this.resistenciaInicial = resistencia;
    }
    public int getTipo() {
        return tipo;
    }

}
