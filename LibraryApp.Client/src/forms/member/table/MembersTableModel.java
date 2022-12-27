/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms.member.table;

import java.time.LocalDate;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import models.Member;

/**
 *
 * @author Djordjije
 */
public class MembersTableModel extends AbstractTableModel{
    private List<Member> members;
    private final String[] columnNames = {"ID","Ime", "Prezime", "Datum rodjenja", "E-mail"};
    private final Class[] columnTypes = {Long.class, String.class, String.class, LocalDate.class, String.class};
    
    public MembersTableModel(List<Member> members){
        this.members = members;
    }
    public Member getMember(int index){
        if(index < 0)
            return null;
        return members.get(index);
    }
    @Override
    public int getRowCount() {
        if(members == null)
            return 0;
        return members.size();
    }
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Member member = members.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return member.getId();
            case 1:
                return member.getFirstname();
            case 2:
                return member.getLastname();
            case 3:
                return member.getBirthday();
            case 4:
                return member.getEmail();
            default:
                return "n/a";
        }
    }
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnTypes[columnIndex];
    }
}