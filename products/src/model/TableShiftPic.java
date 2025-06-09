package model;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import java.time.LocalDateTime;

public class TableShiftPic extends AbstractTableModel {
    private List<shift_pic> sp;
    private final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    public TableShiftPic(List<shift_pic> sp) {
        this.sp = sp;
    }
    
    @Override
    public int getColumnCount() {
        return 5; // Jumlah kolom: id_shift, name, start_check_time, end_check_time, note
    }
    
    @Override
    public int getRowCount() {
        return sp.size();
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID";
            case 1:
                return "Name";
            case 2:
                return "Start Time";
            case 3:
                return "End Time";
            case 4:
                return "Note";
            default:
                return null;
        }
    }
    
    @Override
    public Object getValueAt(int row, int column) {
        switch (column) {
            case 0:
                return sp.get(row).getIdShift();
            case 1:
                return sp.get(row).getName();
            case 2:
                LocalDateTime startTime = sp.get(row).getStart_check_time();
                return startTime != null ? startTime.format(df) : "";
            case 3:
                LocalDateTime endTime = sp.get(row).getEnd_check_time();
                return endTime != null ? endTime.format(df) : "";
            case 4:
                return sp.get(row).getNote();
            default:
                return null;
        }
    }
    
    // Tambahkan metode ini untuk menampilkan tanggal dalam format yang lebih baik
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 0) {
            return Integer.class;
        } else if (columnIndex == 2 || columnIndex == 3) {
            return String.class; // Menampilkan LocalDateTime sebagai String
        } else {
            return String.class;
        }
    }
}
