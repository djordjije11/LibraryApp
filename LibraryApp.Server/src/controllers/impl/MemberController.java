package controllers.impl;

import controllers.interfaces.IController;
import database.sql.brokers.impl.SqlMemberBroker;
import jdk.jshell.spi.ExecutionControl;
import logics.impl.MemberLogic;
import logics.interfaces.IMemberLogic;
import message.Request;
import message.Response;
import models.Member;

/**
 *
 * @author Djordjije
 */
public class MemberController implements IController {
    private IMemberLogic memberLogic;
    
    public MemberController(){
        memberLogic = new MemberLogic(new SqlMemberBroker());
    }
    
    @Override
    public Response handle(Request request)  {
        Response response = new Response();
        Member member = (Member)request.getObject();
        Object dbObject = null;
        try{
            switch (request.getMethod()) {
            case CREATE:
                dbObject = memberLogic.createMember(member);
                break;
            case READALL:
                dbObject = memberLogic.readAllMembers();
                break;
            case FINDWHERE:
                dbObject = memberLogic.findMembers(member);
                break;
            case GET:
                dbObject = memberLogic.findMember(member);
                break;
            case UPDATE:
                dbObject = memberLogic.updateMember(member);
                break;
            case DELETE:
                dbObject = memberLogic.deleteMember(member);
                break;
            default:
                throw new ExecutionControl.NotImplementedException("The request method is not able for a Member object.");
            }
            response.setObject(dbObject);
            response.setConfirmed(true);
            return response;
        } catch(Exception ex){
            response.setConfirmed(false);
            response.setException(ex);
            return response;
        }
    }
}
