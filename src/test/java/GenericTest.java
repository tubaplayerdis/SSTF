import com.tbdis.sstf.*;

import java.io.File;
import java.util.Calendar;
import java.util.Random;

public class GenericTest {
    private static String[] generateRandomWords(int numberOfWords)
    {
        String[] randomStrings = new String[numberOfWords];
        Random random = new Random();
        for(int i = 0; i < numberOfWords; i++)
        {
            char[] word = new char[random.nextInt(8)+3]; // words of length 3 through 10. (1 and 2 letter words are boring.)
            for(int j = 0; j < word.length; j++)
            {
                word[j] = (char)('a' + random.nextInt(26));
            }
            randomStrings[i] = new String(word);
        }
        return randomStrings;
    }
    public static Setting[] CreateSettingTestArray() {
        Setting[] settings = new Setting[3];
        settings[0] = new Setting("Name", "Jimmy");
        settings[1] = new Setting("Age", "18");
        settings[2] = new Setting("Location", "Chicago");
        return settings;
    }
    public static Member[] GenerateRandomMemberArray(int maxsize) {
        Member[] members = new Member[maxsize];
        for(int i = 0; i < maxsize; i++) {
            String[] randata = generateRandomWords(2);
            members[0] = new Member(randata[0], randata[1]);
        }
        return members;
    }
    public static void main(String[] args){
        //Create File
        File file = new File("test.txt");
        try {
            Writer.WriteFile(file, GenerateRandomMemberArray(900));
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
