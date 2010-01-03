package net.bodz.bas.util;

import java.io.IOException;
import java.net.URL;

import net.bodz.bas.commons.collection.TextMap;
import net.bodz.bas.commons.typealiases.HashTextMap;
import net.bodz.bas.io.Files;
import net.bodz.bas.text.util.Interps;
import net.bodz.bas.text.util.Strings;

public class LogTermTest {

    static void generate(String[] levels) throws IOException {
        URL url = LogTermTest.class.getResource("LogTerm.lm"); //$NON-NLS-1$
        String levelTempl = Files.readAll(url);

        System.out.println("static String generator = \"" + LogTermTest.class + "\";"); //$NON-NLS-1$ //$NON-NLS-2$
        TextMap<String> vars = new HashTextMap<String>();
        for (String level : levels) {
            vars.put("name", level.toLowerCase()); //$NON-NLS-1$
            vars.put("NAME", level.toUpperCase()); //$NON-NLS-1$
            vars.put("Name", Strings.ucfirst(level.toLowerCase())); //$NON-NLS-1$
            String code = Interps.dereference(levelTempl, vars);
            System.out.println(code);
        }
    }

    public static void main(String[] args) throws IOException {
        String levels = "FATAL:ERROR:USER:WARN:MESG:INFO:DETAIL:DEBUG:TRACE"; //$NON-NLS-1$
        generate(levels.split(":")); //$NON-NLS-1$
    }

}
