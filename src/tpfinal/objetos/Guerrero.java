package tpfinal.objetos;

public class Guerrero implements Heroe{

    private int tipo = 0;
    public int fuerzaMaxima = 800;
    public int resistenciaMaxima = 700;
    public int experienciaMaxima = 150;

    private int fuerzaInicial = 350;
    private int resistenciaInicial = 700;
    private int experienciaInicial = 50;

    public int getFuerzaMaxima() {
        return fuerzaMaxima;
    }

    public int getResistenciaMaxima() {
        return resistenciaMaxima;
    }

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
