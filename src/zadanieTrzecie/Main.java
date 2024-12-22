package zadanieTrzecie;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        String inputPath = "input.txt";
        String outputPath = "output.txt";

        try (
                BufferedReader br = new BufferedReader(new FileReader(inputPath));
                BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath));
        ){
            String line;
            while((line=br.readLine()) !=null){
                String modifiedLine = line.replace(" ", "-");
                bw.write(modifiedLine);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
