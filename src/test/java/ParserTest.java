import com.tbdis.sstf.Parser;
import com.tbdis.sstf.ParserException;
import com.tbdis.sstf.Setting;

import java.io.File;

public class ParserTest {
    //Assumes there is data to parse
    public static void main(String[] args){
        File file = new File("parsertest.txt");
        Setting[] settings = null;
        try {
            settings = (Setting[]) Parser.ParseFile(file);
            //Should change settings to Member[]
        } catch (ParserException e) {
            System.out.println("Error: "+e.getMessage());
        }
        assert settings != null;
        for(Setting setting : settings){
            System.out.println("---------------------");
            System.out.println("Name: "+setting.Name);
            System.out.println("Data: "+setting.Data);
            System.out.println("---------------------");
        }
    }
}
