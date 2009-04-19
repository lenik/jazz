package net.bodz.bas.log;

import java.io.IOException;
import java.net.URL;

import net.bodz.bas.io.Files;
import net.bodz.bas.text.interp.Interps;
import net.bodz.bas.types.HashTextMap;
import net.bodz.bas.types.TextMap;
import net.bodz.bas.types.util.Strings;
import net.bodz.bas.util.LogTerm;

public class LogTermTest {

    static void generate(String[] levels) throws IOException {
        URL url = Files.classData(LogTerm.class, "lm");
        String levelTempl = Files.readAll(url);

        System.out.println("static String generator = \"" + LogTermTest.class
                + "\";");
        TextMap<String> vars = new HashTextMap<String>();
        for (String level : levels) {
            vars.put("name", level.toLowerCase());
            vars.put("NAME", level.toUpperCase());
            vars.put("Name", Strings.ucfirst(level.toLowerCase()));
            String code = Interps.dereference(levelTempl, vars);
            System.out.println(code);
        }
    }

    public static void main(String[] args) throws IOException {
        String levels = "FATAL:ERROR:USER:WARN:MESG:INFO:DETAIL:DEBUG:TRACE";
        generate(levels.split(":"));
    }

}
