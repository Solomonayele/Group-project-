package edu.metrostate;

public interface Storable {
    public int getID();
    public void setID();
    public Boolean insert(Object o);
        //Object must be Connection
    public Boolean update();
    public Boolean delete();

}
