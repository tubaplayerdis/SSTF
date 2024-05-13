package com.tbdis.sstf;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class Writer {
    public static void WriteSettings(String path, Setting[] settings) throws  WriterException {
        File file = new File(path);
        WriteSettings(file, settings);
    }
    public static void WriteSettings(Path path, Setting[] settings) throws WriterException {
        File file = new File(String.valueOf(path));
        WriteSettings(file, settings);
    }
    public static void WriteSettings(File file, Setting[] settings) throws WriterException {
        FileWriter writer = null;
        try {
            writer = new FileWriter(file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new WriterException("Unable to create File Writer: "+e.getMessage());
        }

        try {
            for (Setting setting : settings){
                writer.write(setting.Name+"│"+setting.Data);
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new WriterException("Unable to write file: "+e.getMessage());
        }


        try {
            writer.close();
        } catch (IOException e) {
            throw new WriterException("Unable to close File Writer: "+e.getMessage());
        }

    }
}
