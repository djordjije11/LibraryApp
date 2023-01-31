package logics.impl;

import java.util.List;
import logics.interfaces.IMemberLogic;
import models.Member;
import logics.operations.member.CreateMember;
import logics.operations.member.DeleteMember;
import logics.operations.member.FindMember;
import logics.operations.member.FindMembers;
import logics.operations.member.ReadAllMembers;
import logics.operations.member.UpdateMember;

/**
 *
 * @author Djordjije
 */
public class MemberLogic implements IMemberLogic {
    @Override
    public Member createMember(Member member) throws Exception {
        CreateMember operation = new CreateMember();
        operation.setMember(member);
        return operation.execute();
    }
    @Override
    public Member findMember(Member member) throws Exception {
        FindMember operation = new FindMember();
        operation.setMember(member);
        return operation.execute();
    }
    @Override
    public List<Member> findMembers(Member member) throws Exception {
        FindMembers operation = new FindMembers();
        operation.setMember(member);
        return operation.execute();
    }
    @Override
    public List<Member> readAllMembers() throws Exception {
        return new ReadAllMembers().execute();
    }
    @Override
    public Member updateMember(Member member) throws Exception {
        UpdateMember operation = new UpdateMember();
        operation.setMember(member);
        return operation.execute();
    }
    @Override
    public Member deleteMember(Member member) throws Exception {
        DeleteMember operation = new DeleteMember();
        operation.setMember(member);
        return operation.execute();
    }
}