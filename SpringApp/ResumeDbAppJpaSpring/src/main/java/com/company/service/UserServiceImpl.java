package com.company.service;


import com.company.dao.impl.UserRepositoryCustom;
import com.company.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserServiceInter {


    @Autowired
    @Qualifier("userDao")
    UserRepositoryCustom userDao;

    
       @Override
        public User findByEmail(String email){
        return userDao.findByEmail(email);
    }
    
    public List<User> getAll(String name,String surname,Integer nationalityId) {
        try {
            return userDao.getAll(name,surname,nationalityId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public boolean updateUser(User u) {
          return userDao.updateUser(u);
    }

    public boolean addUser(User u) {
         return userDao.addUser(u);

    }




    public boolean removeUser(int id) {

         return userDao.removeUser(id);
    }

    public User getById(int userId) {
        return userDao.getById(userId);
    }


}
