package net.bodz.bas.fmt.fs;

import java.io.IOException;
import java.nio.file.Path;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;

public interface IDirForm {

    void readObject(Path folder)
            throws IOException, ParseException;

    void writeObject(Path folder)
            throws IOException, FormatException;
//    void readObject(File folder)
//            throws IOException, ParseException;
//
//    void writeObject(File folder)
//            throws IOException, FormatException;

}
