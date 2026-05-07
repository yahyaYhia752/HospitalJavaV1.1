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


    //    Person{name='yahya', id=99, password='123456789'}
    public static Person StringToPerson(String line) {
        // getting all Properties
        String properties = line.substring(line.indexOf("{") + 1, line.indexOf("}"));
        // getting properties as 1D Array
        String[] parts = properties.split(", ");

        // setup properties one by one
        String name = parts[0].split("=")[1].replace("'", "");
        String strId = parts[1].split("=")[1];
        int id = Integer.parseInt(strId);
        String password = parts[2].split("=")[1].replace("'", "");
        return new Person(name, id, password);
    }
}
