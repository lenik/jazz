package net.bodz.bas.cli.meta;

public class StartModeUtil {

    public static Integer getStartMode(Class<?> type) {
        StartMode startModeAnn = type.getAnnotation(StartMode.class);
        if (startModeAnn == null)
            return null;
        return startModeAnn.value();
    }

}