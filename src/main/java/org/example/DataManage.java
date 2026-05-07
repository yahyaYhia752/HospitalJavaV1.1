package org.example;

import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;

public class DataManage  {
    private static String dataReaded;
    private static String path;
    private static FileWriter fw;
    private static BufferedReader br;
    private static StringBuilder sb;
    public DataManage() throws Exception{
        path = "src/main/resources/data.txt";
        fw = new FileWriter(path);
        br = new BufferedReader(new FileReader(path));
        sb = new StringBuilder();
    }
    public static void update(){
        sb.append("\n").append(dataReaded);
        dataReaded = "";
    }
    public static void setData(String data) throws Exception {
        fw.write(data);
        fw.close();
    }

    // load file
    public static String getData() throws Exception {
        String split;
        while ((split = br.readLine()) != null) {
            sb.append(split).append("\n");
        }
        br.close();
        return sb.toString();
    }
    //accounts
    public static void saveAccount(Person acc) throws Exception {
        dataReaded += acc.toString();
    }
// load data

    public static void loadAccountsFromData() throws Exception {
        String[] split = getData().split("\n");
        for (int i=0; i<split.length;i++){

        }
    }




}
