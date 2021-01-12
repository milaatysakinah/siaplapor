package com.app.siaplapor.data.database;

import java.util.ArrayList;

public interface TableHandler<T> {
    public void create(T t);
    public T readById(String id);
    public ArrayList<T> readAll();
    public void update(T t);
    public  void delete(T t);
}
