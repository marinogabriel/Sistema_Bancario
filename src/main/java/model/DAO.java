package model;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class DAO {
    public static final String DBURL = "jdbc:sqlite:si405.db";
    private static Connection con;
    protected static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    // Connect to SQLite
    public static Connection getConnection() {
        if (con == null) {
            try {
                con = DriverManager.getConnection(DBURL);
                if (con != null) {
                    DatabaseMetaData meta = con.getMetaData();
                }
            } catch (SQLException e) {
                System.err.println("Exception: " + e.getMessage());
            }
        }
        return con;
    }

    protected ResultSet getResultSet(String query) {
        Statement s;
        ResultSet rs = null;
        try {
            s = (Statement) con.createStatement();
            rs = s.executeQuery(query);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return rs;
    }

    protected int executeUpdate(PreparedStatement queryStatement) throws SQLException {
        int update;
        update = queryStatement.executeUpdate();
        return update;
    }

    protected int lastId(String tableName, String primaryKey) {
        Statement s;
        int lastId = -1;
        try {
            s = (Statement) con.createStatement();
            ResultSet rs = s.executeQuery("SELECT MAX(" + primaryKey + ") AS id FROM " + tableName);
            if (rs.next()) {
                lastId = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return lastId;
    }

    public static void terminar() {
        try {
            (DAO.getConnection()).close();
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    // Create table SQLite
    protected final boolean createTable() {
        try {
            PreparedStatement stmt;
            // Table cliente:
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS cliente( \n"
                    + "id INTEGER PRIMARY KEY, \n"
                    + "nome VARCHAR, \n"
                    + "cpf VARCHAR, \n"
                    + "dataNasc DATE); \n");
            executeUpdate(stmt);
            // Table conta:
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS conta( \n"
                    + "id INTEGER PRIMARY KEY, \n"
                    + "dataAbertura DATE, \n"
                    + "tipo VARCHAR, \n"
                    + "saldo DOUBLE, \n"
                    + "limTransacao DOUBLE, \n"
                    + "idCliente INTEGER, \n"
                    + "limCredito INTEGER NULL, \n"
                    + "dia INTEGER NULL); \n");   
            executeUpdate(stmt);
            // Table transfer:
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS transferencia( \n"
                    + "id INTEGER PRIMARY KEY, \n"
                    + "data DATE, \n"
                    + "valor DOUBLE, \n"
                    + "idSaida INTEGER, \n"
                    + "idEntrada INTEGER); \n");
            executeUpdate(stmt);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}