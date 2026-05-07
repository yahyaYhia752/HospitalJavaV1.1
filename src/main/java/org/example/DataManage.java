package org.example;

import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class DataManage  {
    private static String dataReaded;
    private static String path;
    private static FileWriter fw ;
    private static BufferedReader br;
    private static StringBuilder sb;
    public static void setStaticsDataManage() throws Exception{
        path = "src/main/resources/data.txt";
        fw = new FileWriter(path);
        br = new BufferedReader(new FileReader(path));
        sb = new StringBuilder();
    }

    public static String getDataReaded() {
        return dataReaded;
    }

    public static void setDataReaded(String dataReaded) {
        DataManage.dataReaded = dataReaded;
    }

    public static void setFile() throws Exception {
        fw.write(dataReaded);
    }

    // load file
    public static String getFile() throws Exception {
        String str;
        while ((str = br.readLine()) != null) {
            sb.append(str).append("\n");
        }
        return sb.toString();
    }
    //accounts
    public static void saveAccount(Person acc) throws Exception {
        dataReaded += acc.toString();
    }
}
