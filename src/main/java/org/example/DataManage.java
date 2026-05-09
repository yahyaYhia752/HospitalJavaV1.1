package org.example;

import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;

public class DataManage  {
    private static String dataReaded;
    private static String path = "src/main/resources/data.txt";

    public static String getDataReaded() {
        return dataReaded;
    }

    public static void setDataReaded(String dataReaded) {
        DataManage.dataReaded = dataReaded;
    }

    public static void setFile() throws Exception {
        FileWriter fw = new FileWriter(path);
        fw.write(dataReaded);
        fw.close();
    }
    public static void addFile() throws Exception{
        FileWriter fw = new FileWriter(path);
        fw.append("\n"+dataReaded);
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
            String properties = allAccounts[i].substring(allAccounts[i].indexOf("{") + 1, allAccounts[i].indexOf("}"));
            int start = properties.indexOf("name='")+6;
            String pName = properties.substring(start,properties.indexOf("'",start));
            if (pName.equals(nameAcc)){
                return allAccounts[i];
            }
        }
        return "";
    }
    public static String checkPassword(String nameAcc,String password) throws Exception{
        String[] allAccounts = getFile().split("\n");
        for (int i=0;i<allAccounts.length;i++){
            int startIndexPersonName,startIndexPersonPassword;
            startIndexPersonName = allAccounts[i].indexOf("name='") + 6;
            startIndexPersonPassword = allAccounts[i].indexOf("password='") + 10;
            String pName = allAccounts[i].substring(startIndexPersonName, allAccounts[i].indexOf("'",startIndexPersonName));
            String pPassword = allAccounts[i].substring(startIndexPersonPassword, allAccounts[i].indexOf("'",startIndexPersonPassword));
            if (pName.equals(nameAcc)||pPassword.equals(password)){
                return allAccounts[i];
            }
        }
        return "";
    }
}
