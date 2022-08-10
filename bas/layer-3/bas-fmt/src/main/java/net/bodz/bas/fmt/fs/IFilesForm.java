package net.bodz.bas.fmt.fs;

import java.io.File;
import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;

public interface IFilesForm {

    void readObject(File folder)
            throws IOException, ParseException;

    void writeObject(File folder)
            throws IOException, FormatException;

}
