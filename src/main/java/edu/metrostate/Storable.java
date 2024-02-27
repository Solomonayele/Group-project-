package edu.metrostate;

public interface Storable {
    void getID();
    void setID();
    void insert(Object o);
        //Object must be Connection
    void update();
    void delete(Object o);

}
