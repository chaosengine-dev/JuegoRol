package tpfinal.persistencia.repositorios;

import tpfinal.login.models.User;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class UserRepo implements IRepository<User>{
    private final File archivo = new File("D:\\OneDrive - UTN FRMDP\\3er Cuatrimestre\\Programacion y Laboratorio III\\Proyecto\\LogIn\\login3\\login\\src\\Archivos\\usuarios.json");
    private final ObjectMapper mapper = new ObjectMapper();

    private List<User> users;

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
        this.users.addAll(Arrays.asList(objecto));
        guardar();
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
}
