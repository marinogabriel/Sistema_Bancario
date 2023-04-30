package controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import model.Conta;
import model.ContaDAO;
import model.Cliente;
import model.ClienteDAO;
import view.ContaTableModel;
import view.GenericTableModel;


public class Controller {
    
    private static Cliente clienteSelecionado = null;
    private static Conta contaSelecionada = null;
    
    private static JTextField clienteSelecionadoTextField = null;
    private static JTextField contaSelecionadaTextField = null;


    public static void setTextFields(JTextField cliente, JTextField conta) {
        clienteSelecionadoTextField = cliente;
        contaSelecionadaTextField = conta;
    }     
    
    public static void setTableModel(JTable table, GenericTableModel tableModel) {
        table.setModel(tableModel);
    }
    
    public static Cliente getClienteSelecionado() {
        return clienteSelecionado;
    }
    
    public static Conta getContaSelecionado() {
        return contaSelecionada;
    }
    
    public static void setSelected(Object selected) {
        if(selected instanceof Cliente) {
            clienteSelecionado = (Cliente) selected;
            clienteSelecionadoTextField.setText(clienteSelecionado.getNome());
            contaSelecionadaTextField.setText("");
        }else if(selected instanceof Conta) {
            contaSelecionada = (Conta) selected;
            contaSelecionadaTextField.setText(contaSelecionada.getId());
        }
    }
    
    public static void clearSelected(Object selected) {
         if(selected instanceof Cliente) {
            clienteSelecionadoTextField.setText("");
            contaSelecionadaTextField.setText("");
        } else if(selected instanceof Conta) {
            contaSelecionadaTextField.setText("");
        }
    }
    
    public static boolean jRadioButtonClientes(JTable table) {
        setTableModel(table, new ClienteTableModel(ClienteDAO.getInstance().retrieveAll()));
        return true;
    }    
    
    public static boolean jRadioButtonAnimais(JTable table) {
        if(getClienteSelecionado() != null) {
            setTableModel(table, new ContaTableModel(ContaDAO.getInstance().retrieveByIdCliente(getClienteSelecionado().getId())));
            return true;
        }else {
            setTableModel(table, new ContaTableModel(new ArrayList()));
            return false;
        }
    }
    
    public static boolean jRadioButtonConsultas(JTable table) {
        setTableModel(table, new ConsultaTableModel(ConsultaDAO.getInstance().retrieveAll()));
        return true;
    }
    
    public static boolean buscaCliente(JTable table, String text) {
        setTableModel(table, new ClienteTableModel(ClienteDAO.getInstance().retrieveBySimilarName(text)));
        return true;
    }
    
    public static boolean buscaConta(JTable table, String text) {
        setTableModel(table, new ContaTableModel(ContaDAO.getInstance().retrieveBySimilarName(text)));
        return true;
    }
    
    
    public static Transferencia adicionaTranferencia() {
        return ConsultaDAO.getInstance().create(Calendar.getInstance(),8,"","",contaSelecionada.getId(), veterinarioSelecionado.getId(),0, false);
    }
    
    public static boolean atualizaBotaoNovo(JTable table) {
        if((clienteSelecionado!=null)&&(contaSelecionada!=null)&&(veterinarioSelecionado!=null)) {
            ((GenericTableModel) table.getModel()).addItem(adicionaConsulta());
            return true;
        }
        else
            return false;
    }
    
    public static void apagaCliente(Cliente cliente) {
        List<Conta> contas = ContaDAO.getInstance().retrieveByIdCliente(cliente.getId());
        for(Conta conta : contas)
            ContaDAO.getInstance().delete(conta);
        ClienteDAO.getInstance().delete(cliente);
    }
    
    public static void filtraConsultas(JTable table, JToggleButton hoje, JToggleButton vet, JToggleButton pend) {
        String where = "WHERE ";
        if(hoje.isSelected()) {
            where += "data BETWEEN 1670814000000 and 1670900400000 ";
            if(vet.isSelected()) {
                
                where += "AND id_veterinario = "+ veterinarioSelecionado.getId();
                if(pend.isSelected())
                    where += " AND finalizado = false";
            }
            else if(pend.isSelected())
                where += "AND finalizado = false";
        }
        else if(vet.isSelected()) {
            where += "id_veterinario = "+ veterinarioSelecionado.getId();
            if(pend.isSelected()) {
                where += " AND finalizado = false";
            }
        }
        else if(pend.isSelected()) {
            where += "finalizado = false";
        }
        else {
            where = "";
            where += "ORDER BY data, hora";
        }
        String query = "SELECT * FROM consulta "+where;
        ((GenericTableModel) table.getModel()).addListOfItems(ConsultaDAO.getInstance().retrieve(query));
        table.repaint();
    }
}
