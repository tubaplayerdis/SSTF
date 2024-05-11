package com.tbdis.sstf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Parser {
    public static Setting[] ParseSettings(String path) throws ParserException {
        File file = new File(path);
        return ParseSettings(file);
    }

    public static Setting[] ParseSettings(Path path) throws ParserException {
        File file = new File(String.valueOf(path));
        return ParseSettings(file);
    }

    public static Setting[] ParseSettings(File file) throws ParserException {
        if(!file.exists()) throw new ParserException("File does not exist!");

        FileReader reader = null;
        try{
            reader = new FileReader(file);
        } catch (IOException e){
            throw new ParserException("Unable to create File Reader: "+e.getMessage());
        }

        int lines = -1;
        try{
            lines = (int)Files.lines(Paths.get(file.getAbsolutePath())).count();
        } catch (IOException e){
            throw new ParserException("Unable to get number of lines in file: "+e.getMessage());
        }

        if(lines == -1){
            throw new ParserException("Number of lines invalid");
        }

        Setting[] settings = new Setting[lines];

        try (BufferedReader br = new BufferedReader(reader)) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                if(line.indexOf("│") != line.lastIndexOf("│") || !line.contains("│")) throw new ParserException("Format Invalid");
                String nam = line.substring(0, line.indexOf("│"));
                String dat = line.substring(line.indexOf("│"));
                settings[i] = new Setting(nam, dat);
                i++;
            }
        } catch (IOException e) {
            throw new ParserException("Unable to Read File: "+e.getMessage());
        }

        try{
            reader.close();
        } catch (IOException e){
            throw new ParserException("Unable to close reader: "+e.getMessage());
        }

        return settings;
    }
}
