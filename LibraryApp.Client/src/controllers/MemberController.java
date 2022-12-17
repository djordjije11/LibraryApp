/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.IEntity;
import models.Member;
import tcp.TcpClient;

/**
 *
 * @author Djordjije
 */
public class MemberController implements IController {
    private TcpClient tcpClient;
    
    public MemberController(TcpClient tcpClient){
        this.tcpClient = tcpClient;
    }
    
    @Override
    public void save() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void readAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void find(IEntity entity) {
        tcpClient.sendMessage("FIND");
        try {
            tcpClient.sendEntity(entity);
            String message = tcpClient.read();
            Member member = tcpClient.<Member>readEntity();
            System.out.println(member);
            //Member member = tcpClient.read();
            //salje serveru poruku sa zeljenom metodom i dobijenim objektom
            //prima poruku i objekat od servera
            //salje ekranskoj formi
        } catch (IOException ex) {
            Logger.getLogger(MemberController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MemberController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
