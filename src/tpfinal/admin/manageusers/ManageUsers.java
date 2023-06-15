package tpfinal.admin.manageusers;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import tpfinal.graficos.CanvasVentana;
import tpfinal.login.models.User;
import tpfinal.persistencia.LeerArchivos;
import tpfinal.persistencia.repositorios.GestionRepo;
import tpfinal.vistas.VentanaJuego;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;

public class ManageUsers extends JFrame implements VentanaJuego {
    private JPanel ventana;
    private JComboBox comboUsers;
    private JTextField usuario;
    private JPasswordField password1;
    private JPasswordField password2;
    private JCheckBox soyAdministradorCheckBox;
    private JTextField email;
    private JButton button1;
    private JLabel textoUsername;
    private JLabel textoPassword;
    private JLabel textoRepeatpassword;
    private JLabel textoEmail;
    private JLabel textoAdministrarUsuario;
    private Font fuente = LeerArchivos.leerFuente("Recursos/Fuentes/Enchanted Land.otf");

    private ArrayList<User> usuariosRegistrados = new ArrayList<>();

    public ManageUsers() {
        add(ventana);
        setSize(700, 500); //tamaño de la ventana
        setLocationRelativeTo(null); // centra la ventana en la pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // cierra el programa al cerrar la ventana
        setVisible(true); // hace visible la ventana
        textoAdministrarUsuario.setFont(fuente.deriveFont(72f));
        button1.setFont(fuente.deriveFont(36f));
        textoUsername.setFont(fuente.deriveFont(28f));
        textoEmail.setFont(fuente.deriveFont(28f));
        textoPassword.setFont(fuente.deriveFont(28f));
        textoRepeatpassword.setFont(fuente.deriveFont(28f));

        setTitle("ADMINISTRAR USUARIOS"); // titulo de la ventana
        GestionRepo gestion = new GestionRepo();
        usuariosRegistrados = (ArrayList<User>) gestion.listar();

        // muestro los datos de un archivo json en la ventana
        mostrarUser(usuariosRegistrados);

        comboUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sel = (String) comboUsers.getSelectedItem();
                User userSelected = gestion.obtenerUsuario(sel);
                usuario.setText(userSelected.getUsername());
                password1.setText(userSelected.getPassword());
                password2.setText(userSelected.getSecondPassword());
                email.setText(userSelected.getEmail());
                if (userSelected.getisAdmin()) {
                    soyAdministradorCheckBox.setSelected(true);
                } else {
                    soyAdministradorCheckBox.setSelected(false);
                }
            }
        });
    }


    // metodo para mostrar los User en cada uno de los label de la ventana, recibiendo los datos de un archivo json
    public void mostrarUser(ArrayList<User> usuariosRegistrados) {
        comboUsers.addItem("Elija usuario...");
        for (User user : usuariosRegistrados
        ) {
            comboUsers.addItem(user.getUsername());
        }

    }

    @Override
    public void actualizar() {

    }

    @Override
    public void dibujar(Graphics grafico) {

    }

    {
    public JComponent $$$getRootComponent$$$() {
        return ventana;
    }

}
