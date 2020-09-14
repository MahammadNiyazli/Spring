package com.company.dao.inter;

import com.company.entity.Country;

import java.util.List;

public interface CountryDaoInter {

    public List<Country> getAllCountry();
    public boolean addCountry(Country c);
    public boolean updateCountry(Country c);
    public boolean removeCountry(int id);
    public Country getCountryById(int id);
}
