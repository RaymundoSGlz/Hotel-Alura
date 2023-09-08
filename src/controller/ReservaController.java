package controller;

import java.sql.Connection;

import DAO.ReservaDAO;
import factory.ConexionDB;
import modelo.Reserva;

public class ReservaController {

    private ReservaDAO reservaDAO;

    public ReservaController() {
        Connection con = new ConexionDB().conectar();
        this.reservaDAO = new ReservaDAO(con);
    }

    public void guardar(Reserva reserva) {
        reservaDAO.guardar(reserva);
    }

}
