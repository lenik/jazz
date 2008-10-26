package net.bodz.bas.files;

import java.nio.charset.Charset;


public class INIFile extends MapsFile {

    public INIFile(Object file, Charset charset) {
        super(file, charset);
    }

    public INIFile(Object file, String encoding) {
        super(file, encoding);
    }

    public INIFile(Object file) {
        super(file);
    }

    public INIFile(String path) {
        super(path);
    }

}
