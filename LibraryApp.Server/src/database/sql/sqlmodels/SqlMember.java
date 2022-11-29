/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database.sql.sqlmodels;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    String getTableName() {
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
    public String getPreparedStatementDeleteQuery() {
        return "DELETE FROM " + getTableName() + " WHERE ID = ?";
    }

    @Override
    public void setUpPreparedStatementDelete(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setLong(1, member.getId());
    }

    @Override
    public String getStatementSelectQuery() {
        return "SELECT * FROM " + getTableName() + " WHERE ID = " + member.getId();
    }

    @Override
    public String getStatementSelectAllQuery() {
        return "SELECT * FROM " + getTableName();
    }

    @Override
    public IEntity getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return new Member(resultSet.getLong("ID"), resultSet.getString("firstname"), resultSet.getString("lastname"), resultSet.getDate("birthday").toLocalDate(), resultSet.getString("email"));
    }
}
