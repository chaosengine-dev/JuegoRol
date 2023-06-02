package tpfinal.admin.manageusers;
import javax.swing.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;

public class ManageUsers extends JFrame {
    private JLabel titulo;
    private JPanel ventana;
    private JButton siguienteButton;
    private JButton anteriorButton;
    private JButton user1;
    private JButton user2;
    private JButton user3;
    private JButton user4;

    public ManageUsers() {
        add(ventana);
        setSize(700, 500); //tamaño de la ventana
        setLocationRelativeTo(null); // centra la ventana en la pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // cierra el programa al cerrar la ventana

        setTitle("Manage Users"); // titulo de la ventana

        // muestro los datos de un archivo json en la ventana
        mostrarUser("src\\tpfinal\\login\\imagenes\\portada.png");

    }



    // metodo para mostrar la ventana
    public void mostrarVentana() {
        JFrame frame = new JFrame("ManageUsers");
        frame.setContentPane(new ManageUsers().ventana);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    // metodo para mostrar un archivo json en la ventana
   /* public void mostrarJson(String json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode jsonNode = new ObjectMapper().readTree(json);
            if (jsonNode.isArray()) {
                for (JsonNode node : jsonNode) {
                    String nombre = node.get("nombre").asText();
                    System.out.println(nombre);
                }
            }else{
                String nombre = jsonNode.get("nombre").asText();
                System.out.println(nombre);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }*/

    // metodo para mostrar los User en cada uno de los label de la ventana, recibiendo los datos de un archivo json
    public void mostrarUser(String json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode jsonNode = new ObjectMapper().readTree(json);
            if (jsonNode.isArray()) {
                for (JsonNode node : jsonNode) {
                    String nombre = node.get("nombre").asText();
                    String apellido = node.get("apellido").asText();
                    String correo = node.get("correo").asText();
                    String telefono = node.get("telefono").asText();
                    System.out.println(nombre);
                    System.out.println(apellido);
                    System.out.println(correo);
                    System.out.println(telefono);
                }
            }else{
                String nombre = jsonNode.get("nombre").asText();
                String apellido = jsonNode.get("apellido").asText();
                String correo = jsonNode.get("correo").asText();
                String telefono = jsonNode.get("telefono").asText();
                System.out.println(nombre);
                System.out.println(apellido);
                System.out.println(correo);
                System.out.println(telefono);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
