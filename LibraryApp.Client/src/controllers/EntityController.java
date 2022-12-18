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
    public T createEntity(T entity) throws Exception{
        tcpClient.sendMessage("CREATE/" + entity.getClassName());
        tcpClient.sendEntity(entity);
        String message = tcpClient.read();
        if(shouldWaitForEntity(message) == false){
            throw new Exception(message);
        }
        T dbEntity = tcpClient.<T>readEntity();
        return dbEntity;
    }
    public T updateEntity(T entity) throws Exception{
        tcpClient.sendMessage("UPDATE/" + entity.getClassName());
        tcpClient.sendEntity(entity);
        String message = tcpClient.read();
        if(shouldWaitForEntity(message) == false){
            throw new Exception(message);
        }
        T dbEntity = tcpClient.<T>readEntity();
        return dbEntity;
    }
    public T deleteEntity(T entity) throws Exception{
        tcpClient.sendMessage("DELETE/" + entity.getClassName());
        tcpClient.sendEntity(entity);
        String message = tcpClient.read();
        if(shouldWaitForEntity(message) == false){
            throw new Exception(message);
        }
        T dbEntity = tcpClient.<T>readEntity();
        return dbEntity;
    }
    public List<T> readAllEntities(T entity) throws Exception{
        tcpClient.sendMessage("READ ALL/" + entity.getClassName());
        tcpClient.sendEntity(entity);
        String message = tcpClient.read();
        if(shouldWaitForEntity(message) == false){
            throw new Exception(message);
        }
        List<T> dbEntity = tcpClient.<List<T>>readEntity();
        return dbEntity;
    }
    public List<T> findEntities(T entity) throws Exception{
        tcpClient.sendMessage("FIND/" + entity.getClassName());
        tcpClient.sendEntity(entity);
        String message = tcpClient.read();
        if(shouldWaitForEntity(message) == false){
            throw new Exception(message);
        }
        List<T> dbEntity = tcpClient.<List<T>>readEntity();
        return dbEntity;
    }
    public T getEntity(T entity) throws Exception{
        tcpClient.sendMessage("GET/" + entity.getClassName());
        tcpClient.sendEntity(entity);
        String message = tcpClient.read();
        if(shouldWaitForEntity(message) == false){
            throw new Exception(message);
        }
        T dbEntity = tcpClient.<T>readEntity();
        return dbEntity;
    }
    
    protected boolean shouldWaitForEntity(String message){
        //provera poruke
        return true;
    }
}
