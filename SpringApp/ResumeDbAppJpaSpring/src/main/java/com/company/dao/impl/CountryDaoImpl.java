package com.company.dao.impl;

import com.company.dao.AbstractDAO;
import com.company.dao.inter.CountryDaoInter;
import com.company.entity.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CountryDaoImpl extends AbstractDAO implements CountryDaoInter {


    public List<Country> getAllCountry() {
        List<Country> result = new ArrayList<Country>();
        try {
            Connection c = connect();
            Statement stmt = c.createStatement();
            stmt.execute("select * from country");
            ResultSet rs = stmt.getResultSet();

            while(rs.next()){
              Country country = getCountry(rs);
              result.add(country);
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public Country getCountry(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String nationality = rs.getString("nationality");

        Country c = new Country(id,name,nationality);
        return c;
    }

    @Override
    public boolean addCountry(Country c) {
        try {
            Connection connection = connect();
            PreparedStatement stmt = connection.prepareStatement("insert into country(name,nationality) values(?,?)");
            stmt.setString(1, c.getName());
            stmt.setString(2, c.getNationality());
            return stmt.execute();
        } catch (Exception ex) {
            Logger.getLogger(CountryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } 
    }

    @Override
    public boolean updateCountry(Country c) {
             try {
            Connection connection = connect();
            PreparedStatement stmt = connection.prepareStatement("update country set name=?,nationality=? where id=?");
            stmt.setString(1, c.getName());
            stmt.setString(2, c.getNationality());
            stmt.setInt(3, c.getId());
            return stmt.execute();
        } catch (Exception ex) {
            Logger.getLogger(CountryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean removeCountry(int id) {
            try {
            Connection connection = connect();
            Statement stmt = connection.createStatement();
            return stmt.execute("delete from country where id = "+id);
        } catch (Exception ex) {
            Logger.getLogger(CountryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public Country getCountryById(int id) {
        try {
            Connection connection = connect();
            Statement stmt = connection.createStatement();
            stmt.execute("select * from country where id = "+id);
            ResultSet rs = stmt.getResultSet();
            
            while(rs.next()){
                Country c = getCountry(rs);
                return c;
            }
        } catch (Exception ex) {
            Logger.getLogger(CountryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
           
        } 
        return null;
    }


}
