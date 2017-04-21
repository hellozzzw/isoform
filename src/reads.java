
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author wjZhang
 */
class reads {

    static int  ID2Reads(String ID_file,String outfile) throws IOException {
        Scanner IDScanner = new Scanner(new BufferedReader(new FileReader(ID_file)));
//        HashMap<String, String> nMap = new HashMap<>();
        Map<String, String> m = new HashMap();

        int i=0;
        Scanner fasta = new Scanner(new BufferedReader(new FileReader("data/pacbio-ccs-read.fasta")));
        BufferedWriter bw = new BufferedWriter(new FileWriter(outfile+i));
        String s, s2, s3;
        while (fasta.hasNextLine()) {
            s2 = fasta.nextLine().substring(1);
            s3 = fasta.nextLine();
            m.put(s2, s3);
        }
//        for (Map.Entry<String,String> entry : m.entrySet()) {
//            System.out.println( entry.getValue());
//
//            
//        }
        fasta.close();

        while (IDScanner.hasNextLine()) {
            s = IDScanner.nextLine();

            if (m.get(s) == null) {
//                bw.newLine();
                bw.flush();
                bw.close();
                
                bw= new BufferedWriter(new FileWriter(outfile+(i++)));
//                  System.out.println();
            } else {
//             System.out.println(m.get(s));
                bw.write(">"+s.split("\\/")[1]);
                bw.newLine();
                bw.write(m.get(s));
                bw.newLine();

            }

            //        System.out.println(m.get(IDScanner.nextLine())); 
        }
        bw.flush();
        bw.close();
        IDScanner.close();
        return i;
            

    }

}
