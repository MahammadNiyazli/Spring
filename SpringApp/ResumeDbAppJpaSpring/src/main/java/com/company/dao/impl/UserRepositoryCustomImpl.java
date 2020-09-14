package com.company.dao.impl;


import at.favre.lib.crypto.bcrypt.BCrypt;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Date;
import java.sql.ResultSet;
import entity.User;
@Repository("userDao")
public class UserRepositoryCustomImpl extends AbstractDAO implements UserRepositoryCustom {


    @PersistenceContext
    EntityManager em;


    private User getUser(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String phone = rs.getString("phone");
        String email = rs.getString("email");
        String profileDesc = rs.getString("profile_description");
        String address = rs.getString("address");
        int nationalityId = rs.getInt("nationality_id");
        int birthplaceId = rs.getInt("birthplace_id");
        String nationalityStr = rs.getString("nationality");
        String birthplaceStr = rs.getString("birthplace");
        Date birthdate = rs.getDate("birthdate");
       

        Country nationality = new Country(nationalityId, null, nationalityStr);
        Country birthplace = new Country(birthplaceId, birthplaceStr, null);
        return new User(id, name, surname, phone, email,profileDesc,address,birthdate, nationality, birthplace);
    }

    private User getUserSimple(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String phone = rs.getString("phone");
        String email = rs.getString("email");
        String profileDesc = rs.getString("profile_description");
        String address = rs.getString("address");
        String password = rs.getString("password");
        Date birthdate = rs.getDate("birthdate");


        User u = new User(id, name, surname, phone, email,profileDesc,address,birthdate, null, null);
        u.setPassword(password);
        return u;
    }

    //JPQL
    
//    public User findByEmail(String email){
//        EntityManager em = em();
//        Query q = em.createQuery("select u from User u where u.email=:email", User.class);
//        q.setParameter("email", email);
//        
//        List<User> list = q.getResultList();
//        if(list.size()==1){
//            return list.get(0);
//        }
//        return null;
//    }
    
    //CriteriaBuilder
//    @Override
//        public User findByEmail(String email){
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery q1 = cb.createQuery(User.class);
//        Root postRoot = q1.from(User.class);
//        CriteriaQuery q2 = q1.where(cb.equal(postRoot.get("email"), email));
//        Query query = em.createQuery(q2);
//        
//        List<User> list = query.getResultList();
//        if(list.size()==1){
//            return list.get(0);
//        }
//        return null;
//    }
    
    
    //NamedQuery
//        @Override
//        public User findByEmail(String email){
//        Query query = em.createNamedQuery("User.findByEmail",User.class);
//        query.setParameter("email", email);
//        List<User> list = query.getResultList();
//        if(list.size()==1){
//            return list.get(0);
//        }
//        return null;
//    }

    
    //SQL
    
       @Override
        public User findByEmail(String email){
        Query query = em.createNativeQuery("select * from user where email = ?", User.class);
        query.setParameter(1, email);
        List<User> list = query.getResultList();
        if(list.size()==1){
            return list.get(0);
        }
        return null;
    }
    public List<User> getAll(String name,String surname,Integer nationalityId) {

           String jpql = "select u from User u where 1=1";

//
//            String sql = "select u.*,n.nationality , c.name as birthplace " +
//                    " from user u left join country n " +
//                    " on n.id=u.nationality_id left join country c on c.id=u.birthplace_id where 1=1 ";

            if(name!=null && !name.trim().isEmpty()){
                jpql += " and u.name=:name";
            }
            if(surname!=null && !surname.trim().isEmpty()){
                jpql += " and u.surname=:surname";
            }
            if(nationalityId!=null ){
                jpql += " and u.nationality.id=:nId"; 
            }
//            PreparedStatement stmt = c.prepareStatement(sql);

            Query q =em.createQuery(jpql, User.class);
            
            if(name!=null && !name.trim().isEmpty()){
                q.setParameter("name",name);
            }
            if(surname!=null && !surname.trim().isEmpty()){
                q.setParameter("surname",surname);
            }
            if(nationalityId!=null){
                q.setParameter("nId",nationalityId);
            }
          
            List<User> result  = q.getResultList();
            return result;
    }


    public boolean updateUser(User u) {
           em.merge(u);
           return true;
    }
   public static BCrypt.Hasher crypt = BCrypt.withDefaults();
    public boolean addUser(User u) {
         u.setPassword(crypt.hashToString(4, u.getPassword().toCharArray()));
           em.persist(u);
        return true;

    }



    public boolean removeUser(int id) {

         User u = em.find(User.class, id);
           em.remove(u);
        return true;
    }

    public User getById(int userId) {
        User u = em.find(User.class, userId);
        return u;
    }


}
