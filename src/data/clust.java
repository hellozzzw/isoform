/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Scanner;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
//

/**
 *
 * @author wjZhang
 */
public class clust {

    public static Set<lgrExon> ExonSetOfLongreads = new HashSet<>();
    public static Set<Exonlgr> lgrOfExn = new HashSet<>();

    public static void main(String[] args) throws IOException {

        Set<lgrExon> s1 = clust("src/align.result.sort");

    }

    public static void prints(Set<Exonlgr> sss) throws IOException {
        int m = 0;
        BufferedWriter bwx;
        List<Exonlgr> sssList = new ArrayList<>(sss);
        for (Exonlgr sssList1 : sssList) {

            bwx = new BufferedWriter(new FileWriter("src/set" + m));
            for (String s : sssList1.ReadsSet) {
                bwx.write(s);
                bwx.newLine();

            }
            m++;

        }
    }

    public static Set<lgrExon> clust(String align) throws IOException {
        try {
            Scanner algS = new Scanner(new BufferedReader(new FileReader(align)));
            String sf = "", s0, s2;
            lgrExon temp = new lgrExon();
            temp.ID = sf;
            temp.exonSet = new HashSet<>();
            Set<lgrExon> ExonSetOfLongreads = new HashSet<>();
            BufferedWriter bwe = new BufferedWriter(new FileWriter("src/LongwithExon"));

            Set<String> tempexon = new HashSet<>();
            while (algS.hasNextLine()) {
                s2 = algS.nextLine().split("\t")[2];
                s0 = algS.nextLine().split("\t")[0];
                //            System.out.println("tempID:"+temp.ID);
                //           System.out.println("TempSize:"+temp.exonSet.size());

                if (!sf.equals(s2)) {
                    temp.ID = sf;
                    temp.exonSet =tempexon;

//System.out.println(temp.exonSet.size());
                    ExonSetOfLongreads.add(temp);
                    bwe.write(temp.ID);
                    bwe.newLine();
                    for (String  xxx : temp.exonSet) {
                         bwe.write(xxx+"\t");
                    }
                    bwe.newLine();
                    
                   
                    
                    temp.exonSet.clear();
                    tempexon.clear();
                    temp=new  lgrExon();
                    temp.ID = s2;
                    sf = s2;

                }
                tempexon.add(s0);

            }
            
            
            
bwe.close();
//            for (lgrExon xxx : ExonSetOfLongreads) {
//                System.out.println(xxx.ID);
//                System.out.println(xxx.exonSet.size());
//                
//            }
            return ExonSetOfLongreads;
        } catch (FileNotFoundException e) {
        }
 return ExonSetOfLongreads;
        //  return ExonSetOfLongreads;
    }

//    public static Set<Exonlgr> fExonListOfLongreads(Set<lgrExon> ExonSetOfLongreads) {
//        try {
//            List<lgrExon> ExonListOfLongreads = new ArrayList<>(ExonSetOfLongreads);
//            for (lgrExon xxx : ExonSetOfLongreads) {
//                System.out.println(xxx.ID);
//                System.out.println(xxx.exonSet.size());
//                
//            }
//            
//            
//            Set<String> ReadsSet = new HashSet<>(), tempexonSet;
//            Exonlgr tempc = new Exonlgr();
//            //       List<Exonlgr>
//            for (int i = 0; i < ExonListOfLongreads.size(); i++) {
//                tempexonSet = ExonListOfLongreads.get(i).exonSet;
//               
//                for (int j = 0; j < ExonListOfLongreads.size(); j++) {
//                    if (tempexonSet == ExonListOfLongreads.get(j).exonSet) {
//                        ReadsSet.add(ExonListOfLongreads.get(j).ID);
//                    }
//
//                }
//                ReadsSet.add(ExonListOfLongreads.get(i).ID);
//
//                tempc.exonSet = ExonListOfLongreads.get(i).exonSet;
//                tempc.ReadsSet = ReadsSet;
//                
//
//                lgrOfExn.add(tempc);
//           //     System.out.println(lgrOfExn.size());
////
//                tempc.ReadsSet.clear();
//                tempc.exonSet.clear();
//                ReadsSet.clear();
//
//            }
//return lgrOfExn;
//        } catch (Exception e) {
//
//        }
//
//        return lgrOfExn;
//    }

    public static class lgrExon {

        String ID;
        Set<String> exonSet;

    }

    public static class Exonlgr {

        Set<String> exonSet;
        Set<String> ReadsSet;

    }

    public static class isoset {

        Set<String> readsSet;
        List<String> exonSet;
    }
}
