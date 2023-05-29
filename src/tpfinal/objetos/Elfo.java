package tpfinal.objetos;

public class Elfo implements Heroe{
    public int fuerzaMaxima = 600;
    public int resistenciaMaxima = 1000;
    public int experienciaMaxima = 250;
    public int fuerzaInicial = 350;
    public int resistenciaInicial = 1000;
    private int experienciaInicial = 100;
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
}
