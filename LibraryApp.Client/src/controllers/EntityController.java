package controllers;

import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import message.Method;
import message.Request;
import message.Response;
import models.IEntity;
import session.Session;
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
    
    private void checkResponse(Response response) throws Exception{
        if(response.isConfirmed() == false)
            throw response.getException();
    }
    private Response sendRequestAndGetResponse(Request request) throws IOException, ClassNotFoundException {
        tcpClient.sendObject(request);
        return tcpClient.<Response>readObject();
    }
    public T createEntity(T entity) throws Exception{
        Request request = new Request(entity, entity.getModelElement(), Method.CREATE);
        Response response = sendRequestAndGetResponse(request);
        checkResponse(response);
        return (T)response.getObject();
    }
    public T updateEntity(T entity) throws Exception{
        Request request = new Request(entity, entity.getModelElement(), Method.UPDATE);
        Response response = sendRequestAndGetResponse(request);
        checkResponse(response);
        return (T)response.getObject();
    }
    public T deleteEntity(T entity) throws Exception{
        Request request = new Request(entity, entity.getModelElement(), Method.DELETE);
        Response response = sendRequestAndGetResponse(request);
        checkResponse(response);
        return (T)response.getObject();
    }
    public List<T> readAllEntities(T entity) throws Exception{
        Request request = new Request(entity, entity.getModelElement(), Method.READALL);
        Response response = sendRequestAndGetResponse(request);
        checkResponse(response);
        return (List<T>)response.getObject();
    }
    public List<T> findEntities(T entity) throws Exception{
        Request request = new Request(entity, entity.getModelElement(), Method.FINDWHERE);
        Response response = sendRequestAndGetResponse(request);
        checkResponse(response);
        return (List<T>)response.getObject();
    }
    public T getEntity(T entity) throws Exception{
        Request request = new Request(entity, entity.getModelElement(), Method.GET);
        Response response = sendRequestAndGetResponse(request);
        checkResponse(response);
        return (T)response.getObject();
    }
}
