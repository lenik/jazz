package user.cg;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.c.reflect.query.PublicFields;
import net.bodz.bas.c.reflect.query.ReflectQuery;
import net.bodz.bas.c.string.Strings;
import net.bodz.bas.io.res.builtin.URLResource;
import net.bodz.bas.io.res.tools.StreamReading;
import net.bodz.bas.log.LogLevel;

public class LoggerCG {

    List<String> levelNames;
    String src_ILogger;
    String src_Logger;
    String src_AbstractLogger;
    String src_SinkBasedLogger;

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

        URL classILoggerURL = getClass().getResource("LoggerCG.ILogger");
        URL classLoggerURL = getClass().getResource("LoggerCG.Logger");
        URL classAbstractLoggerURL = getClass().getResource("LoggerCG.AbstractLogger");
        URL classSinkBasedLoggerURL = getClass().getResource("LoggerCG.SinkBasedLogger");

        src_ILogger = new URLResource(classILoggerURL)//
                .to(StreamReading.class).readString();
        src_Logger = new URLResource(classLoggerURL)//
                .to(StreamReading.class).readString();
        src_AbstractLogger = new URLResource(classAbstractLoggerURL)//
                .to(StreamReading.class).readString();
        src_SinkBasedLogger = new URLResource(classSinkBasedLoggerURL)//
                .to(StreamReading.class).readString();
    }

    public void makeILogger()
            throws IOException {
        for (String levelName : levelNames) {
            String LevelName = Strings.ucfirst(levelName);

            String level = src_ILogger;
            level = level.replace("${name}", levelName);
            level = level.replace("${Name}", LevelName);

            String rettype = "void";
            String retdoc = "";
            String retdoc_ = "";
            if (levelName.equals("fatal") || levelName.equals("error")) {
                rettype = "boolean";
                retdoc = ""
                        + "     * @return If user interaction is concerned, return <code>true</code> for retry, and\n"
                        + "     *         <code>false</code> for continue/cancel. Otherwise, return <code>false</code>.\n";
                retdoc_ = retdoc;
            }
            level = level.replace("${rettype}", rettype);
            level = level.replace("${retdoc}", retdoc);
            level = level.replace("${retdoc_}", retdoc_);

            System.out.println(level);
        }
    }

    public void makeLogger()
            throws IOException {
        for (String levelName : levelNames) {
            String LevelName = Strings.ucfirst(levelName);

            String level = src_Logger;
            level = level.replace("${name}", levelName);
            level = level.replace("${Name}", LevelName);

            String rettype = "void";
            String retdoc = "";
            String retdoc_ = "";
            if (levelName.equals("fatal") || levelName.equals("error")) {
                rettype = "boolean";
                retdoc = ""
                        + "     * @return If user interaction is concerned, return <code>true</code> for retry, and\n"
                        + "     *         <code>false</code> for continue/cancel. Otherwise, return <code>false</code>.\n";
                retdoc_ = retdoc;
            }
            level = level.replace("${rettype}", rettype);
            level = level.replace("${retdoc}", retdoc);
            level = level.replace("${retdoc_}", retdoc_);

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

    public void makeSinkBasedLogger()
            throws IOException {
        for (String levelName : levelNames) {
            String LevelName = Strings.ucfirst(levelName);
            String LEVELNAME = levelName.toUpperCase();

            String text = src_SinkBasedLogger;
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

    static String target = "AL";

    public static void main(String[] args)
            throws Exception {
        LoggerCG cg = new LoggerCG();
        switch (target) {
        case "IL":
            cg.makeILogger();
            break;
        case "L":
            cg.makeLogger();
            break;
        case "AL":
            cg.makeAbstractLogger();
            break;
        case "SL":
            cg.makeSinkBasedLogger();
            break;
        default:
            assert false;
        }
    }

}
