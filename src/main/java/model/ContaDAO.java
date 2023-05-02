package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static model.DAO.getConnection;


public class ContaDAO extends DAO {
    private static ContaDAO instance;

    private ContaDAO() {
        getConnection();
        createTable();
    }
    
    // Singleton
    public static ContaDAO getInstance() {
        return (instance==null?(instance = new ContaDAO()):instance);
    }

// CRUD    
    public Conta create(Calendar dataAbertura, float saldo, float limTransacao, int idCliente) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO conta (dataAbertura, saldo, limTransacao, idCliente) VALUES (?,?,?,?)");
            stmt.setDate(1, new java.sql.Date(dataAbertura.getTimeInMillis()));
            stmt.setFloat(2, saldo);
            stmt.setFloat(3, limTransacao);
            stmt.setInt(4, idCliente);
            executeUpdate(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("conta","id"));
    }
    
    /* Uma pequena gambiarra, depois explico...
    public boolean isLastEmpty(){
        Conta lastConta = this.retrieveById(lastId("conta","id"));
        if (lastConta != null) {
            return lastConta.getNome().isBlank();
        }
        return false;
    }*/

    private Conta buildObject(ResultSet rs) {
        Conta conta = null;
        try {
            Calendar dt = Calendar.getInstance();
            dt.setTime(rs.getDate("dataAbertura"));
            conta = new Conta(rs.getInt("id"), dt, rs.getString("tipo"), rs.getFloat("saldo"), rs.getFloat("limTransacao"), rs.getInt("idCliente"));
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return conta;
    }

    // Generic Retriever
    public List retrieve(String query) {
        List<Conta> contas = new ArrayList();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()) {
                contas.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return contas;
    }
    
    // RetrieveAll
    public List retrieveAll() {
        return this.retrieve("SELECT * FROM conta");
    }
    
    // RetrieveLast
    public List retrieveLast(){
        return this.retrieve("SELECT * FROM conta WHERE id = " + lastId("conta","id"));
    }

    // RetrieveById
    public Conta retrieveById(int id) {
        List<Conta> contas = this.retrieve("SELECT * FROM conta WHERE id = " + id);
        return (contas.isEmpty()?null:contas.get(0));
    }
    
    // RetrieveByIdConta
    public List retrieveByIdCliente(int idCliente) {
        return this.retrieve("SELECT * FROM conta WHERE idCliente = " + idCliente);
    }

    // RetrieveBySimilarName
    public List retrieveBySimilarName(String nome) {
        return this.retrieve("SELECT * FROM conta WHERE nome LIKE '%" + nome + "%'");
    }    
        
    // Update
    public void update(Conta conta) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE conta SET dataAbertura=?, saldo=?, limTransacao=?, idConta=? WHERE id=?");
            stmt.setDate(1, new Date(conta.getDataAbertura().getTimeInMillis()));
            stmt.setDouble(2, conta.getSaldo());
            stmt.setDouble(3, conta.getLimTransacao());
            stmt.setInt(4, conta.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
        // Delete   
    public void delete(Conta conta) {
        PreparedStatement stmt;
        try {
            stmt = DAO.getConnection().prepareStatement("DELETE FROM conta WHERE id = ?");
            stmt.setInt(1, conta.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
    
    public boolean delete(int id) {
        PreparedStatement stmt;
        try {
            stmt = DAO.getConnection().prepareStatement("DELETE FROM conta WHERE id = ?");
            stmt.setInt(1, id);
            executeUpdate(stmt);
            return true;
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
            return false;
        }
    }
}
