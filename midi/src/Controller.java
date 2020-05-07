import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

public class Controller {

     static int recordKey;
     static HashMap<Integer,String> keyToFile = new HashMap<>();

     public void setRecordKey(int keyNum) throws IOException {
         recordKey = keyNum;
         //Window.enableRecordButton();
         Window.b.setEnabled(true);
         System.out.println(" \nRECORD KEY SET TO: "+recordKey);
         FileManagement.saveFile();
     }

     public void setSavedKey(int keyNum){
         recordKey = keyNum;
     }

     public int getRecordKey(){
         return recordKey;
     }

     public void removeKey() { recordKey=-1; }

     public void recieveMappedKey(int keynum,String file){
         keyToFile.put(keynum,file);
     }

     public HashMap<Integer, String> getKeyToFile() { return keyToFile; }
}
