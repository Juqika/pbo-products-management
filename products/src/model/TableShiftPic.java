package model;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TableShiftPic extends AbstractTableModel {
    private List<shift_pic> sp;
    private final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    public TableShiftPic(List<shift_pic> sp) {
        this.sp = sp;
    }
    
    @Override
    public int getColumnCount() {
        return sp.size();
    }
    
    @Override
    public int getRowCount() {
        return sp.size();
    }
    
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "id_shift";
            case 1:
                return "name";
            case 2:
                return "start_check_time";
            case 3:
                return "end_check_time";
            case 4:
                return "note";
            default:
                return null;
        }
    }
    
    public Object getValueAt(int row, int column) {
        switch (column) {
            case 0:
                return sp.get(row).getIdShift();
            case 1:
                return sp.get(row).getName();
            case 2:
                return sp.get(row).getStart_check_time();
            case 3:
                return sp.get(row).getEnd_check_time();
            case 4:
                return sp.get(row).getNote();
            default:
                return null;
        }
    }

}
