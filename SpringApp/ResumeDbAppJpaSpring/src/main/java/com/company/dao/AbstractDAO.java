package com.company.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractDAO {
    public Connection connect() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");   
        String url = "jdbc:mysql://localhost:3306/resume";
        String userName = "root";
        String password = "0503771480m";
        Connection c = DriverManager.getConnection(url, userName, password);
        return c;
    }
    
   private static EntityManagerFactory emf = null;
    
    public EntityManager em(){
        if(emf==null){
        emf = Persistence.createEntityManagerFactory("resumeappPU");
        }
        EntityManager em = emf.createEntityManager();
        return em;
    }
              
    public void closeEmf(EntityManager em){
             emf.close();
          }
}
