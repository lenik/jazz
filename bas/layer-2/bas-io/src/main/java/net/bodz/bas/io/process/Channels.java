package net.bodz.bas.io.process;

import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.channels.ReadableByteChannel;

public class Channels {

    public static ReadableByteChannel findChannel(InputStream in) {
        if (in == null)
            return null;
        if (in instanceof FilterInputStream) {
            FilterInputStream filterIn = (FilterInputStream) in;
            Field in_field;
            try {
                in_field = filterIn.getClass().getField("in");
                InputStream in2 = (InputStream) in_field.get(filterIn);
                return findChannel(in2);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        if (in instanceof FileInputStream) {
            FileInputStream fileIn = (FileInputStream) in;
            return fileIn.getChannel();
        }
        return null;
    }

}
