package logics.operations.member;

import database.sql.brokers.impl.SqlMemberBroker;
import database.sql.brokers.interfaces.IMemberBroker;
import java.sql.Connection;
import java.time.LocalDate;
import logics.operations.Operation;
import models.Member;

/**
 *
 * @author Djordjije
 */
public class CreateMember extends Operation<Member> {
    private final IMemberBroker memberBroker;
    private Member member;
    public CreateMember(){
        memberBroker = new SqlMemberBroker();
    }
    public void setMember(Member member){
        this.member = member;
    }
    @Override
    protected void checkPrecondition(Connection connection) throws Exception {
        String firstname = member.getFirstname();
        String lastname = member.getLastname();
        LocalDate birthday = member.getBirthday();
        if(firstname == null || firstname.isBlank() || lastname == null || lastname.isBlank() || birthday == null){
            throw new Exception("A member is not valid.");
        }
    }
    @Override
    protected Member executeOperation(Connection connection) throws Exception {
        return memberBroker.createMember(member, connection);
    }
    @Override
    protected boolean isTransaction() {
        return true;
    }
}
