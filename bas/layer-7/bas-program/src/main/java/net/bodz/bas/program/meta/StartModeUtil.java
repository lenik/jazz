package net.bodz.bas.program.meta;

public class StartModeUtil {

    public static Integer getStartMode(Class<?> type) {
        StartMode startModeAnn = type.getAnnotation(StartMode.class);
        if (startModeAnn == null)
            return null;
        return startModeAnn.value();
    }

}
