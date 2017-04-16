
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
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

    public static class LaE {

        Set<String> ex;
        String IdString;
        Boolean flag;

        public LaE() {
            this.flag = true;
        }

    };

    public static void main(String[] args) throws IOException {
        String LongwithexonFile = "src/LongwithExon";

        Scanner FileScanner = new Scanner(new BufferedReader(new FileReader(LongwithexonFile)));

        Set<LaE> LLaEs = new HashSet<>();
        int i = 0;
        BufferedWriter bw = new BufferedWriter(new FileWriter("src/clust" + i));
        BufferedWriter bwm = new BufferedWriter(new FileWriter("src/mm"));
        String s;
        LaE l = new LaE();
        l.IdString = "";
        l.ex = new TreeSet<>();
        while (FileScanner.hasNextLine()) {
            s = FileScanner.nextLine();

            if (s.toCharArray()[0] == 'm') {
                if (!"".equals(l.IdString)) {
                    LLaEs.add(l);
                }
                l = null;
                l = new LaE();
                l.IdString = s;
                l.ex = new TreeSet<>();

            } else {
                l.ex.addAll(Arrays.asList(s.split("\t")));

            }

        }
        List<LaE> sssList = new ArrayList<>(LLaEs);
        for (LaE en : sssList) {
            bwm.newLine();
                bwm.write(en.IdString);
                bwm.newLine();
            for (String ss : en.ex) {
                bwm.write(ss+"\t");
                
            }
            
        }
        for (int j = 0; j < sssList.size(); j++) {
            for (int k = j + 1; k < sssList.size(); k++) {
                if (sssList.get(j).ex == sssList.get(k).ex) {
                    System.out.println("yes");
                    bw.write(sssList.get(k).IdString);
                    bw.newLine();
                    sssList.get(k).flag = false;//已检查过,禁止再次检查
                }
         
            }
       if (sssList.get(j).flag) {
                    bw.write(sssList.get(j).IdString);
                    sssList.get(j).flag = false;
                    bw.newLine();
                    bw.newLine();
                }
        }

    }

}
