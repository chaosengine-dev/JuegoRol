package tpfinal.objetos;

public class Mago implements Heroe{

    private int tipo = 2;
    public int fuerzaMaxima = 400;
    public int resistenciaMaxima = 800;
    private int experienciaMaxima = 350;

    public int fuerzaInicial = 300;
    public int resistenciaInicial = 800;
    private int experienciaInicial = 300;


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

    @Override
    public int getTipo() {
        return tipo;
    }
}
