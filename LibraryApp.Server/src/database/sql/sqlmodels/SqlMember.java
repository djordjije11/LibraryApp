package database.sql.sqlmodels;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.IEntity;
import models.Member;

/**
 *
 * @author Djordjije
 */
public class SqlMember extends SqlEntity {
    private Member member;
    
    public SqlMember(Member member){
        super(member);
        this.member = member;
    }
    public SqlMember(){}
    
    @Override
    protected String getTableName() {
        return "member";
    }
    @Override
    public IEntity getEntity(){
        return member;
    }

    @Override
    public String getPreparedStatementInsertQuery() {
        return "INSERT INTO " + getTableName() + "(firstname, lastname, birthday, email) VALUES (?,?,?,?)";
    }

    @Override
    public void setUpPreparedStatementInsert(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, member.getFirstname());
        preparedStatement.setString(2, member.getLastname());
        preparedStatement.setDate(3, Date.valueOf(member.getBirthday()));
        preparedStatement.setString(4, member.getEmail());
    }

    @Override
    public String getPreparedStatementUpdateQuery() {
        return "UPDATE " + getTableName() + " SET firstname = ?, lastname = ?, birthday = ?, email = ? WHERE ID = ?";
    }

    @Override
    public void setUpPreparedStatementUpdate(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, member.getFirstname());
        preparedStatement.setString(2, member.getLastname());
        preparedStatement.setDate(3, Date.valueOf(member.getBirthday()));
        preparedStatement.setString(4, member.getEmail());
        preparedStatement.setLong(5, member.getId());
    }

    @Override
    public String getStatementSelectWithConditionQuery() {
        List<String> conditions = new ArrayList<>();
        if(member.getFirstname() != null && member.getFirstname().isBlank() == false){
            conditions.add("firstname LIKE '" + member.getFirstname() + "%'");
        }
        if(member.getLastname() != null && member.getLastname().isBlank() == false){
            conditions.add("lastname LIKE '" + member.getLastname() + "%'");
        }
        return constructSelectWithConditionsQuery(conditions);
    }

    @Override
    public IEntity getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return new Member(resultSet.getLong("ID"), resultSet.getString("firstname"), resultSet.getString("lastname"), resultSet.getDate("birthday").toLocalDate(), resultSet.getString("email"));
    }

    @Override
    public List<IEntity> getListOfEntities() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
