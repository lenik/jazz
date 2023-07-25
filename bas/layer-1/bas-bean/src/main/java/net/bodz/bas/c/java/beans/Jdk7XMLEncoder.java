package net.bodz.bas.c.java.beans;

import java.io.OutputStream;

import com.googlecode.openbeans.XMLEncoder;

public class Jdk7XMLEncoder {

    public static XMLEncoder getInstance(OutputStream out, String charset, boolean declaration, int indentation) {
        XMLEncoder encoder = new XMLEncoder(out);
        return encoder;
    }

}
