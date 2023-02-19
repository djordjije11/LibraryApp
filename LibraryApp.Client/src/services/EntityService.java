package services;

import java.io.IOException;
import java.util.List;
import message.Method;
import message.ModelElement;
import message.Request;
import message.Response;
import models.IEntity;
import tcp.TcpClient;

/**
 *
 * @author Djordjije
 */
public abstract class EntityService<T extends IEntity> {
    protected TcpClient tcpClient;
    
    public EntityService(TcpClient tcpClient){
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
    private <ReturnType, ParameterType> ReturnType executeService(ParameterType object, ModelElement modelElement, Method method) throws Exception{
        Request request = new Request(object, modelElement, method);
        Response response = sendRequestAndGetResponse(request);
        checkResponse(response);
        return (ReturnType)response.getObject();
    }
    public T createEntity(T entity) throws Exception{
        return this.<T, T>executeService(entity, entity.getModelElement(), Method.CREATE);
    }
    public List<T> createEntities(List<T> entities) throws Exception{
        return this.<List<T>, List<T>>executeService(entities, entities.get(0).getModelElement(), Method.CREATELIST);
    }
    public T updateEntity(T entity) throws Exception{
        return this.<T, T>executeService(entity, entity.getModelElement(), Method.UPDATE);
    }
    public List<T> updateEntities(List<T> entities) throws Exception{
        return this.<List<T>, List<T>>executeService(entities, entities.get(0).getModelElement(), Method.UPDATELIST);
    }
    public T deleteEntity(T entity) throws Exception{
        return this.<T, T>executeService(entity, entity.getModelElement(), Method.DELETE);
    }
    public List<T> readAllEntities(T entity) throws Exception{
        return this.<List<T>, T>executeService(entity, entity.getModelElement(), Method.READALL);
    }
    public List<T> findEntities(T entity) throws Exception{
        return this.<List<T>, T>executeService(entity, entity.getModelElement(), Method.FINDWHERE);
    }
    public T getEntity(T entity) throws Exception{
        return this.<T, T>executeService(entity, entity.getModelElement(), Method.GET);
    }
}
