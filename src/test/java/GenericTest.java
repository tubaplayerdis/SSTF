import com.tbdis.sstf.*;

import java.io.File;

public class GenericTest {
    public static Setting[] CreateSettingTestArray(){
        Setting[] settings = new Setting[3];
        settings[0] = new Setting("Name", "Jimmy");
        settings[1] = new Setting("Age", "18");
        settings[2] = new Setting("Location", "Chicago");
        return settings;
    }
    public static void main(String[] args){
        //Create File
        File file = new File("test.txt");
        try {
            Writer.WriteFile(file, CreateSettingTestArray());
        } catch (WriterException e) {
            System.out.println("Error with Writer: "+e.getMessage());
        }
        Member[] members = null;
        try {
            members = Parser.ParseFile(file);
        } catch (ParserException e) {
            System.out.println("Error with parser: "+e.getMessage());
        }
        //If all is good
        assert members != null;
        System.out.println("Parsed Data!");
        for(Member member : members){
            System.out.println("Name: "+member.Name+" Data: "+member.Data);
        }

    }
}
