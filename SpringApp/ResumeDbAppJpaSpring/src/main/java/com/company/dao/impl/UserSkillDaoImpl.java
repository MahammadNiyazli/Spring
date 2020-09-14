package com.company.dao.impl;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class UserSkillDaoImpl extends AbstractDAO implements UserSkillDaoInter {


    @Override
    public List<UserSkill> getAllSkillByUserId(int userId) {

        List<UserSkill> result = new ArrayList<UserSkill>();
        try {
            Connection c = connect();
            PreparedStatement stmt = c.prepareStatement(""
                    + "select us.id as userSkillId,u.*,us.skill_id,us.power,s.name as skill_name " +
                    "from user_skill us " +
                    "left join user u on us.user_id = u.id " +
                    "left join skill s on s.id = us.skill_id " +
                    "where us.user_id=?");
            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                UserSkill u = getUserSkill(rs);
                result.add(u);
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    private UserSkill getUserSkill(ResultSet rs) throws Exception {
        int userSkillId = rs.getInt("userSkillId");
        int userId = rs.getInt("id");
        int skillId = rs.getInt("skill_id");
        int power = rs.getInt("power");
        String skillName = rs.getString("skill_name");
        Skill skill = new Skill(skillId, skillName);
        return new UserSkill(userSkillId, new User(userId), skill, power);
    }

    @Override
    public boolean insertUserSkill(UserSkill userSkill) {
     try(Connection c = connect()){
           PreparedStatement stmt = c.prepareStatement("insert into user_skill(user_id,skill_id,power) values(?,?,?)",Statement.RETURN_GENERATED_KEYS);
           stmt.setInt(1, userSkill.getUser().getId());
           stmt.setInt(2, userSkill.getSkill().getId());
           stmt.setInt(3, userSkill.getPower());
           stmt.execute();
           
           ResultSet generatedKeys = stmt.getGeneratedKeys();
           if(generatedKeys.next()){
               userSkill.setId(generatedKeys.getInt(1));
           }
       }catch(Exception ex){
           ex.printStackTrace();
           return false;
       }
       return true;
    }

    @Override
    public boolean removeUserSkill(int id) {
     try(Connection c = connect()){
           PreparedStatement stmt = c.prepareStatement("delete from user_skill where id = ?");
           stmt.setInt(1,id);
           stmt.execute();
       }catch(Exception ex){
           ex.printStackTrace();
           return false;
       }
       return true;
    }

    @Override
    public boolean updateUserSkill(UserSkill userSkill) {
        try(Connection c = connect()){
    PreparedStatement stmt = c.prepareStatement("update user_skill set user_id = ?,skill_id = ?,power = ? where id = ?");
           stmt.setInt(1, userSkill.getUser().getId() );
           stmt.setInt(2, userSkill.getSkill().getId());
           stmt.setInt(3, userSkill.getPower());
           stmt.setInt(4, userSkill.getId());
           stmt.execute();
       }catch(Exception ex){
           ex.printStackTrace();
           return false;
       }
       return true;
    }

}
