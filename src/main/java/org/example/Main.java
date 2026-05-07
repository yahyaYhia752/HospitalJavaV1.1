package org.example;

import javax.print.Doc;
import javax.swing.*;
import javax.xml.crypto.Data;
import  java.util.ArrayList;
import java.util.Objects;

public class Main {
    static void main() {
        Doctor[] allDoctors = {new Doctor(
                "DcAhmed",
                1009,
                "0987654321",
                1002,
                "talking so much",
                10400
        ),new Doctor(
                "xxxwww",
                1019,
                "0954321",
                12,
                "tidkfr",
                1040
        )};
        try {
            DataManage.setStaticsDataManage();
            DataManage.setDataReaded(allDoctors[0].toString());
            DataManage.setDataReaded("\n");
            DataManage.setDataReaded(allDoctors[1].toString());
            DataManage.setFile();
        }
        catch (Exception e){
            IO.println("ERROR:"+e.getMessage());
            e.printStackTrace();
        }
    }
}
