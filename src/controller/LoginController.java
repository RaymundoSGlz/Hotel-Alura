package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.Usuario;
import views.Login;
import views.MenuUsuario;

public class LoginController implements ActionListener {

    private Login login;

    public LoginController(Login login) {
        this.login = login;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String usuario = login.getUsuario();
        String contraseña = login.getContraseña();

        if (Usuario.validarUsuario(usuario, contraseña)) {
            MenuUsuario menu = new MenuUsuario();
            menu.setVisible(true);
            login.dispose();
        } else {
            JOptionPane.showMessageDialog(login, "Usuario o Contraseña no válidos");
        }
    }

}
