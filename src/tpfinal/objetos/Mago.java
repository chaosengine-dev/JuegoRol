package tpfinal.objetos;

public class Mago implements Heroe{

    private int tipo = 2;
    private int fuerzaMaxima = 400;
    private int resistenciaMaxima = 800;
    private int experienciaMaxima = 350;
    private int fuerzaInicial = 300;
    private int resistenciaInicial = 800;
    private int experienciaInicial = 300;

    @Override
    public int getFuerzaMaxima() {
        return fuerzaMaxima;
    }
    @Override
    public int getResistenciaMaxima() {
        return resistenciaMaxima;
    }
    @Override
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
    @Override
    public int getTipo() {
        return tipo;
    }
}
