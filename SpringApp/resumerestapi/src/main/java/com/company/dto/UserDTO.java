package com.company.dto;

import com.company.entity.User;
import com.company.entity.UserSkill;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {
    private int id;
    private String name;
    private String surname;
    private String password;
    private List<UserSkillDTO> userSkills;

    public UserDTO() {
    }

    public UserDTO(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public UserDTO(User u) {
        this.id = u.getId();
        this.name = u.getName();
        this.surname = u.getSurname();

        List<UserSkill> list = u.getUserSkillList();
        List<UserSkillDTO> userSkillDTOS = new ArrayList<>();
        for (int i=0;i<list.size();i++){
           userSkillDTOS.add(new UserSkillDTO(list.get(i)));
        }
        userSkills = userSkillDTOS;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<UserSkillDTO> getUserSkills() {
        return userSkills;
    }

    public void setUserSkills(List<UserSkillDTO> userSkills) {
        this.userSkills = userSkills;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
