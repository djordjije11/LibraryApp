package logics.operations;

import database.sql.connection.SqlConnectionFactory;
import java.sql.Connection;
/**
 *
 * @author Djordjije
 */
public abstract class Operation<ReturnType>{
    public final ReturnType execute() throws Exception{
        Connection connection = SqlConnectionFactory.getConnection();
         try {
            checkPrecondition(connection);
            ReturnType dbObject = executeOperation(connection);
            if(isTransaction() == true){
                connection.commit();
            }
            return dbObject;
        } catch(Exception ex){
            if(isTransaction() == true){
                connection.rollback();
            }
            throw ex;
        } finally{
            SqlConnectionFactory.releaseConnection(connection);
        }
    }
    protected abstract void checkPrecondition(Connection connection) throws Exception;
    protected abstract ReturnType executeOperation(Connection connection) throws Exception;
    protected abstract boolean isTransaction();
}
