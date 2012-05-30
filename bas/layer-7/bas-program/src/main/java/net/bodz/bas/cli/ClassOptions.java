package net.bodz.bas.cli;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import net.bodz.bas.c.reflect.query.ReflectQuery;
import net.bodz.bas.c.string.Strings;
import net.bodz.bas.c.type.ClassLocal;
import net.bodz.bas.c.type.ClassLocals;
import net.bodz.bas.cli.opt.AbstractOption;
import net.bodz.bas.cli.opt.IOption;
import net.bodz.bas.cli.opt.IOptionGroup;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.program.ProgramNameUtil;

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

    public static void loadOptions(Object classobj, List<String> args)
            throws CLIException {
        assert classobj != null;
        Class<Object> clazz = (Class<Object>) classobj.getClass();
        IOptionGroup group = getClassOptions(clazz);
        try {
            group.load(classobj, args);
        } catch (ParseException e) {
            throw new CLIException(e.getMessage(), e);
        }
    }

    @SuppressWarnings("unchecked")
    public static String helpOptions(Class<?> clazz, String restSyntax, final int tabsize, final int docColumn)
            throws CLIException {
        final IOptionGroup group = getClassOptions(clazz);
        Map<String, IOption> options = group.getOptions();
        StringBuilder buffer = new StringBuilder(options.size() * 80);

        String program = ProgramNameUtil.getProgramName(clazz);
        AlignedOptionFormatter formatter = new AlignedOptionFormatter();
        formatter.setTabSize(tabsize);
        formatter.setDocColumn(docColumn);

        buffer.append("Syntax: \n");
        buffer.append("    " + program + " [OPTION] [--]");

        if (group.specfiles != null) {
            int ifile = 0;
            while (true) {
                AbstractOption fopt = group.specfiles.get(ifile++);
                if (fopt == null)
                    break;
                String fnam = fopt.getFriendlyName();
                buffer.append(' ');
                buffer.append(fnam);
            }
        }

        buffer.append(" ");
        if (restSyntax == null) {
            boolean usingRestSyntax = false;
            if (usingRestSyntax) {
                // getRestSyntax();
                Method restf = ReflectQuery.selectDeclaredMethods(clazz).nameEquals("getRestSyntax").iterator(true)
                        ._next();
                if (restf == null)
                    buffer.append("...");
                else {
                    try {
                        restSyntax = (String) restf.invoke(null);
                    } catch (ReflectiveOperationException e) {
                        throw new CLIException(e.getMessage(), e);
                    }
                    buffer.append(restSyntax);
                }
            } else {
                // Option appopt = Nullables.getAnnotation(clazz, Option.class);
                restSyntax = "XXX"; // appopt.vnam();
                buffer.append(restSyntax);
            }
        }
        buffer.append(restSyntax);
        buffer.append("\n");

        buffer.append("\n");

        Map<String, Set<IOption>> groups = new HashMap<String, Set<IOption>>();
        Comparator<IOption> optnamsort = new Comparator<IOption>() {
            @Override
            public int compare(IOption a, IOption b) {
                return a.getFriendlyName().compareTo(b.getFriendlyName());
            }
        };
        for (Map.Entry<String, IOption> entry : options.entrySet()) {
            IOption opt = entry.getValue();
            if (opt.isHidden())
                continue;
            String optnam = opt.getFriendlyName();
            if (!optnam.equals(entry.getKey()))
                continue;
            IOptionGroup group = opt.getGroup();
            if (group == null)
                group = program;
            Set<IOption> groupOpts = groups.get(group);
            if (groupOpts == null)
                groups.put(group, //
                        groupOpts = new TreeSet<IOption>(optnamsort));
            groupOpts.add(opt);
        }

        boolean first = true;
        for (Map.Entry<String, Set<IOption>> entry : groups.entrySet()) {
            if (first)
                first = false;
            else
                buffer.append("\n");
            String name = entry.getKey();
            Set<IOption> grpopts = entry.getValue();
            buffer.append(Strings.ucfirst(name) + " options: \n");
            buffer.append(formatter.filter(grpopts));
        }
        return buffer.toString();
    }

}
