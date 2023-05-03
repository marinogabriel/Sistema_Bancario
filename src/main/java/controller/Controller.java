package controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JTable;
import javax.swing.JTextField;
import model.Conta;
import model.ContaDAO;
import model.Cliente;
import model.ClienteDAO;
import view.ClienteTableModel;
import view.ContaTableModel;
import view.GenericTableModel;
import view.TelaConta;


public class Controller {
    
    TelaConta conta = new TelaConta();
    
    private static Cliente clienteSelecionado = null;
    public static Conta contaSelecionada = null;
    
    private static JTextField clienteSelecionadoTextField = null;
    private static JTextField contaSelecionadaTextField = null;
    
    private static JTable jTable = null;
    
    public static JTextField saldoTextField = null;
    public static JTextField saldoInputTextField = null;
    public static JTextField contaInputTextField = null;
    public static JTextField valorInputTextField = null;


    public static void setTextFields(JTextField cliente, JTextField conta) {
        clienteSelecionadoTextField = cliente;
        contaSelecionadaTextField = conta;
    }
    
    public static void setJTable(JTable table) {
        jTable = table;
    }

    public static JTable getJTable() {
        return jTable;
    }
    
    public static void setTextFieldsConta(JTextField saldo, JTextField conta, JTextField valor, JTextField saldo2) {
        saldoInputTextField = saldo;
        contaInputTextField = conta;
        valorInputTextField = valor;
        saldoTextField = saldo2;
    }  
    
    public static void setTableModel(JTable table, GenericTableModel tableModel) {
        table.setModel(tableModel);
    }
    
    public static Cliente getClienteSelecionado() {
        return clienteSelecionado;
    }
    
    public static Conta getContaSelecionada() {
        return contaSelecionada;
    }
    
    public static void setSelected(Object selected) {
        if(selected instanceof Cliente) {
            clienteSelecionado = (Cliente) selected;
            clienteSelecionadoTextField.setText(clienteSelecionado.getNome());
            contaSelecionadaTextField.setText("");
        }
        else if(selected instanceof Conta) {
            contaSelecionada = (Conta) selected;
            contaSelecionadaTextField.setText(Integer.toString(contaSelecionada.getId()));
        }
    }
    
    public static boolean jRadioButtonClientes(JTable table) {
        setTableModel(table, new ClienteTableModel(ClienteDAO.getInstance().retrieveAll()));
        return true;
    }
    
    public static boolean jRadioButtonContas(JTable table) {
        if(getClienteSelecionado() != null) {
            setTableModel(table, new ContaTableModel(ContaDAO.getInstance().retrieveByIdCliente(getClienteSelecionado().getId())));
            return true;
        }else {
            setTableModel(table, new ContaTableModel(new ArrayList()));
            return false;
        }
    }
        
    public static void apagaCliente(Cliente cliente) {
        List<Conta> contas = ContaDAO.getInstance().retrieveByIdCliente(cliente.getId());
        for(Conta conta : contas)
            ContaDAO.getInstance().delete(conta);
        ClienteDAO.getInstance().delete(cliente);
    }
        
    public static Conta adicionaConta() {
        return ContaDAO.getInstance().create(Calendar.getInstance(), (double) 0, "Comum", (double) 100, 0, 0, clienteSelecionado.getId());

    }
    
    public static Conta adicionaContaEspecial() {
        return ContaDAO.getInstance().create(Calendar.getInstance(), (double) 0, "Especial", (double) 100, 0, 100, clienteSelecionado.getId());
    }
    
    public static Conta adicionaContaPoupança() {
        return ContaDAO.getInstance().create(Calendar.getInstance(), (double) 0, "Poupança", (double) 100, Calendar.MONTH + 1, 0 , clienteSelecionado.getId());
    }
    
    public static Cliente adicionaCliente() {
        return ClienteDAO.getInstance().create("", "", Calendar.getInstance());
    }
    
    public static boolean atualizaBotaoNovo(JTable table) {
        if(table.getModel() instanceof ClienteTableModel){
            ((GenericTableModel) table.getModel()).addItem(adicionaCliente());
            return true;
        }
        else if(clienteSelecionado!=null) {
            ((GenericTableModel) table.getModel()).addItem(adicionaConta());
            return true;
        }
        else
            return false;
    }
    
    public static boolean atualizaNovoEspecial(JTable table) {
        if(clienteSelecionado!=null) {
            ((GenericTableModel) table.getModel()).addItem(adicionaContaEspecial());
            setSelected(ContaDAO.getInstance().retrieveLast());
            return true;
        }
        else
            return false;
    }
    
    public static boolean atualizaNovoPoupança(JTable table) {
        if(clienteSelecionado!=null) {
            ((GenericTableModel) table.getModel()).addItem(adicionaContaPoupança());
            return true;
        }
        else
            return false;
    }
    
    public static boolean atualizaTransacao(JTable table) {
        setTableModel(table, new ContaTableModel(ContaDAO.getInstance().retrieveByIdCliente(getClienteSelecionado().getId())));
        return true;
    }

}