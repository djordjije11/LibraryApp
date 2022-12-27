package logics.impl;

import database.sql.brokers.interfaces.IMemberBroker;
import database.sql.connection.SqlConnectionFactory;
import java.util.List;
import logics.interfaces.IMemberLogic;
import models.Member;
import java.sql.Connection;

/**
 *
 * @author Djordjije
 */
public class MemberLogic implements IMemberLogic {
    private final IMemberBroker memberBroker;
    public MemberLogic(IMemberBroker memberBroker){
        this.memberBroker = memberBroker;
    }
    @Override
    public Member createMember(Member member) throws Exception {
        Connection connection = SqlConnectionFactory.getConnection();
        try{
            Member createdMember = memberBroker.createMember(member, connection);
            connection.commit();
            return createdMember;
        } catch(Exception ex){
            connection.rollback();
            throw ex;
        } finally{
            SqlConnectionFactory.releaseConnection(connection);
        }
    }
    @Override
    public Member findMember(Member member) throws Exception {
        Connection connection = SqlConnectionFactory.getConnection();
        try{
            return memberBroker.findMember(member, connection);
        } finally{
            SqlConnectionFactory.releaseConnection(connection);
        }
    }
    @Override
    public List<Member> findMembers(Member member) throws Exception {
        Connection connection = SqlConnectionFactory.getConnection();
        try{
            return memberBroker.findMembers(member, connection);
        } finally{
            SqlConnectionFactory.releaseConnection(connection);
        }
    }
    @Override
    public List<Member> readAllMembers() throws Exception {
        Connection connection = SqlConnectionFactory.getConnection();
        try{
            return memberBroker.readAllMembers(new Member(), connection);
        } finally{
            SqlConnectionFactory.releaseConnection(connection);
        }
    }
    @Override
    public Member updateMember(Member member) throws Exception {
        Connection connection = SqlConnectionFactory.getConnection();
        try{
            Member updatedMember = memberBroker.updateMember(member, connection);
            connection.commit();
            return updatedMember;
        } catch(Exception ex){
            connection.rollback();
            throw ex;
        } finally{
            SqlConnectionFactory.releaseConnection(connection);
        }
    }
    @Override
    public Member deleteMember(Member member) throws Exception {
        Connection connection = SqlConnectionFactory.getConnection();
        try{
            Long amount = memberBroker.getCountOfAllLendingsByMember(member, connection);
            if(amount > 0) {
                throw new Exception("A member can't be deleted from database if it has a history of lendings.");
            }
            Member deletedMember = memberBroker.deleteMember(member, connection);
            connection.commit();
            return deletedMember;
        } catch(Exception ex){
            connection.rollback();
            throw ex;
        } finally{
            SqlConnectionFactory.releaseConnection(connection);
        }
    }
}