package tpfinal.persistencia;

import java.util.List;

public interface IRepository<T> {
    void cargar();
    void guardar();
    List<T> listar();
    boolean agregar(T... objecto) throws Exception;
    void eliminar(String userName);
    void modificar(String userName);
    boolean existeUsername(String username);
    boolean verificarCredenciales(String username, String password);
}