package tpfinal.objetos;

/**
 * Clase que describe un tipo de Personaje implementa la interface Heroe.
 * Tiene sus atributos de fuerza, experiencia y resistencia.
 */
public class Guerrero implements Heroe{

    private int tipo = 0;
    private int fuerzaMaxima = 800;
    private int resistenciaMaxima = 700;
    private int experienciaMaxima = 150;
    private int fuerzaInicial = 350;
    private int resistenciaInicial = 700;
    private int experienciaInicial = 50;

    @Override
    public int getFuerzaMaxima() {
        return fuerzaMaxima;
    }
    @Override
    public int getResistenciaMaxima() {
        return resistenciaMaxima;
    }
    @Override
    public int getExperienciaMaxima() {return experienciaMaxima;}
    @Override
    public int getFuerza() {
        return fuerzaInicial;
    }

    @Override
    public int getExperiencia() {
        return experienciaInicial;
    }

    @Override
    public int getResistencia() {
        return resistenciaInicial;
    }

    @Override
    public void setFuerza(int fuerza) {
        this.fuerzaInicial = fuerza;
    }

    @Override
    public void setExperiencia(int experiencia) {
        this.experienciaInicial = experiencia;
    }

    @Override
    public void setResistencia(int resistencia) {
        this.resistenciaInicial = resistencia;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
