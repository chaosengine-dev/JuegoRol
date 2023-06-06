package tpfinal.persistencia.repositorios;

import tpfinal.persistencia.UserExceptions;
import tpfinal.login.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class GestionRepo implements IRepository<User> {
    private final File archivo = new File("src/tpfinal/login/archivos/usuarios.json");
    private final ObjectMapper mapper = new ObjectMapper();
    private List<User> users;

    public GestionRepo() {
    }

    @Override
    public void cargar() {
        try {
            CollectionType listType = mapper.getTypeFactory().constructCollectionType(List.class, User.class);
            this.users = mapper.readValue(archivo, listType);
        } catch (IOException e) {
            this.users = new ArrayList<>();
        }
    }

    @Override
    public void guardar() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new FileOutputStream(archivo), this.users);
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    @Override
    public List<User> listar() {
        cargar();
        return this.users;
    }

    @Override
    public void agregar(User... objecto) throws UserExceptions {
        cargar();
        try {
            for (User user : objecto) {
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
                this.users.addAll(Arrays.asList(objecto));
                guardar();
                JOptionPane.showMessageDialog(null, "El nombre de usuario " + user.getUsername() + " se ha agregado correctamente");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());}
    }

    private int getNextID(){
        int nextID = 0;
        for(User user : this.users){
            if(user.getId() > nextID){
                nextID = user.getId();
            }
        }
        return nextID + 1;
    }
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

    public User obtenerUsuario(String nombre){
        for (User user: users
             ) {
            if (user.getUsername().equals(nombre)){
                return user;
            }
        }
        return null;
    }

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

    public boolean existeUsername(String username) {
        cargar();
        for(User user : this.users){
            if(Objects.equals(user.getUsername(), username)){
                return true;
            }
        }
        return false;
    }

    public boolean isAdmin(String username) {
        cargar();
        for(User user : this.users){
            if(Objects.equals(user.getUsername(), username)){
                return user.getisAdmin();
            }
        }
        return false;
    }

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
