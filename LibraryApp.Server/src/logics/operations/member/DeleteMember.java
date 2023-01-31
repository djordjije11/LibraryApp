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
public class DeleteMember extends Operation<Member> {
    private final IMemberBroker memberBroker;
    private Member member;
    
    public DeleteMember(){
        memberBroker = new SqlMemberBroker();
    }
    public void setMember(Member member){
        this.member = member;
    }
    
    @Override
    protected void checkPrecondition(Connection connection) throws Exception {
        if(memberBroker.getCountOfAllLendingsByMember(member, connection) > 0){
            throw new Exception("A member cannot be deleted from database if it has a history of lendings.");
        }
    }
    @Override
    protected Member executeOperation(Connection connection) throws Exception {
        return memberBroker.deleteMember(member, connection);
    }
    @Override
    protected boolean isTransaction() {
        return true;
    }
}
