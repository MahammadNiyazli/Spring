package com.company.dao.impl;

import com.company.dao.impl.UserRepositoryCustom;
import com.company.entity.User;
import com.company.service.UserServiceInter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Integer>, UserRepositoryCustom {
    public User findByName(String name);
    public User findByNameAndSurname(String name,String surname);

    @Query(value = "select * from User where email=?",nativeQuery = true)
    public User findByEmail(String email);

    @Query(value = "select u from User u where u.email=:email")
    public User findByEmail2(String email);
}
