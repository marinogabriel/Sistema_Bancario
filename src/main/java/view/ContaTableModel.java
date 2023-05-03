package view;

import java.text.SimpleDateFormat;
import java.util.List;
import model.Conta;


public class ContaTableModel extends GenericTableModel {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public ContaTableModel(List vDados) {
        super(vDados, new String[]{"Conta", "Tipo", "Saldo"});
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return float.class; 
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
    
   @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Conta conta = (Conta) vDados.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return conta.getId();
            case 1:
                return conta.getTipo();
            case 2:
                return conta.getSaldo();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }   
}