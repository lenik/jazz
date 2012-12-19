package user.cg;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.c.reflect.query.PublicFields;
import net.bodz.bas.c.reflect.query.ReflectQuery;
import net.bodz.bas.c.string.Strings;
import net.bodz.bas.io.resource.builtin.URLResource;
import net.bodz.bas.io.resource.tools.StreamReading;
import net.bodz.bas.log.LogLevel;

public class LoggerCG {

    List<String> levelNames;
    String src_Logger;
    String src_AbstractLogger;

    public LoggerCG()
            throws IOException {
        PublicFields levelFields = ReflectQuery.selectFields(LogLevel.class);
        levelFields.staticOnly();

        levelNames = new ArrayList<String>();

        for (Field f : levelFields) {
            if (!LogLevel.class.isAssignableFrom(f.getType()))
                continue;
            String levelName = f.getName().toLowerCase();
            levelNames.add(levelName);
        }

        URL classLoggerURL = getClass().getResource("LoggerCG.Logger");
        URL classAbstractLoggerURL = getClass().getResource("LoggerCG.AbstractLogger");

        src_Logger = new URLResource(classLoggerURL)//
                .tooling()._for(StreamReading.class).readString();
        src_AbstractLogger = new URLResource(classAbstractLoggerURL)//
                .tooling()._for(StreamReading.class).readString();
    }

    public void makeLogger()
            throws IOException {
        for (String levelName : levelNames) {
            String LevelName = Strings.ucfirst(levelName);

            String level = src_Logger;
            level = level.replace("${name}", levelName);
            level = level.replace("${Name}", LevelName);

            String rettype = "void";
            if (levelName.equals("fatal") || levelName.equals("error")) {
                rettype = "boolean";
            }
            level = level.replace("${rettype}", rettype);

            System.out.println(level);
        }
    }

    public void makeAbstractLogger()
            throws IOException {
        for (String levelName : levelNames) {
            String LevelName = Strings.ucfirst(levelName);
            String LEVELNAME = levelName.toUpperCase();

            String text = src_AbstractLogger;
            text = text.replace("${name}", levelName);
            text = text.replace("${Name}", LevelName);
            text = text.replace("${NAME}", LEVELNAME);

            String rettype = "void";
            String _return = "";
            if (levelName.equals("fatal") || levelName.equals("error")) {
                rettype = "boolean";
                _return = "return ";
            }
            text = text.replace("${rettype}", rettype);
            text = text.replace("${return}", _return);

            System.out.println(text);
        }
    }

    static boolean generateInterfaceOrClass = false;

    public static void main(String[] args)
            throws Exception {
        LoggerCG cg = new LoggerCG();
        if (generateInterfaceOrClass)
            cg.makeLogger();
        else
            cg.makeAbstractLogger();
    }

}
