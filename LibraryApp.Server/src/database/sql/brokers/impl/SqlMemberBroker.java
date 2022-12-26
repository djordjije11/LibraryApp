package database.sql.brokers.impl;

import database.sql.brokers.interfaces.IMemberBroker;
import database.sql.sqlmodels.SqlMember;
import helper.EntitiesConverter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import models.Member;

/**
 *
 * @author Djordjije
 */
public class SqlMemberBroker extends SqlEntityBroker implements IMemberBroker {
    
    @Override
    public Member createMember(Member member, Connection connection) throws Exception {
        return (Member) create(new SqlMember(member), connection);
    }
    @Override
    public Member findMember(Member member, Connection connection) throws Exception {
        return (Member)find(new SqlMember(member), connection);
    }
    @Override
    public List<Member> findMembers(Member member, Connection connection) throws Exception {
        return EntitiesConverter.<Member>convertList(findEntities(new SqlMember(member), connection));
    }
    @Override
    public List<Member> readAllMembers(Member member, Connection connection) throws Exception {
        return EntitiesConverter.<Member>convertList(readAll(new SqlMember(member), connection));
    }
    @Override
    public Member updateMember(Member member, Connection connection) throws Exception {
        return (Member)update(new SqlMember(member), connection);
    }
    @Override
    public Member deleteMember(Member member, Connection connection) throws Exception {
        return (Member)delete(new SqlMember(member), connection);
    }

    @Override
    public Long getCountOfAllLendingsByMember(Member member, Connection connection) throws Exception {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS amount FROM lending WHERE memberID = " + member.getId());
        resultSet.next();
        Long amount = resultSet.getLong("amount");
        resultSet.close();
        statement.close();
        return amount;
    }
}
