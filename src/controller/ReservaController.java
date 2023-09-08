package controller;

import java.sql.Connection;
import java.util.List;

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

    public List<Reserva> buscar() {
        return this.reservaDAO.buscar();
    }

    public List<Reserva> buscarId(String id) {
        return this.reservaDAO.buscarId(id);
    }

}
