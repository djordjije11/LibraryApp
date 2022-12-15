package logics.impl;

import database.sql.brokers.interfaces.IMemberBroker;
import java.util.List;
import logics.interfaces.IMemberLogic;
import models.Member;

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
        try{
            memberBroker.openConnection();
            memberBroker.openTransaction();
            Member createdMember = memberBroker.createMember(member);
            memberBroker.commitTransaction();
            return createdMember;
        } catch(Exception ex){
            memberBroker.rollbackTransaction();
            throw ex;
        } finally{
            memberBroker.closeConnection();
        }
    }
    @Override
    public Member readMember(Member member) throws Exception {
        try{
            memberBroker.openConnection();
            return memberBroker.readMember(member);
        } finally{
            memberBroker.closeConnection();
        }
    }
    @Override
    public List<Member> readAllMembers() throws Exception {
        try{
            memberBroker.openConnection();
            return memberBroker.readAllMembers(new Member());
        } finally{
            memberBroker.closeConnection();
        }
    }
    @Override
    public Member updateMember(Member member) throws Exception {
        try{
            memberBroker.openConnection();
            memberBroker.openTransaction();
            Member updatedMember = memberBroker.updateMember(member);
            memberBroker.commitTransaction();
            return updatedMember;
        } catch(Exception ex){
            memberBroker.rollbackTransaction();
            throw ex;
        } finally{
            memberBroker.closeConnection();
        }
    }
    @Override
    public Member deleteMember(Member member) throws Exception {
        //ovu logiku treba obnoviti kasnije
        try{
            memberBroker.openConnection();
            memberBroker.openTransaction();
            Member deletedMember = memberBroker.deleteMember(member);
            memberBroker.commitTransaction();
            return deletedMember;
        } catch(Exception ex){
            memberBroker.rollbackTransaction();
            throw ex;
        } finally{
            memberBroker.closeConnection();
        }
    }
}
