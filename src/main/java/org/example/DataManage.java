package org.example;

import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;

public class DataManage  {
    private static String dataReaded;
    private static String path = "src/main/resources/data.txt";
    private static String countingFile = "src/main/resources/CountAccounts.txt";
    private static int countPerson;
    private static int countPaitent;
    private static int countDoctor;

    public static int getCountPersonFromFile() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(countingFile));
        String personCount = br.readAllAsString().substring(br.readAllAsString().indexOf("Person:") + 1, br.readAllAsString().indexOf("\n"));
        return Integer.parseInt(personCount);
    }
    public static void SaveCountPersonFromFile() throws Exception{
        FileWriter fw = new FileWriter(countingFile);
        BufferedReader br = new BufferedReader(new FileReader(countingFile));
        String line =br.readAllLines().get(0).split(":")[0];
        fw.write(line+":"+countPerson);
        fw.close();
    }
    public static int getCountPaitentFromFile() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(countingFile));
        String personCount = br.readAllAsString().substring(br.readAllAsString().indexOf("Paitent:") + 1, br.readAllAsString().indexOf("\n"));
        return Integer.parseInt(personCount);
    }
    public static void SaveCountPaitentFromFile() throws Exception{
        FileWriter fw = new FileWriter(countingFile);
        BufferedReader br = new BufferedReader(new FileReader(countingFile));
        String line =br.readAllLines().get(1).split(":")[0];
        fw.write(line+":"+countPaitent);
        fw.close();
    }
    public static int getCountDoctorFromFile() throws Exception{
        BufferedReader br = new BufferedReader(new FileReader(countingFile));
        String personCount = br.readAllAsString().substring(br.readAllAsString().indexOf("Doctor:") + 1, br.readAllAsString().indexOf("\n"));
        return Integer.parseInt(personCount);
    }
    public static void SaveCountDoctorFromFile() throws Exception{
        FileWriter fw = new FileWriter(countingFile);
        BufferedReader br = new BufferedReader(new FileReader(countingFile));
        String line =br.readAllLines().get(2).split(":")[0];
        fw.write(line+":"+countDoctor);
        fw.close();
    }

    public static String getDataReaded() {
        return dataReaded;
    }

    public static void setDataReaded(String dataReaded) {
        DataManage.dataReaded = dataReaded;
    }

    public static void setFile() throws Exception {
        FileWriter fw = new FileWriter(path); ;
        fw.write(dataReaded);
        fw.close();
    }

    // load file
    public static String getFile() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(path));
        StringBuilder sb = new StringBuilder();
        String str;
        while ((str = br.readLine()) != null) {
            sb.append(str).append("\n");
        }
        br.close();
        return sb.toString();
    }
    public static String findAccount(String nameAcc) throws Exception{
        String[] allAccounts = getFile().split("\n");
        for (int i=0;i<allAccounts.length;i++){
            String Pname = allAccounts[i].substring(allAccounts[i].indexOf("name='") + 1, allAccounts[i].indexOf("'"));
            if (Pname.equals(nameAcc)){
                return allAccounts[i];
            }
        }
        return "";
    }
    public static String checkPassword(String nameAcc,String password) throws Exception{
        String[] allAccounts = getFile().split("\n");
        for (int i=0;i<allAccounts.length;i++){
            String pName = allAccounts[i].substring(allAccounts[i].indexOf("name='") + 1, allAccounts[i].indexOf("'"));
            String pPassword = allAccounts[i].substring(allAccounts[i].indexOf("password='") + 1, allAccounts[i].indexOf("'"));
            if (pName.equals(nameAcc)||pPassword.equals(password)){
                return allAccounts[i];
            }
        }
        return "";
    }
}
