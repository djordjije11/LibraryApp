package logics.impl;

import java.util.List;
import logics.interfaces.ILendingLogic;
import models.Lending;
import logics.operations.lending.CreateLendings;
import logics.operations.lending.FindUnreturnedLendingsByMember;
import logics.operations.lending.ReturnLendings;

/**
 *
 * @author Djordjije
 */
public class LendingLogic implements ILendingLogic {
    @Override
    public List<Lending> createLendings(List<Lending> lendings) throws Exception {
        CreateLendings operation = new CreateLendings();
        operation.setLendings(lendings);
        return operation.execute();
    }
    @Override
    public List<Lending> returnLendings(List<Lending> lendings) throws Exception {
        ReturnLendings operation = new ReturnLendings();
        operation.setLendings(lendings);
        return operation.execute();
    }

    @Override
    public List<Lending> findUnreturnedLendingsByMember(Lending lending) throws Exception {
        FindUnreturnedLendingsByMember operation = new FindUnreturnedLendingsByMember();
        operation.setLending(lending);
        return operation.execute();
    }
}
