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
import static java.lang.Thread.sleep;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logics.impl.MemberLogic;
import models.Member;
import tcp.TcpCommunicator;

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
            
            ServerSocket socket;
            Socket socketCommunication;
            socket = new ServerSocket(9001);
            socketCommunication = socket.accept();
            TcpCommunicator tcpCommunicator = new TcpCommunicator(socketCommunication);
            String message = tcpCommunicator.read();
            System.out.println(message);
            List<Member> members = tcpCommunicator.<List<Member>>readEntity();
            for (Member member : members) {
                System.out.println(member.getFirstname() + " " + member.getLastname());
            }
            //HOCE!!! DAKLE SUPER RADI I SA LISTAMA
            
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
