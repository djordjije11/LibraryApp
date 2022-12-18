package database.sql.brokers.impl;

import database.sql.brokers.interfaces.IMemberBroker;
import database.sql.sqlmodels.SqlMember;
import helper.EntitiesConverter;
import java.sql.SQLException;
import java.util.List;
import models.Member;

/**
 *
 * @author Djordjije
 */
public class SqlMemberBroker extends SqlEntityBroker implements IMemberBroker {
    
    @Override
    public Member createMember(Member member) throws Exception {
        return (Member) create(new SqlMember(member));
    }
    @Override
    public Member findMember(Member member) throws Exception {
        return (Member)find(new SqlMember(member));
    }
    @Override
    public List<Member> findMembers(Member member) throws Exception {
        return EntitiesConverter.<Member>convertList(findEntities(new SqlMember(member)));
    }
    @Override
    public List<Member> readAllMembers(Member member) throws Exception {
        return EntitiesConverter.<Member>convertList(readAll(new SqlMember(member)));
    }
    @Override
    public Member updateMember(Member member) throws Exception {
        return (Member)update(new SqlMember(member));
    }
    @Override
    public Member deleteMember(Member member) throws Exception {
        return (Member)delete(new SqlMember(member));
    }
}
