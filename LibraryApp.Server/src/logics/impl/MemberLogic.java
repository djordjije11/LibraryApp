/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logics.impl;

import database.sql.brokers.interfaces.IMemberBroker;
import java.time.LocalDate;
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
    public Member createMember(String firstname, String lastname, LocalDate birthday, String email) throws Exception {
        try{
            memberBroker.openConnection();
            memberBroker.openTransaction();
            Member member = memberBroker.createMember(new Member(firstname, lastname, birthday, email));
            memberBroker.commitTransaction();
            return member;
        } catch(Exception ex){
            memberBroker.rollbackTransaction();
            throw ex;
        } finally{
            memberBroker.closeConnection();
        }
    }

    @Override
    public Member readMember(Long id) throws Exception {
        try{
            memberBroker.openConnection();
            return memberBroker.readMember(new Member(id));
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
    public Member updateMember(Long id, String firstname, String lastname, LocalDate birthday, String email) throws Exception {
        try{
            memberBroker.openConnection();
            memberBroker.openTransaction();
            Member member = memberBroker.updateMember(new Member(id, firstname, lastname, birthday, email));
            memberBroker.commitTransaction();
            return member;
        } catch(Exception ex){
            memberBroker.rollbackTransaction();
            throw ex;
        } finally{
            memberBroker.closeConnection();
        }
    }

    @Override
    public Member deleteMember(Long id) throws Exception {
        //ovu logiku treba obnoviti kasnije
        try{
            memberBroker.openConnection();
            memberBroker.openTransaction();
            Member member = memberBroker.deleteMember(new Member(id));
            memberBroker.commitTransaction();
            return member;
        } catch(Exception ex){
            memberBroker.rollbackTransaction();
            throw ex;
        } finally{
            memberBroker.closeConnection();
        }
    }
}
