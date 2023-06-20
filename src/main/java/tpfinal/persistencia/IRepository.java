package tpfinal.persistencia;

import java.util.List;

public interface IRepository<T> {
    void cargar();
    void guardar();
    List<T> listar();
    void agregar(T... objecto) throws Exception;
    void eliminar(String userName);
    void modificar(T dato);
}