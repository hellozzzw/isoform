
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Zwj
 */
public class ForLongreadswithExon {

    public static class Element_ex_L {

        private boolean flag;
        Set<String> ex;
        String IdString;

        public void prints() {
            for (String en : ex) {
                System.out.print(en + "\t");
            }
            System.out.print("\n");
        }

        public Element_ex_L() {
            this.flag = true;
        }
    }

    public static void Deal(String LongwithexonFile) throws IOException {
        // LongwithexonFile = "data/LongwithExon";
        Scanner FileScanner = new Scanner(new BufferedReader(new FileReader(LongwithexonFile)));
        
        ClustList(FileToSet(FileScanner));
        
        FileScanner.close();
        //       return 0;
//        return ClustList(s);

    }

    public static List FileToSet(Scanner file) {
        Element_ex_L E = new Element_ex_L();
        List List = new LinkedList();

        String s;
        while (file.hasNextLine()) {
            s = file.nextLine();
            if (s.toCharArray()[0] == 'm') {
                if (E.IdString != null) {
                    List.add(E);
                }

                E = new Element_ex_L();
                E.IdString = s;
                E.ex = new TreeSet<>();

            } else {
                E.ex.addAll(Arrays.asList(s.split("\t")));

            }

        }

        return List;

    }

    public static void  ClustList(List<Element_ex_L> l) throws IOException {
        SimpleDateFormat time = new SimpleDateFormat("mm:ss");
        String times = time.format(new java.util.Date());
        System.out.println(times);
 //       List RedunList = null;
        BufferedWriter bw = new BufferedWriter(new FileWriter("data/SetOfReads"));
//        System.out.println(l.size());
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).flag) {

                Element_ex_L tempL = l.get(i);
                for (int j = i + 1; j < l.size(); j++) {
//                      System.out.println(l.get(j).IdString);
                    if (tempL.ex.equals(l.get(j).ex) && l.get(j).flag) {
                        bw.write(l.get(j).IdString);
                        bw.newLine();
//                    System.out.println(l.get(j).IdString);
                        l.get(j).flag = false;
                    }
                }
                bw.write(l.get(i).IdString);
                bw.newLine();
                bw.newLine();
//            System.out.println(l.get(i).IdString);
//            System.out.println();
                l.get(i).flag = false;
            }
        }
        bw.flush();
        bw.close();
        System.out.println(times);
        //return RedunList;
    }
}
