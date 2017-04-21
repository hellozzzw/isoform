
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import sun.applet.AppletViewer;
import sun.java2d.pipe.BufferedRenderPipe;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author wjZhang
 */
public class main {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here

//        * * * pre - deal - with reference data (gff & fasta)
        
        ScanRefoExonSet.RnaFromGenome("data/GCA_001477535.1_Pneu_jiro_RU7_V2_rna_from_genomic.fna");
//         ScanRefoExonSet.ScanRef("data/major surface glycoprotein.gff3", "data/major surface glycoprotein.fasta");
//        ScanRefoExonSet.UniqMrk("data/exonDataset");
        
        
 //      AlgnToSet. clust("data/aln-j/mapJun.reads.out.sort","data/aln-j/LongwithExon");
 //       ForLongreadswithExon.Deal("data/aln-j/LongwithExon");
//         int m= reads.ID2Reads("data/aln-j/SetOfReads","data/aln-j/ReadsClust"); 
//         System.out.println(m);
//         
//                      reads.ID2Reads("data/SetOfReads","data/ReadsClust");
     mergeFile("data/aln-j/ReadsClust",4400);


    }
 public int count(FileInputStream filename) throws IOException {
        InputStream is = new BufferedInputStream(filename);
        byte[] c = new byte[1024];
        int count = 0;
        int readChars = 0;
        while ((readChars = is.read(c)) != -1) {
            for (int i = 0; i < readChars; ++i) {
                if (c[i] == '\n')
                    ++count;
            }
        }
        is.close();
        return count;
    }    
 
 public static  void mergeFile(String ReadsClust,int m) throws FileNotFoundException, IOException {

    
     int t;

        for (int i = 0; i < m; i++) { 
            
    
            Scanner Fs=new Scanner(new BufferedReader(new FileReader(ReadsClust+i)));
            t=0;
            while(Fs.hasNextLine()){t++;
            }
            
            System.out.println(t/2);
          //  System.out.println(ReadsClust+i+"\t"+Fs.toString().length()); 
                Fs.close();

           
          
            
            
        }
        
        
    
    }
}
    
    




