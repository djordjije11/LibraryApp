package logics.operations.member;

import database.sql.brokers.impl.SqlMemberBroker;
import database.sql.brokers.interfaces.IMemberBroker;
import java.sql.Connection;
import java.util.List;
import logics.operations.Operation;
import models.Member;

/**
 *
 * @author Djordjije
 */
public class ReadAllMembers extends Operation<List<Member>> {
    private final IMemberBroker memberBroker;
    public ReadAllMembers(){
        memberBroker = new SqlMemberBroker();
    }
    @Override
    protected void checkPrecondition(Connection connection) throws Exception {
        //NO PRECONDITION
    }
    @Override
    protected List<Member> executeOperation(Connection connection) throws Exception {
        return memberBroker.readAllMembers(new Member(), connection);
    }
    @Override
    protected boolean isTransaction() {
        return false;
    }
}
