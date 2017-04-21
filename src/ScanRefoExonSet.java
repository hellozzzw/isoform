
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

    public static void RnaFromGenome(String fnafile) {
        String head, seq, headinfoArray;
        int n, flagPoint, count, exonN;
        try {
            Scanner fna = new Scanner(new BufferedReader(new FileReader(fnafile)));
            BufferedWriter bwExonSet = new BufferedWriter(new FileWriter("data/JuncDataset"));
//            BufferedWriter bwExonfastaSet = new BufferedWriter(new FileWriter("data/JuncDataset.fasta"));
            BufferedWriter bwUniqSet = new BufferedWriter(new FileWriter("data/UniqExonDataset"));
            while (fna.hasNextLine()) {
                head = fna.nextLine();
                n = head.split(" ").length;
                headinfoArray = head.split(" ")[n - 1].replaceAll(",", "\t").replaceAll("\\..", "\t").replaceAll("[a-z A-Z]|<|>|\\(|\\)|\\[|\\]|=", "");
                exonN = headinfoArray.split("\t").length;
                seq = fna.nextLine();
                if (exonN == 2) {
                    bwUniqSet.write(head + "\n" + seq + "\n");
                } else {
                    count = 0;
                    flagPoint = 0;
                    for (int i = 0; i < exonN - 2; i++) {
                        flagPoint += (Integer.parseInt(headinfoArray.split("\t")[++i]) - Integer.parseInt(headinfoArray.split("\t")[i - 1]));
                        flagPoint = (flagPoint - 10 < 0) ? 10 : flagPoint;
                        flagPoint = (flagPoint + 10 > seq.length() - 10) ? seq.length() - 10 : flagPoint;
                        bwExonSet.write(head.split(" ")[1] + "." + (count++) + "\t" + seq.substring(flagPoint - 10, flagPoint + 10) + "\n");
//                        bwExonfastaSet.write(">"+head.split(" ")[1] + "." + (count++) + "\n" + seq.substring(flagPoint - 10, flagPoint + 10) + "\n");
//                        bwExonSet.write(head.split(" ")[1] + "." + count++ + "\t" + Integer.parseInt(headinfoArray.split("\t")[i - 1]) + "\t" + (Integer.parseInt(headinfoArray.split("\t")[i])) + "\t" + seq.substring(flagPoint - 10, flagPoint + 10) + "\n");
//                        System.out.print(head.split(" ")[1] + "." + count++ + "\t" + Integer.parseInt(headinfoArray.split("\t")[i - 1])+"\t"+(Integer.parseInt(headinfoArray.split("\t")[i]))+"\t"+ seq.subSequence(flagPoint - 10, flagPoint + 10) + "\n");
                    }
                }
            }
            bwExonSet.flush();
            bwExonSet.close();
            bwUniqSet.flush();
            bwUniqSet.close();
            UniqMrk("data/JuncDataset","data/JuncDataset.fasta");
//            bwExonfastaSet.flush();
//            bwExonfastaSet.close();
                    
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ScanRefoExonSet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ScanRefoExonSet.class.getName()).log(Level.SEVERE, null, ex);
        } 

    }

    
      public static void UniqMrk(String Markfile,String outString) {
        try {
            HashMap<String, String> nMap = new HashMap<>();
            Scanner mark = new Scanner(new BufferedReader(new FileReader(Markfile)));
            //  ObjectOutputStream bwMark = new ObjectOutputStream(new FileOutputStream("src/UniqMarkDataset"));
            BufferedWriter bwMark = new BufferedWriter(new FileWriter(outString));

            int i = 0;
            String s = null, id, li;

            while (mark.hasNextLine()) {
                li = mark.nextLine();
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
    public static void UniqMrk(String Markfile) {
            UniqMrk(Markfile,"data/UniqExonDataset.fasta");
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
                    bwExonSet.write((i++) + "\t" + m.get(s[0]).toString().substring(start, start + 20) + "\n");
                    bwExonSet.write((i++) + "\t" + m.get(s[0]).toString().substring(end - 20, end) + "\n");

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
