package forms.lending.table;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import models.Author;
import models.Book;
import models.CopyOfBook;

/**
 *
 * @author Djordjije
 */
public class CopiesOfBookTableModel extends AbstractTableModel {
    private List<CopyOfBook> copiesOfBook;
    private final String[] columnNames = {"ID","Naslov", "Autor"};
    private final Class[] columnTypes = {Long.class, String.class, Author.class};
    
    public CopiesOfBookTableModel(List<CopyOfBook> copiesOfBook){
        this.copiesOfBook = copiesOfBook;
    }
    public CopyOfBook getCopyOfBook(int index){
        if(index < 0)
            return null;
        return copiesOfBook.get(index);
    }
    @Override
    public int getRowCount() {
        if(copiesOfBook == null || copiesOfBook.isEmpty()){
            return 0;
        }
        return copiesOfBook.size();
    }
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CopyOfBook copyOfBook = copiesOfBook.get(rowIndex);
        Book book = copyOfBook.getBook();
        switch (columnIndex) {
            case 0:
                return copyOfBook.getId();
            case 1:
                if(book == null){
                    return "n/a";
                }
                return book.getTitle();
            case 2:
                if(book == null){
                    return "n/a";
                }
                return book.getAuthor();
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
