
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wjZhang
 */
public class ScanRefoExonSet {

    /**
     * @param args the command line arguments
     */


    public static class Lrs {

// exons of one Longreads
        public String LongReadsID;
        private Set<String> exonSet;

    }

    public static void UniqMrk(String Markfile) {
        try {
            HashMap<String, String> nMap = new HashMap<>();
            Scanner mark = new Scanner(new BufferedReader(new FileReader(Markfile)));
            //  ObjectOutputStream bwMark = new ObjectOutputStream(new FileOutputStream("src/UniqMarkDataset"));
            BufferedWriter bwMark = new BufferedWriter(new FileWriter("data/UniqExonDataset.fasta"));

            int i = 0;
            String s = null, id,li;

            while (mark.hasNextLine()) {
                li=mark.nextLine();
                s = li.split("\t")[1];
                id = li.split("\t")[0];
                if (nMap.get(s) == null) {
                    nMap.put(s, id);
                }
            }
            for (String x : nMap.keySet()) {
                bwMark.write(">" + nMap.get(x) + "\n" + x + "\n");
               // System.out.print(">" + nMap.get(x) + "\n" + x + "\n");
            }
            bwMark.flush();
            bwMark.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ScanRefoExonSet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ScanRefoExonSet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void ScanRef(String GffFile, String FastaFile) {
        try {
            SimpleDateFormat time = new SimpleDateFormat("mm:ss");
            String times = time.format(new java.util.Date());
            System.out.println(times);
            Scanner fasta = new Scanner(new BufferedReader(new FileReader(FastaFile)));

            BufferedWriter bwExonSet = new BufferedWriter(new FileWriter("data/exonDataset"));
            Scanner gff = new Scanner(new BufferedReader(new FileReader(GffFile)));
            Map m = new HashMap();
            String sm;
            while (fasta.hasNextLine()) {
  
                m.put(fasta.nextLine().split("\\|")[3], fasta.nextLine());
            }
            System.out.println("fasta scanned");
            fasta.close();

            int i = 0;
            while (gff.hasNextLine()) {

                String[] s = gff.nextLine().split("\t");
                if (s[0].toCharArray()[0] == '#') {
                    continue;
                }

                if ("exon".equals(s[2])) {

                    int start = Integer.parseInt(s[3]);
                    int end = Integer.parseInt(s[4]);

//                    String s1 = s[0] + "\t" + m.get(s[0]).toString().substring(start, start+20); ///chr
//                    String s2 = s[0] + "\t" + m.get(s[0]).toString().substring(end-20, end); 
                    bwExonSet.write((i++) + "\t" + m.get(s[0]).toString().substring(start, start + 20));
                    bwExonSet.newLine();
                    bwExonSet.write((i++) + "\t" + m.get(s[0]).toString().substring(end - 20, end));
                    bwExonSet.newLine();

                }

            }
            gff.close();
            bwExonSet.flush();
            bwExonSet.close();
            System.out.println("ooo");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ScanRefoExonSet.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
            Logger.getLogger(ScanRefoExonSet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
