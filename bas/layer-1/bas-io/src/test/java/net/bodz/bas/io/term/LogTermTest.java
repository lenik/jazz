package net.bodz.bas.io.term;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LogTermTest {

    static void generate(String[] levels)
            throws IOException {
        URL url = LogTermTest.class.getResource("LogTerm.lm");
        String levelTempl = Files.readAll(url);

        System.out.println("static String generator = \"" + LogTermTest.class + "\";");
        Map<String, String> vars = new HashMap<String, String>();
        for (String level : levels) {
            vars.put("name", level.toLowerCase());
            vars.put("NAME", level.toUpperCase());
            vars.put("Name", Strings.ucfirst(level.toLowerCase()));
            String code = Interps.dereference(levelTempl, vars);
            System.out.println(code);
        }
    }

    public static void main(String[] args)
            throws IOException {
        String levels = "FATAL:ERROR:USER:WARN:MESG:INFO:DETAIL:DEBUG:TRACE";
        generate(levels.split(":"));
    }

}
