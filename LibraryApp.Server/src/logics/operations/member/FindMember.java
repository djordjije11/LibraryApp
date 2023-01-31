package logics.operations.member;

import database.sql.brokers.impl.SqlMemberBroker;
import database.sql.brokers.interfaces.IMemberBroker;
import java.sql.Connection;
import logics.operations.Operation;
import models.Member;

/**
 *
 * @author Djordjije
 */
public class FindMember extends Operation<Member> {
    private final IMemberBroker memberBroker;
    private Member member;
    public FindMember(){
        memberBroker = new SqlMemberBroker();
    }
    public void setMember(Member member){
        this.member = member;
    }
    @Override
    protected void checkPrecondition(Connection connection) throws Exception {
        //NO PRECONDITION
    }
    @Override
    protected Member executeOperation(Connection connection) throws Exception {
        return memberBroker.findMember(member, connection);
    }
    @Override
    protected boolean isTransaction() {
        return false;
    }
}
