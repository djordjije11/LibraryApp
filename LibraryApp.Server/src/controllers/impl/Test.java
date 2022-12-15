/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.impl;

import database.configurations.ConfigFilePaths;
import database.configurations.IConfigurationManager;
import database.configurations.JsonFileConfigurationManager;
import database.sql.brokers.impl.SqlMemberBroker;
import database.sql.connectors.SqlConnector;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logics.impl.MemberLogic;
import models.Member;

/**
 *
 * @author Djordjije
 */
public class Test {
    
    //BELESKA: Mogu da uvedem interfejs IBroker sa metodama getMemberBroker(), getBookBroker(), initializeConnector()...
    
    public static void main(String[] args) throws Exception {
        try {
            IConfigurationManager configManager = new JsonFileConfigurationManager();
            configManager.initialize(ConfigFilePaths.SQL_JSON);
            SqlConnector.setConfigurationManager(configManager);
            
            
            
            
            //new MemberLogic(new SqlMemberBroker()).createMember("Miloš", "Klebečko", LocalDate.of(2001, Month.JANUARY, 10), "kasper@gmail.com");
            //new MemberLogic(new SqlMemberBroker()).updateMember(Long.valueOf(3), "Stefan", "Radović", LocalDate.of(1997, Month.SEPTEMBER, 25), "stefanlalov@gmail.com");
            //System.out.println(new MemberLogic(new SqlMemberBroker()).readMember(Long.valueOf(3)).getLastname());
            /*
            List<Member> members = new MemberLogic(new SqlMemberBroker()).readAllMembers();
            for (Member member : members) {
                System.out.println(member.getFirstname());
            }
            */
            //new AuthorLogic(new SqlAuthorBroker()).createAuthor("Charles", "Dickens");
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
