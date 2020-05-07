import java.io.FileNotFoundException;
import java.io.IOException;

public class StartUpManager {

    protected static Controller c = new Controller();

    public static void startApp() throws IOException {
        if (FileManagement.checkIfFirstSetup()) {
            System.out.println("Starting in first boot mode");
            FileManagement.createFile();
            Window.make();
        } else {
            System.out.println("Starting in non first boot");
            int savedKey = Integer.parseInt(FileManagement.readSavedKey());
            c.setSavedKey(savedKey);
            Window.make();
        }
    }
}
