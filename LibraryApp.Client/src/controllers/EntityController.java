/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.List;
import models.IEntity;
import tcp.TcpClient;

/**
 *
 * @author Djordjije
 */
public abstract class EntityController<T extends IEntity> {
    protected TcpClient tcpClient;
    
    public EntityController(TcpClient tcpClient){
        this.tcpClient = tcpClient;
    }
    
    public T save(T entity) throws Exception{
        return null;
    }
    public T delete(T entity) throws Exception{
        tcpClient.sendMessage("DELETE/" + entity.getClassName());
        tcpClient.sendEntity(entity);
        String message = tcpClient.read();
        if(shouldWaitForEntity(message) == false){
            throw new Exception(message);
        }
        T dbEntity = tcpClient.<T>readEntity();
        return dbEntity;
    }
    public List<T> readAll(T entity) throws Exception{
        tcpClient.sendMessage("READ ALL/" + entity.getClassName());
        tcpClient.sendEntity(entity);
        String message = tcpClient.read();
        if(shouldWaitForEntity(message) == false){
            throw new Exception(message);
        }
        List<T> dbEntity = tcpClient.<List<T>>readEntity();
        return dbEntity;
    }
    public List<T> find(T entity) throws Exception{
        tcpClient.sendMessage("FIND/" + entity.getClassName());
        tcpClient.sendEntity(entity);
        String message = tcpClient.read();
        if(shouldWaitForEntity(message) == false){
            throw new Exception(message);
        }
        List<T> dbEntity = tcpClient.<List<T>>readEntity();
        return dbEntity;
    }
    
    protected boolean shouldWaitForEntity(String message){
        //provera poruke
        return true;
    }
    
    
}
