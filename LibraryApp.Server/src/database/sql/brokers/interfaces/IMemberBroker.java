/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package database.sql.brokers.interfaces;

import java.util.List;
import models.Member;

/**
 *
 * @author Djordjije
 */
public interface IMemberBroker extends IEntityBroker {
    Member createMember(Member member) throws Exception;
    Member findMember(Member member) throws Exception;
    List<Member> findMembers(Member member) throws Exception;
    List<Member> readAllMembers(Member member) throws Exception;
    Member updateMember(Member member) throws Exception;
    Member deleteMember(Member member) throws Exception;
}
