package forms.book.table;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import models.Author;
import models.dto.BookDto;

/**
 *
 * @author Djordjije
 */
public class BooksTableModel extends AbstractTableModel {
    private List<BookDto> books;
    private final String[] columnNames = {"ID","Naslov", "Autor"};
    private final Class[] columnTypes = {Long.class, String.class, Author.class};
    
    public BooksTableModel(List<BookDto> books){
        this.books = books;
    }
    public void updateMembersList(List<BookDto> books){
        this.books = books;
        fireTableDataChanged();
    }
    public BookDto getBook(int index){
        if(index < 0)
            return null;
        return books.get(index);
    }
    @Override
    public int getRowCount() {
        if(books == null)
            return 0;
        return books.size();
    }
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        BookDto book = books.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return book.getId();
            case 1:
                return book.getTitle();
            case 2:
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