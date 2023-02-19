package helper;

import java.util.ArrayList;
import java.util.List;
import models.Book;
import models.IEntity;
import models.dto.BookDto;

/**
 *
 * @author Djordjije
 */
public class EntitiesConverter  {
    public static <T extends IEntity> List<T> convertList(List<IEntity> entities) {
        if(entities == null || entities.isEmpty()){
            return null;
        }
        List<T> list = new ArrayList<>();
        for(IEntity entity : entities){
            list.add((T) entity);
        }
        return list;
    }
    public static List<BookDto> convertBookListToBookDtoList(List<Book> books){
        if(books == null)
            return null;
        List<BookDto> booksDto = new ArrayList<>();
        for (Book book : books) {
            booksDto.add(new BookDto(book));
        }
        return booksDto;
    }
}
