package tpfinal.admin.manageusers;
import javax.swing.*;

import tpfinal.graficos.Icono;
import tpfinal.login.models.User;
import tpfinal.persistencia.LeerArchivosTxtPng;
import tpfinal.persistencia.UserExceptions;
import tpfinal.persistencia.UsuarioRepositorio;
import tpfinal.vistas.AdministrarVentanas;
import tpfinal.vistas.VentanaJuego;

import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que crea la ventana de administrar usuarios
 */
public class ManageUsers extends JFrame implements VentanaJuego {
    private JPanel ventana;
    private JComboBox<String> comboUsers;
    private JTextField usuario;
    private JPasswordField password1;
    private JPasswordField password2;
    private JCheckBox soyAdministradorCheckBox;
    private JTextField email;
    private JLabel textoUsername;
    private JLabel textoPassword;
    private JLabel textoRepeatpassword;
    private JLabel textoEmail;
    private JLabel textoAdministrarUsuario;
    private JButton btnGuardar;
    private JLabel elegirUser;
    private JButton btnEliminar;
    private JButton btnCancelar;
    private JTextField idUsuario;

    private JFrame jFramePrincipal;
    private ArrayList<User> usuariosRegistrados;
    private final UsuarioRepositorio gestion = new UsuarioRepositorio();

    /**
     * El constructor le da forma a la ventana JFrame, setea los atributos de la misma.
     * Asigna la tipografia a los JLabel.
     * Le asigna a los botones del formulario un listener que va a detectar el click y ejecutar
     * los metodos correspondientes.
     */
    public ManageUsers() {
        Font FUENTE_MEDIEVAL = LeerArchivosTxtPng.leerFuente("Recursos/Fuentes/eland.ttf");

        jFramePrincipal = new JFrame("ADMINISTRAR USUARIOS");
        jFramePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFramePrincipal.setPreferredSize(new Dimension(850, 720));
        jFramePrincipal.setResizable(true);
        jFramePrincipal.pack();
        jFramePrincipal.setLocationRelativeTo(null);
        jFramePrincipal.setVisible(true);
        ImageIcon icon = Icono.crearIcono();
        jFramePrincipal.setIconImage(icon.getImage());
        // Setear tipografia
        textoAdministrarUsuario.setFont(FUENTE_MEDIEVAL.deriveFont(Font.BOLD, 46));
        textoUsername.setFont(FUENTE_MEDIEVAL.deriveFont(Font.BOLD, 26));
        textoEmail.setFont(FUENTE_MEDIEVAL.deriveFont(Font.BOLD, 26));
        textoPassword.setFont(FUENTE_MEDIEVAL.deriveFont(Font.BOLD, 26));
        textoRepeatpassword.setFont(FUENTE_MEDIEVAL.deriveFont(Font.BOLD, 26));
        soyAdministradorCheckBox.setFont(FUENTE_MEDIEVAL.deriveFont(Font.BOLD, 26));
        elegirUser.setFont(FUENTE_MEDIEVAL.deriveFont(Font.BOLD, 26));

        usuariosRegistrados = (ArrayList<User>) gestion.listar();

        // muestro los datos de un archivo json en la ventana
        mostrarUser(usuariosRegistrados);
        comboUsers.addActionListener(cambioUsuarioComboBox());

        // Asigno listener a los botones
        btnGuardar.addActionListener(clickBotonGuardar());
        btnEliminar.addActionListener(clickEliminarUsuario());
        btnCancelar.addActionListener(clickBotonCancelar());

        jFramePrincipal.add(ventana);
        jFramePrincipal.pack();
        jFramePrincipal.setLocationRelativeTo(null);
        jFramePrincipal.setVisible(true);
    }

