package com.company.dao.impl;

import com.company.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserRepositoryCustom {

    public List<User> getAll(String name,String surname,Integer nationalityId) throws SQLException, Exception;

    public boolean updateUser(User u);

    public boolean addUser(User u);

    public boolean removeUser(int id);

    public User getById(int id);

    public User findByEmail(String email);

}
