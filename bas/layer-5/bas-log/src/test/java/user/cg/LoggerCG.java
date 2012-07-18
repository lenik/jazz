package user.cg;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.c.loader.ClassResource;
import net.bodz.bas.c.reflect.query.PublicFields;
import net.bodz.bas.c.reflect.query.ReflectQuery;
import net.bodz.bas.c.string.Strings;
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

        src_Logger = ClassResource.classData(getClass(), "Logger")//
                .tooling()._for(StreamReading.class).readTextContents();
        src_AbstractLogger = ClassResource.classData(getClass(), "AbstractLogger")//
                .tooling()._for(StreamReading.class).readTextContents();
    }

    public void makeLogger()
            throws IOException {
        for (String levelName : levelNames) {
            String LevelName = Strings.ucfirst(levelName);

            String level = src_Logger;
            level = level.replaceAll("@name@", levelName);
            level = level.replaceAll("@Name@", LevelName);

            System.out.println(level);
        }
    }

    public void makeAbstractLogger()
            throws IOException {
        for (String levelName : levelNames) {
            String LevelName = Strings.ucfirst(levelName);
            String LEVELNAME = levelName.toUpperCase();

            String text = src_AbstractLogger;
            text = text.replaceAll("@name@", levelName);
            text = text.replaceAll("@Name@", LevelName);
            text = text.replaceAll("@NAME@", LEVELNAME);

            String rettype = "void";
            String _return = "";
            if (levelName.equals("fatal") || levelName.equals("error")) {
                rettype = "boolean";
                _return = "return ";
            }
            text = text.replaceAll("@rettype@", rettype);
            text = text.replaceAll("@return@", _return);

            System.out.println(text);
        }
    }

    public static void main(String[] args)
            throws Exception {
        LoggerCG cg = new LoggerCG();
        // cg.makeLogger();
        cg.makeAbstractLogger();
    }

}
