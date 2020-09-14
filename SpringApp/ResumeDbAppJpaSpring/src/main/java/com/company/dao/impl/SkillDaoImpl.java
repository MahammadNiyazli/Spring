package com.company.dao.impl;

import com.company.dao.AbstractDAO;
import com.company.dao.inter.SkillDaoInter;
import com.company.entity.Skill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SkillDaoImpl extends AbstractDAO implements SkillDaoInter {


    public List<Skill> getAllSkill() {
        List<Skill> result = new ArrayList<Skill>();
        try {
            Connection c = connect();
            Statement stmt = c.createStatement();
            stmt.execute("select * from skill");
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                Skill skill = getSkill(rs);
                result.add(skill);
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public Skill getSkill(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");

        Skill s = new Skill(id, name);
        return s;
    }

    @Override
    public boolean insertSkill(Skill skill) {
       try(Connection c = connect()){
           PreparedStatement stmt = c.prepareStatement("insert into skill(name) values(?)",Statement.RETURN_GENERATED_KEYS);
           stmt.setString(1, skill.getName());
           stmt.execute();
           
           ResultSet generatedKeys = stmt.getGeneratedKeys();
           if(generatedKeys.next()){
               skill.setId(generatedKeys.getInt(1));
           }
       }catch(Exception ex){
           ex.printStackTrace();
           return false;
       }
       return true;
    }

    @Override
    public boolean removeSkill(int id) {
      try(Connection c = connect()){
           PreparedStatement stmt = c.prepareStatement("delete from skill where id = ?");
           stmt.setInt(1,id);
           stmt.execute();
       }catch(Exception ex){
           ex.printStackTrace();
           return false;
       }
       return true;
    }

    @Override
    public boolean updateSkill(Skill skill) {
     try(Connection c = connect()){
           PreparedStatement stmt = c.prepareStatement("update skill set name = ? where id = ?");
           stmt.setString(1, skill.getName());
           stmt.setInt(2, skill.getId());
           stmt.execute();
       }catch(Exception ex){
           ex.printStackTrace();
           return false;
       }
       return true;
    }
}

