package org.example;

// super class
public class Person {
    private String name;
    private int id;
    private String password;
    private static int count;

    public Person(String name,int id, String password){
        count += 1;
        this.password = password;
        this.name = name;
        this.id = id;
    }
    //getters
    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }
    public String getPassword() {
        return password;
    }

    //setters
    public void setName(String name) {
        this.name = name;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public static int getCount() {return count;}

    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", password='" + password + '\'' +
                '}';
    }
}
