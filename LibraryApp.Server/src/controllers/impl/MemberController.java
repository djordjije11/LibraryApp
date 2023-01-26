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
            case CREATE:    //SAVE THE MEMBER
                dbObject = memberLogic.createMember(member);
                break;
            case READALL:   //GET ALL MEMBERS
                dbObject = memberLogic.readAllMembers();
                break;
            case FINDWHERE: //GET MEMBERS WITH SIMILAR FIRSTNAME AND LASTNAME AS THE SEARCHED TEXT
                dbObject = memberLogic.findMembers(member);
                break;
            case GET:   //GET THE MEMBER BY ID
                dbObject = memberLogic.findMember(member);
                break;
            case UPDATE:    //UPDATE THE MEMBER
                dbObject = memberLogic.updateMember(member);
                break;
            case DELETE:    //DELETE THE MEMBER BY ID
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
