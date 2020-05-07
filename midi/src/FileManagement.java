
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileManagement {

    static File config = new File("Config.SBE");
    static boolean fileExists = false;

    public FileManagement() throws FileNotFoundException {
    }

    public static void createFile(){

        try {
            if(config.createNewFile()) {


                FileWriter myWriter = new FileWriter(config);
                myWriter.write("false\n");
                myWriter.write("---SETTINGS--- //Refer to Documentation\n");
                myWriter.write(""+StartUpManager.c.getRecordKey()+"\n");
                myWriter.write(""+Window.themeColor);
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            }
            else {
                fileExists = true;
                System.out.println("File already exists");
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public static String readSavedKey(){
        String text = "";
        //int lineNumber = 2;
        try {
            FileReader readfile = new FileReader("Config.SBE");
            BufferedReader readbuffer = new BufferedReader(readfile);
            for (int i = 0; i < 3; i++) {
                text = readbuffer.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("The specific Line is: " + text);
        return text;
    }

    public static String readThemeSetting(){
        String text = "";
        //int lineNumber = 2;
        try {
            FileReader readfile = new FileReader("Config.SBE");
            BufferedReader readbuffer = new BufferedReader(readfile);
            for (int i = 0; i < 4; i++) {
                text = readbuffer.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("The specific Line is: " + text);
        return text;
    }

    public static void saveFile() throws IOException {
        FileWriter myWriter = new FileWriter(config);
        myWriter.write("false\n");
        myWriter.write("---SETTINGS--- //Refer to Documentation\n");
        myWriter.write(""+StartUpManager.c.getRecordKey()+"\n");
        myWriter.write(""+Window.themeColor);

        HashMap<Integer,String> keyToFile = StartUpManager.c.getKeyToFile();
        keyToFile.forEach((k, v) -> {
            try {
                myWriter.write(k+v+"\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        myWriter.close();
        System.out.println("\nSuccessfully saved to the file.");
    }


    public static boolean checkIfFirstSetup() throws FileNotFoundException {
        try{
            Scanner r = new Scanner(config);
            String firstLine = r.nextLine();
            if(firstLine.equals("false")){
                return false;
            }
            else
                return true;
        } catch (FileNotFoundException e) {
            return true;
        }
    }

    public static File[] getEverySavedRecording(){
        File folder = new File("Recordings");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                System.out.println("File " + listOfFiles[i].getName());
            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + listOfFiles[i].getName());
            }
        }
        return listOfFiles;
    }

}
