import com.tbdis.sstf.Setting;
import com.tbdis.sstf.Writer;
import com.tbdis.sstf.WriterException;

import java.io.File;

public class WriterTest {
    public static void main(String[] args) {
        Setting[] settings = GenericTest.CreateSettingTestArray();
        File file = new File("Settings.txt");
        try {
            Writer.WriteFile(file, settings);
        } catch (WriterException e) {
            System.out.println("Writer Exception: "+e.getMessage());
        }
    }
}
