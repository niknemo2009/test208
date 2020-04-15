package com.parking;

import java.sql.ResultSet;

public interface DAO<T> {
    public T create(T obj);
    public Car[] get();
    public Car get(int id);
    public  boolean update(T obj, String newValue);
    public boolean delete(int id);
}
