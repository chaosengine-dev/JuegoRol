package tpfinal.persistencia;

import tpfinal.login.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import tpfinal.login.registration.Registration;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * Crud para leer, guardar, modificar y eliminar objetos del tipo usuario en el archivo Json.
 * Implementa la interface generica IRepository.
 */
public class UsuarioRepositorio implements IRepository<User> {
    private final File archivo = new File("Recursos/login/archivos/usuarios.json");
    private final ObjectMapper mapper = new ObjectMapper();
    private List<User> users;


    public UsuarioRepositorio() {
        cargar();
    }

    /**
     * Lee el archivo Json y lo guarda en un objeto del tipo CoolectionType.
     * Luego convierte esa colleccion en un List de objectos tipo User.
     * Captura el posible error en tiempo de ejecucion al no poder leer el archivo
     * e inicializa la lista como un ArrayList.
     */
    @Override
    public void cargar() {
        try {
            CollectionType listType = mapper.getTypeFactory().constructCollectionType(List.class, User.class);
            this.users = mapper.readValue(archivo, listType);
        } catch (IOException e) {
            this.users = new ArrayList<>();
        }
    }

    /**
     * Escribe el archivo Json con todo el contenido de la lista de usuarios.
     * Captura el posible error en tiempo de ejecucion al no poder leer el archivo y muestra un mensaje.
     */
    @Override
    public void guardar() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new FileOutputStream(archivo), this.users);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el archivo: " + e.getMessage());
        }
    }

    /**
     * Obtiene la lista de objetos User usando el metodo cargar de esta misma clase.
     *
     * @return La lista de objetos User.
     */
    @Override
    public List<User> listar() {
        cargar();
        return this.users;
    }

    /**
     * Agrega los objetos del tipo User enviados por parametro, puede recibir uno o mas objetos del mismo tipo.
     * Verifica que el usuario no exista, que los datos ingresados en el formulario sean correctos
     * crea el objeto y lo persiste.
     *
     * @param objeto Objeto o lista de objetos del tipo User
     * @return True si se guardo en forma correcta o false si hubo algun error.
     */
    @Override
    public void agregar(User... objeto) {
        this.users.addAll(List.of(objeto));
        guardar();
    }

    /**
     * Elimina un usuario segun el nombre de usuario enviado por parametro
     *
     * @param userName Nombre del usuario a eliminar.
     */
    @Override
    public void eliminar(String userName) {
        cargar();
        for (User user : this.users) {
            if (Objects.equals(user.getUsername(), userName)) {
                this.users.remove(user);
                break;
            }
        }
        guardar();
    }

    /**
     * Modifica los datos de un usuario en el caso de existir.
     * Carga todos los usuarios al List, busca el que coincida
     * Pide el usuario modificado y modifica todos sus datos y aplica los cambios.
     *
     * @param userModificado usuario ya modificado.
     */
    @Override
    public void modificar(User userModificado) {
        cargar();
        for(User user : users){
            if(user.getId() == userModificado.getId()){
                users.set(users.indexOf(user), userModificado);
                break;
            }
        }
        guardar();
    }
}
