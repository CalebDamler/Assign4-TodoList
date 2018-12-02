package edu.niu.cs.caleb.assign4;

/**
 * Created by Caleb on 5/2/2017.
 */
public class ToDo {
    private int id;
    private String name;


    public ToDo(int newId, String newName) {
        this.id = newId;
        this.name = newName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return id + " " + name;
    }
}
