package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Animal;
import model.AnimalDAO;
import model.ClienteDAO;
import model.Consulta;
import model.ConsultaDAO;
import model.VeterinarioDAO;


public class ContasTableModel extends GenericTableModel {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public ContasTableModel(List vDados) {
        super(vDados, new String[]{"Data", "Hora", "Cliente", "Animal", "Veterinário", "Obs", "Exames", "Finalizada"});
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return Integer.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return String.class;
            case 5:
                return String.class; 
            case 6:
                return String.class;
            case 7:
                return Boolean.class;
             
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
    
   @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Consulta consulta = (Consulta) vDados.get(rowIndex);
        Animal animal;
        switch (columnIndex) {
            case 0:
                return dateFormat.format(consulta.getData().getTime());
            case 1:
                return consulta.getHora();
            case 2:
                animal = AnimalDAO.getInstance().retrieveById(consulta.getIdAnimal());
                return ClienteDAO.getInstance().retrieveById(animal.getIdCliente()).getNome();
            case 3:
                animal = AnimalDAO.getInstance().retrieveById(consulta.getIdAnimal());
                return animal.getNome();
            case 4:
                return VeterinarioDAO.getInstance().retrieveById(consulta.getIdVeterinario()).getNome();
            case 5:
                return consulta.getDescricao();
            case 6:
                return consulta.getExames();
            case 7:
                return consulta.isFinalizado();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Consulta consulta = (Consulta) vDados.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                Calendar cal = Calendar.getInstance();
                try {
                    cal.setTime(dateFormat.parse((String)aValue));
                } catch(ParseException ex) {
                    Logger.getLogger(ContasTableModel.class.getName()).log(Level.SEVERE, null, ex);
                }
                consulta.setData(cal);
                break;
            case 1:
                consulta.setHora((Integer)aValue);
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break; 
            case 5:
                consulta.setDescricao((String)aValue);
                break;
            case 6:
                consulta.setExames((String)aValue);
                break;
            case 7:
                consulta.setFinalizado((Boolean)aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        ConsultaDAO.getInstance().update(consulta);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if((columnIndex<2)||(columnIndex>4))
            return true;
        else
            return false;
    }   

}