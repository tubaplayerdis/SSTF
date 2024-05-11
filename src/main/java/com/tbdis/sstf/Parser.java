package com.tbdis.sstf;

import java.io.File;
import java.nio.file.Path;

public class Parser {
    public static Setting[] ParseSettings(String path) throws ParserException {

    }

    public static Setting[] ParseSettings(Path path) throws ParserException {

    }

    public static Setting[] ParseSettings(File file) throws ParserException {
        if(!file.exists()) throw new ParserException("File does not exist!");

    }
}
