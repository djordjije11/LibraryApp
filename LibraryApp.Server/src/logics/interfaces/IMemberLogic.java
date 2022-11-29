/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logics.interfaces;

import java.time.LocalDate;
import java.util.List;
import models.Member;

/**
 *
 * @author Djordjije
 */
public interface IMemberLogic {
    Member createMember(String firstname, String lastname, LocalDate birthday, String email) throws Exception;
    Member readMember(Long id) throws Exception;
    List<Member> readAllMembers() throws Exception;
    Member updateMember(Long id, String firstname, String lastname, LocalDate birthday, String email) throws Exception;
    Member deleteMember(Long id) throws Exception;
}
