package controller;

import java.sql.Connection;

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

}