    private ActionListener clickBotonCancelar(){
        return e -> {
            jFramePrincipal.dispose();
            AdministrarVentanas.iniciarVentanaAdmin();
            AdministrarVentanas.cambiarEstadoActual(11);
        };
    }
    private ActionListener clickBotonGuardar() {
        return e -> {
            try {
                // TODO: Se debe separar la logica de crear el usuario de la persistencia
                User newUser = new User(usuario.getText(), password1.getText(), password2.getText(), email.getText());

                //ASIGNO EL ID AL USUARIO
                newUser.setId(Integer.parseInt(idUsuario.getText()));

                //VALIDAR EL USUARIO ANTES DE AGREGARLO
                validarUsuario(newUser);

                //VERIFICAR ADMIN
                if (soyAdministradorCheckBox.isSelected()){
                    newUser.setAdmin(true);
                }
                //MODIFICAR USUARIO
                gestion.modificar(newUser);

                JOptionPane.showMessageDialog(null, "USUARIO MODIFICADO " + newUser.getUsername() + ".", "Usuario modificado", JOptionPane.INFORMATION_MESSAGE);
                jFramePrincipal.dispose();
                AdministrarVentanas.iniciarVentanaGestionUsuarios();
                AdministrarVentanas.cambiarEstadoActual(12);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        };
    }

    private boolean validarUsuario(User user) throws UserExceptions {
        boolean existe = false;
        //VALIDAR EXISTENCIA DEL USUARIO
        for (User existingUser : usuariosRegistrados) {
            if (existingUser.getUsername().equals(user.getUsername()) && existingUser.getId() != user.getId()) {
                existe = true;
                break;
            }
        }
        if (existe) {
            throw new UserExceptions("El usuario " + user.getUsername() + " ya existe");
        }

        //VALIDAR DATOS DEL USUARIO
        if (user.getUsername().length() < 6) {
            throw new UserExceptions("El nombre de usuario debe tener al menos 6 caracteres");
        }
        if (!user.getUsername().matches("[a-zA-Z0-9]*")) {
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
        return true;
    }

    private ActionListener clickEliminarUsuario() {
        return e -> {
            if (comboUsers.getSelectedItem() != null && comboUsers.getSelectedIndex() != 0){
                System.out.println(comboUsers.getSelectedIndex());
                String sel = (String) comboUsers.getSelectedItem();
                int option = JOptionPane.showConfirmDialog(null, "Seguro desea eliminar el usuario " + sel + "?");
                if (option == 0){
                    gestion.eliminar(sel);
                    jFramePrincipal.dispose();
                    AdministrarVentanas.iniciarVentanaGestionUsuarios();
                    AdministrarVentanas.cambiarEstadoActual(12);
                    JOptionPane.showMessageDialog(null, "USUARIO ELIMINADO. ", "Eliminar usuario", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        };
    }
    private ActionListener cambioUsuarioComboBox() {
        return e -> {
            if (comboUsers.getSelectedItem() != null && comboUsers.getSelectedIndex() != 0){
                String sel = (String) comboUsers.getSelectedItem();
                User userSelected = obtenerUsuario(sel);
                usuario.setText(userSelected.getUsername());
                password1.setText(userSelected.getPassword());
                password2.setText(userSelected.getSecondPassword());
                email.setText(userSelected.getEmail());
                idUsuario.setText(String.valueOf(userSelected.getId()));
                if (userSelected.getisAdmin()) {
                    soyAdministradorCheckBox.setSelected(true);
                } else {
                    soyAdministradorCheckBox.setSelected(false);
                }
            }
        };
    }

    /**
     * Busca en la lista de User, el usuario que coincida con el nombre enviado por parametro.
     *
     * @param nombre Nombre de usuario buscado
     * @return Objeto del tipo User encontrado, null en caso contrario.
     */
    public User obtenerUsuario(String nombre) {
        UsuarioRepositorio gestion = new UsuarioRepositorio();
        for (User user : gestion.listar()) {
            if (user.getUsername().equals(nombre)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Crea el combobox con los usuarios que lee del ArrayList enviado por parametro
     *
     * @param usuariosRegistrados ArrayList con todos los usuarios registrados, que lee del Json.
     */
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

}
