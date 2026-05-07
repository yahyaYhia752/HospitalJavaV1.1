package org.example;


import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;


public class Main {
    private static String countingFile = "src/main/resources/CountAccounts.txt";
    static void main() {
//        Doctor[] allDoctors = {new Doctor(
//                "DcAhmed",
//                1009,
//                "0987654321",
//                1002,
//                "talking so much",
//                10400
//        ),new Doctor(
//                "xxxwww",
//                1019,
//                "0954321",
//                12,
//                "tidkfr",
//                1040
//        )};
//        try {
//            DataManage.setStaticsDataManage();
//            DataManage.setDataReaded(allDoctors[0].toString()+
//                    "\n"+
//                    allDoctors[1].toString()
//            );
//            DataManage.setFile();
//            IO.println(DataManage.getFile());
//        }
//        catch (Exception e){
//            IO.println("ERROR:"+e.getMessage());
//            e.printStackTrace();
//        }

        try{
            BufferedReader br = new BufferedReader(new FileReader(countingFile));
            IO.println();
        }
        catch (Exception e){
            IO.println(e.getMessage());
        }
    }
}
