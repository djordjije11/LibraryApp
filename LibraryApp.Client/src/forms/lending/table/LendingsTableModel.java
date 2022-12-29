package forms.lending.table;

import java.time.LocalDate;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import models.Book;
import models.CopyOfBook;
import models.Lending;

/**
 *
 * @author Djordjije
 */
public class LendingsTableModel extends AbstractTableModel {
    private List<Lending> lendings;
    private final String[] columnNames = {"ID","Naslov", "ID primerka", "Datum iznajmljivanja"};
    private final Class[] columnTypes = {Long.class, String.class, Long.class, LocalDate.class};
    
    public LendingsTableModel(List<Lending> lendings){
        this.lendings = lendings;
    }
    
    public void clearData(){
        lendings.clear();
        fireTableDataChanged();
    }
    public Lending getLending(int index){
        if(index < 0)
            return null;
        return lendings.get(index);
    }
    @Override
    public int getRowCount() {
        if(lendings == null || lendings.isEmpty()){
            return 0;
        }
        return lendings.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Lending lending = lendings.get(rowIndex);
        CopyOfBook copyOfBook = lending.getCopyOfBook();
        switch (columnIndex) {
            case 0:
                return lending.getId();
            case 1:
                if(copyOfBook != null){
                    Book book = copyOfBook.getBook();
                    if(book != null){
                        return book.getTitle();
                    }
                } 
                return "n/a";
            case 2:
                if(copyOfBook != null){
                    return copyOfBook.getId();
                }
                return "n/a";
            case 3:
                return lending.getLendingDate();
            default:
                return "n/a";
        }
    }
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnTypes[columnIndex];
    }
}
