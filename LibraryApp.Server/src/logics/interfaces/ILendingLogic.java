package logics.interfaces;

import java.util.List;
import models.Lending;

/**
 *
 * @author Djordjije
 */
public interface ILendingLogic {
    List<Lending> createLendings(List<Lending> lendings) throws Exception;
    List<Lending> returnLendings(List<Lending> lendings) throws Exception;
}
