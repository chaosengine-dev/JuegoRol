package tpfinal.persistencia.repositorios;

import tpfinal.login.models.User;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class UserRepo implements IRepository<User>{
    private final File archivo = new File("src/tpfinal/login/archivos/usuarios.json");
    private final ObjectMapper mapper = new ObjectMapper();
    private List<User> users;

    public UserRepo() {
    }

    @Override
    public void cargar(){
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
    public List<User> listar(){
        cargar();
        return this.users;
    }

    @Override
    public void agregar(User... objecto){
        cargar();
        for(User user : objecto){
            boolean existe = false;

            for(User existingUser : this.users){
                if(existingUser.getUsername().equalsIgnoreCase(user.getUsername())){
                    existe = true;
                    break;
                }
            }
            if (existe){
                JOptionPane.showMessageDialog(null, "El nombre " + user.getUsername() + " ya existe");
            }else{
                this.users.addAll(Arrays.asList(objecto));
                guardar();
                JOptionPane.showMessageDialog(null, "El nombre de usuario " + user.getUsername() + " se ha agregado correctamente");
            }
        }
    }
    @Override
    public void eliminar(String userName){
        cargar();
        for(User user : this.users){
            if(Objects.equals(user.getUsername(), userName)){
                this.users.remove(user);
                break;
            }
        }
        guardar();
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
