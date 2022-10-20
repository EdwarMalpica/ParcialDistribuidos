import java.io.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class Main {

    private String cad;
    private int n;
    private int n1;

    Properties properties;
    public Main(){
        properties = new Properties();
        try {
            properties.load(new FileInputStream(new File("config/config.properties")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        getProperties();
        writeFile();
    }

    public void getProperties(){
        cad = properties.getProperty("cad");
        n = Integer.parseInt(properties.getProperty("n"));
        n1 = Integer.parseInt(properties.getProperty("n1"));

    }

    public void writeFile(){
        System.out.println("asdasd");
        System.out.println(properties.getProperty("pathArchivo").toString());
        File file = new File(properties.getProperty("pathArchivo").toString());
        File file1 = new File(properties.getProperty("pathArchivo").toString()+properties.getProperty("nombreArchivo").toString());
        System.out.println("ruta:"+file1.getAbsolutePath());

        if(!file1.exists()||!file.exists()){
            try{
                file.mkdirs();
                file1.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

            int count = 0;
            while(true){

                try {
                    FileWriter fileWriter = new FileWriter(file1,true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    if(count%n1==0){
                        System.out.println("Escribio");
                        bufferedWriter.write(cad+"\n");
                    }else{
                        if(count%n==0){
                            bufferedWriter.write(LocalDateTime.now().toString()+"\n");
                        }
                    }
                    count++;
                    bufferedWriter.close();
                    Thread.sleep(1000);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                /*try {
                    PrintWriter printWriter = new PrintWriter(file);
                    if(count%n1==0){
                        System.out.println("Escribio");
                        printWriter.println(cad);
                    }else{
                        if(count%n==0){
                            printWriter.println(LocalDateTime.now().toString());
                        }
                    }
                    count++;
                    printWriter.close();
                    Thread.sleep(1000);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            */

            }



    }

    public static void main(String[] args) {

       Main main = new Main();
    }
}