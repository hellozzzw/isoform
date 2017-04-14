
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

/**
 *
 * @author wjZhang
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        
        
       // ***pre-deal-with reference data (gff & fasta)
        ScanRef("src/major surface glycoprotein.gff3", "src/major surface glycoprotein.fasta");
       
       UniqMrk("/src/MarkDataset");
       
       
       
    }

    public static void ScanRef(String GffFile, String FastaFile) {
        try {
            SimpleDateFormat time = new SimpleDateFormat("mm:ss");
            String times = time.format(new java.util.Date());
            System.out.println(times);
            Scanner fasta = new Scanner(new BufferedReader(new FileReader(FastaFile)));

            BufferedWriter bwM=new BufferedWriter(new FileWriter("src/MarkDataset"));

            Map m = new HashMap();
            while (fasta.hasNextLine()) {
                m.put(fasta.nextLine().split("\\|")[3], fasta.nextLine());
            }
            System.out.println("fasta scanned");

            Scanner gff = new Scanner(new BufferedReader(new FileReader(GffFile)));
            while (gff.hasNextLine()) {
                String[] s = gff.nextLine().split("\t");
                if (s[0].toCharArray()[0] == '#') {
                    continue;
                }

                if ("exon".equals(s[2])) {

                    int start = Integer.parseInt(s[3]);
                    int end = Integer.parseInt(s[4]);
                    String s1= s[0]+"\t"+m.get(s[0]).toString().substring(start, start + 20)+"\t"+m.get(s[0]).toString().substring(end - 20, end); ///chr
                    bwM.write(s1);
                    bwM.newLine();
                  
//  System.out.println(Matrix[i]);
                }

            }
            gff.close();
            bwM.close();
         //   System.out.println(Matrix.length);
//            for (int j = 0; j < Matrix.length; j++) {
//                String[] strings = Matrix[j];
//                
//            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(JavaApplication1.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        catch (IOException ex) {
                Logger.getLogger(JavaApplication1.class.getName()).log(Level.SEVERE, null, ex);
            }

    }
    
    public static void UniqMrk(String Markfile){
        try {
            BufferedReader mark = new BufferedReader(new FileReader(Markfile));
            System.out.println(mark.lines());
            int i=0;
            String[] s=new String[]{};
            

            while (mark.readLine() != null) {
                s[i] = mark.readLine();
                i++;

            }

            
            
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JavaApplication1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JavaApplication1.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        

        
        
    }
            

}
