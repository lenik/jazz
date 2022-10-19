package net.bodz.bas.t.variant;

import java.io.File;
import java.util.Date;
import java.util.List;

import net.bodz.bas.c.java.util.Dates;
import net.bodz.bas.c.string.Strings;
import net.bodz.bas.err.ParseException;

public class StringFnCodegen
        extends GenericCodegen {

    // \beginTemplate primitive

    public static float parseFloat(String s)
            throws ParseException {
        try {
            return Float.parseFloat(s);
        } catch (NumberFormatException e) {
            throw new ParseException("error parse " + s, e);
        }
    }

    public static float parseFloat(String s, float fallback) {
        if (s == null)
            return fallback;
        try {
            return Float.parseFloat(s);
        } catch (NumberFormatException e) {
            return fallback;
        }
    }
    // \endTemplate

    // \beginTemplate default

    public static File parseFile(String s)
            throws ParseException {
        if (s == null)
            return null;
        try {
            return new File(s);
        } catch (Exception e) {
            throw new ParseException("error parse " + s, e);
        }
    }

    public static File parseFile(String s, File fallback) {
        if (s == null)
            return fallback;
        try {
            return new File(s);
        } catch (Exception e) {
            return fallback;
        }
    }
    // \endTemplate

    // \beginTemplate other

    public static Date parseDate(String s)
            throws ParseException {
        if (s == null)
            return null;
        try {
            return Dates.ISO8601.parse(s);
        } catch (Exception e) {
            throw new ParseException("error parse " + s, e);
        }
    }

    public static Date parseDate(String s, Date fallback) {
        if (s == null)
            return fallback;
        try {
            return parseDate(s);
        } catch (Exception e) {
            return fallback;
        }
    }
    // \endTemplate

    @Override
    protected void mainImpl(String[] args)
            throws Exception {
        for (String key : typeGroups.keySet()) {
            List<TypeInfo> types = typeGroups.get(key);
            List<String> template = getTemplates().get(key);
            if (template == null)
                template = getTemplates().get(K_OTHER);
            if (template == null)
                throw new IllegalArgumentException("n/a template for " + key);

            for (TypeInfo info : types) {
                for (String line : template) {
                    switch (key) {
                    case K_PRIMITIVE:
                        line = line.replace("float", info.type.getSimpleName());
                        line = line.replace("Float", info.boxedType.getSimpleName());
                        break;

                    case K_DEFAULT:
                        line = line.replace("File", info.type.getSimpleName());
                        break;

                    default:
                        line = line.replace("Date", info.type.getSimpleName());
                        line = line.replace("date", Strings.lcfirst(info.type.getSimpleName()));
                        break;
                    }
                    System.out.println(line);
                }
            }
        }
    }

    public static void main(String[] args)
            throws Exception {
        new StringFnCodegen().execute(args);
    }

}
