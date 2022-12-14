package logics.interfaces;

import java.util.List;
import models.Member;

/**
 *
 * @author Djordjije
 */
public interface IMemberLogic {
    Member createMember(Member member) throws Exception;
    Member findMember(Member member) throws Exception;
    List<Member> findMembers(Member member) throws Exception;
    List<Member> readAllMembers() throws Exception;
    Member updateMember(Member member) throws Exception;
    Member deleteMember(Member member) throws Exception;
}
