package database.sql.brokers.interfaces;

import java.sql.Connection;
import java.util.List;
import models.Member;

/**
 *
 * @author Djordjije
 */
public interface IMemberBroker {
    Member createMember(Member member, Connection connection) throws Exception;
    Member findMember(Member member, Connection connection) throws Exception;
    List<Member> findMembers(Member member, Connection connection) throws Exception;
    List<Member> readAllMembers(Member member, Connection connection) throws Exception;
    Member updateMember(Member member, Connection connection) throws Exception;
    Member deleteMember(Member member, Connection connection) throws Exception;
    Long getCountOfAllLendingsByMember(Member member, Connection connection) throws Exception;
}
