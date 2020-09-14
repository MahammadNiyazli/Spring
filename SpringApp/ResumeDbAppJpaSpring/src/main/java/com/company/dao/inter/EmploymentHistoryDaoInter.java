package com.company.dao.inter;

import com.company.entity.EmploymentHistory;

import java.util.List;

public interface EmploymentHistoryDaoInter {

    public List<EmploymentHistory> getAllEmploymentHistoryByUserId(int userId);
    
    public boolean insertEmploymentHistory(EmploymentHistory emp);
    
    public boolean removeEmploymentHistory(int id);
     
    public boolean updateEmploymentHistory(EmploymentHistory emp);

}
