package tpfinal.admin.adminpage;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPage extends JFrame{
    private JPanel ventana;
    private JButton jugarButton;
    private JButton gestionarUsuariosButton;
    private JLabel titulo;

    public AdminPage(){
        add(ventana);
        setSize(700, 500); //tamaño de la ventana
        setLocationRelativeTo(null); // centra la ventana en la pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // cierra el programa al cerrar la ventana

        setTitle("Admin Page"); // titulo de la ventana

        jugarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Entrando al juego...");
            }
        });
        gestionarUsuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Gestionando usuarios...");
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
