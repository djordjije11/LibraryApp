package database.sql.sqlmodels;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Member;

/**
 *
 * @author Djordjije
 */
public class SqlMember extends SqlEntity<Member> {
    public SqlMember(Member member){
        super(member);
    }
    public SqlMember(){}
    
    @Override
    protected String getTableName() {
        return "member";
    }

    @Override
    public String getPreparedStatementInsertQuery() {
        return "INSERT INTO " + getTableName() + "(firstname, lastname, birthday, email) VALUES (?,?,?,?)";
    }

    @Override
    public void setUpPreparedStatementInsert(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, entity.getFirstname());
        preparedStatement.setString(2, entity.getLastname());
        preparedStatement.setDate(3, Date.valueOf(entity.getBirthday()));
        preparedStatement.setString(4, entity.getEmail());
    }

    @Override
    public String getPreparedStatementUpdateQuery() {
        return "UPDATE " + getTableName() + " SET firstname = ?, lastname = ?, birthday = ?, email = ? WHERE ID = ?";
    }

    @Override
    public void setUpPreparedStatementUpdate(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, entity.getFirstname());
        preparedStatement.setString(2, entity.getLastname());
        preparedStatement.setDate(3, Date.valueOf(entity.getBirthday()));
        preparedStatement.setString(4, entity.getEmail());
        preparedStatement.setLong(5, entity.getId());
    }

    @Override
    public String getStatementSelectWithConditionQuery() {
        List<String> conditions = new ArrayList<>();
        if(entity.getFirstname() != null && entity.getFirstname().isBlank() == false){
            conditions.add("firstname LIKE '" + entity.getFirstname() + "%'");
        }
        if(entity.getLastname() != null && entity.getLastname().isBlank() == false){
            conditions.add("lastname LIKE '" + entity.getLastname() + "%'");
        }
        return constructSelectWithConditionsQuery(conditions);
    }

    @Override
    public Member getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return new Member(resultSet.getLong("ID"), resultSet.getString("firstname"), resultSet.getString("lastname"), resultSet.getDate("birthday").toLocalDate(), resultSet.getString("email"));
    }
}
