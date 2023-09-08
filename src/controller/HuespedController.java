package controller;

import java.sql.Connection;
import java.util.List;

import DAO.HuespedDAO;
import factory.ConexionDB;
import modelo.Huesped;

public class HuespedController {

    private HuespedDAO huespedDAO;

    public HuespedController() {
        Connection conexion = new ConexionDB().conectar();

        this.huespedDAO = new HuespedDAO(conexion);
    }

    public void guardar(Huesped huesped) {
        huespedDAO.guardar(huesped);
    }

    public List<Huesped> buscar() {
        return this.huespedDAO.buscar();
    }

    public List<Huesped> buscarId(String id) {
        return this.huespedDAO.buscarId(id);
    }

    public List<Huesped> buscarApellidoHuesped(String apellido) {
        return this.huespedDAO.buscarApellidoHuesped(apellido);
    }

}
