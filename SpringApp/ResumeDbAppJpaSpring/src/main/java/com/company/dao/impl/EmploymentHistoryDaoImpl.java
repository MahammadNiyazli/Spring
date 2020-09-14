package com.company.dao.impl;

import com.company.dao.AbstractDAO;
import com.company.dao.inter.EmploymentHistoryDaoInter;
import com.company.entity.EmploymentHistory;
import com.company.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmploymentHistoryDaoImpl extends AbstractDAO implements EmploymentHistoryDaoInter {


    public List<EmploymentHistory> getAllEmploymentHistoryByUserId(int userId) {
        List<EmploymentHistory> result = new ArrayList<EmploymentHistory>();
        try {
            Connection c = connect();
            PreparedStatement stmt = c.prepareStatement("select * from employment_history where user_id = ?");
            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                EmploymentHistory emp = getEmploymentHistory(rs);
                result.add(emp);
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    private EmploymentHistory getEmploymentHistory(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String header  = rs.getString("header");
        String jobDescription = rs.getString("job_description");
        Date beginDate = rs.getDate("begin_date");
        Date endDate  = rs.getDate("end_Date");
        int userId = rs.getInt("user_id");

        EmploymentHistory emp = new EmploymentHistory(id,header,beginDate,endDate,jobDescription,new User(userId));
        return emp;

    }
    
     @Override
    public boolean insertEmploymentHistory(EmploymentHistory emp) {
     try(Connection c = connect()){
           PreparedStatement stmt = c.prepareStatement("insert into employment_history(header,begin_date,end_date,job_description,user_id) values(?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
           stmt.setString(1, emp.getHeader());
           stmt.setDate(2,  new Date(emp.getBeginDate().getTime()));
           stmt.setDate(3,  new Date(emp.getEndDate().getTime()));
           stmt.setString(4, emp.getJobDescription());
           stmt.setInt(5, emp.getUser().getId());
           stmt.execute();
           
           ResultSet generatedKeys = stmt.getGeneratedKeys();
           if(generatedKeys.next()){
               emp.setId(generatedKeys.getInt(1));
           }
       }catch(Exception ex){
           ex.printStackTrace();
           return false;
       }
       return true;
    }

    @Override
    public boolean removeEmploymentHistory(int id) {
     try(Connection c = connect()){
           PreparedStatement stmt = c.prepareStatement("delete from employment_history where id = ?");
           stmt.setInt(1,id);
           stmt.execute();
       }catch(Exception ex){
           ex.printStackTrace();
           return false;
       }
       return true;
    }

    @Override
    public boolean updateEmploymentHistory(EmploymentHistory emp) {
        try(Connection c = connect()){
    PreparedStatement stmt = c.prepareStatement("update employment_history set header=?,begin_date=?,end_date=?,job_description=?,user_id=? where id=?");
           stmt.setString(1, emp.getHeader());
           stmt.setDate(2,  new Date(emp.getBeginDate().getTime()));
           stmt.setDate(3,  new Date(emp.getEndDate().getTime()));
           stmt.setString(4, emp.getJobDescription());
           stmt.setInt(5, emp.getUser().getId());
           stmt.setInt(6, emp.getId());
           stmt.execute();
       }catch(Exception ex){
           ex.printStackTrace();
           return false;
       }
       return true;
    }

}
