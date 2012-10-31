package net.bodz.bas.cli;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.c.type.ClassLocal;
import net.bodz.bas.c.type.ClassLocals;
import net.bodz.bas.cli.model.IOptionGroup;
import net.bodz.bas.cli.skel.CLIException;
import net.bodz.bas.err.ParseException;

public class ClassOptions {

    // private static boolean cache = false;
    private static ClassLocal<IOptionGroup> clOptions = ClassLocals.createMap(IOptionGroup.class);

    public static <T> IOptionGroup getClassOptions(Class<T> clazz) {
        IOptionGroup copt = clOptions.load(clazz);
        return copt;
    }

    public static String[] loadOptions(Object classobj, String[] args)
            throws CLIException {
        List<String> list = new ArrayList<String>(args.length);
        for (String arg : args)
            list.add(arg);
        loadOptions(classobj, list);
        return list.toArray(new String[0]);
    }

    public static void loadOptions(Object context, List<String> args)
            throws CLIException {
        assert context != null;
        Class<Object> clazz = (Class<Object>) context.getClass();
        IOptionGroup group = getClassOptions(clazz);
        try {
            group.load(context, args);
        } catch (ParseException e) {
            throw new CLIException(e.getMessage(), e);
        }
    }

}
