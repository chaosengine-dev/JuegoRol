package tpfinal.persistencia;

import tpfinal.login.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
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
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    /**
     * Obtiene la lista de objetos User usando el metodo cargar de esta misma clase.
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
     * @param objeto Objeto o lista de objetos del tipo User
     * @return True si se guardo en forma correcta o false si hubo algun error.
     */
    @Override
    public boolean agregar(User... objeto) {
        boolean nuevoUserCorrecto = false;
        cargar();
        try {
            for (User user : objeto) {
                boolean existe = false;

                for (User existingUser : this.users) {
                    if (existingUser.getUsername().equalsIgnoreCase(user.getUsername())) {
                        existe = true;
                        break;
                    }
                }
                if (existe) {
                    throw new UserExceptions("El nombre de usuario " + user.getUsername() + " ya existe");
                }
                if (user.getUsername().length() < 6) {
                    throw new UserExceptions("El nombre de usuario debe tener al menos 6 caracteres");
                }
                if (!user.getUsername().matches("[a-zA-Z0-9]+")) {
                    throw new UserExceptions("El nombre de usuario solo puede contener letras y numeros");
                }
                if (user.getPassword().length() < 6) {
                    throw new UserExceptions("La contraseña debe tener al menos 6 caracteres");
                }
                if (!user.getPassword().equals(user.getSecondPassword())) {
                    throw new UserExceptions("Las contraseñas no coinciden");
                }
                if (!user.getEmail().contains("@")) {
                    throw new UserExceptions("El email debe contener un @");
                }
                int nextID = getNextID(); // OBTENER EL ID SIGUIENTE
                user.setId(nextID); // ASIGNO EL ID AL USUARIO
                this.users.addAll(Arrays.asList(objeto));
                guardar();
                JOptionPane.showMessageDialog(null, "El nombre de usuario " + user.getUsername() + " se ha agregado correctamente");
                nuevoUserCorrecto = true;
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());

        } finally {
            return nuevoUserCorrecto;
        }
    }

    /**
     * Obtiene el siguiente id para almacenar un User nuevo.
     * @return int con el id siguiente.
     */
    private int getNextID(){
        int nextID = 0;
        for(User user : this.users){
            if(user.getId() > nextID){
                nextID = user.getId();
            }
        }
        return nextID + 1;
    }

    /**
     * Elimina un usuario segun el nombre de usuario enviado por parametro
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
     * Busca en la lista de User, el usuario que coincida con el nombre enviado por parametro.
     * @param nombre Nombre de usuario buscado
     * @return Objeto del tipo User encontrado, null en caso contrario.
     */
    public User obtenerUsuario(String nombre){
        for (User user: users
             ) {
            if (user.getUsername().equals(nombre)){
                return user;
            }
        }
        return null;
    }

    /**
     * Modifica los datos de un usuario en el caso de existir.
     * Carga todos los usuarios al List, busca el que coincida
     * Pide los datos para modificar y lo guarda al finalizar.
     * @param userName nombre de usuario que deseamos modificar.
     */
    @Override
    public void modificar(String userName){
        cargar();
        Scanner scanner = new Scanner(System.in);
        for(User user : this.users){
            if(Objects.equals(user.getUsername(), userName)){
                System.out.println("Ingrese el nuevo nombre de usuario: ");
                user.setUsername(scanner.nextLine());
                System.out.println("Ingrese la nueva contraseña: ");
                user.setPassword(scanner.nextLine());
                System.out.println("Ingrese la nueva contraseña nuevamente: ");
                user.setSecondPassword(scanner.nextLine());
                System.out.println("Ingrese el nuevo email: ");
                user.setEmail(scanner.nextLine());
                break;
            }
        }
        guardar();
    }

    /**
     * Verifica si existe el nombre de usuario en el json y devuelve true en caso que exista
     * o false en caso contrario.
     * @param username Nombre de usuario buscado.
     * @return true si existe el usuario, false en caso contrario.
     */
    public boolean existeUsername(String username) {
        cargar();
        for(User user : this.users){
            if(Objects.equals(user.getUsername(), username)){
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica si existe el usuario es administrador y devuelve true en caso que lo sea
     * o false en caso contrario.
     * @param username Nombre de usuario buscado.
     * @return true si el usuario es administrador, false en caso contrario.
     */
    public boolean isAdmin(String username) {
        cargar();
        for(User user : this.users){
            if(Objects.equals(user.getUsername(), username)){
                return user.getisAdmin();
            }
        }
        return false;
    }

    /**
     * Verifica si el conjunto username y password pertenencen a un usuario del json.
     * @param username Nombre de usuario buscado.
     * @param password Password del usuario buscado.
     * @return true si el conjunto es valido, false en caso contrario.
     */
    public boolean verificarCredenciales(String username, String password) {
        cargar();
        for(User user : this.users){
            if(Objects.equals(user.getUsername(), username) && Objects.equals(user.getPassword(), password)){
                return true;
            }
        }
        return false;
    }
}
