package net.bodz.bas.cli;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import net.bodz.bas.c.reflect.query.ReflectQuery;
import net.bodz.bas.c.string.StringLengthComparator;
import net.bodz.bas.c.string.Strings;
import net.bodz.bas.c.type.ClassLocal;
import net.bodz.bas.c.type.ClassLocals;
import net.bodz.bas.cli.opt.AbstractOption;
import net.bodz.bas.cli.opt.ClassOptions;
import net.bodz.bas.cli.opt.IOptionGroup;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.lang.fn.Filt1;
import net.bodz.bas.meta.program.ProgramNameUtil;

public class ClassCLI {

    private static boolean cache = false;
    private static ClassLocal<ClassOptions<?>> clOptions;
    static {
        clOptions = ClassLocals.createMap(//
                ClassOptions.class);
    }

    public static <T> ClassOptions<T> getClassOptions(Class<T> clazz) {
        ClassOptions<T> copt = (ClassOptions<T>) clOptions.get(clazz);
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
        ClassOptions<Object> copt = getClassOptions(clazz);
        try {
            copt.load(classobj, args);
        } catch (ParseException e) {
            throw new CLIException(e.getMessage(), e);
        }
    }

    protected static abstract class OptionFormat
            extends Filt1<String, Set<AbstractOption>> {

        @Override
        public abstract String filter(Set<AbstractOption> a);

    }

    @SuppressWarnings("unchecked")
    public static String helpOptions(Class<?> clazz, String restSyntax, final int tabsize, final int docColumn)
            throws CLIException {
        final ClassOptions<Object> copt = (ClassOptions<Object>) getClassOptions(clazz);
        TreeMap<String, AbstractOption> options = copt.getOptions();
        StringBuilder buffer = new StringBuilder(options.size() * 80);
        final char[] tab = new char[tabsize];
        Arrays.fill(tab, ' ');

        String program = ProgramNameUtil.getProgramName(clazz);
        buffer.append("Syntax: \n");
        buffer.append(tab);
        buffer.append(program + " [OPTION] [--]");
        if (copt.specfiles != null) {
            int ifile = 0;
            while (true) {
                AbstractOption fopt = copt.specfiles.get(ifile++);
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

        Map<String, Set<AbstractOption>> groups = new HashMap<String, Set<AbstractOption>>();
        Comparator<AbstractOption> optnamsort = new Comparator<AbstractOption>() {
            @Override
            public int compare(AbstractOption a, AbstractOption b) {
                return a.getFriendlyName().compareTo(b.getFriendlyName());
            }
        };
        for (Map.Entry<String, AbstractOption> entry : options.entrySet()) {
            AbstractOption opt = entry.getValue();
            if (opt.isHidden())
                continue;
            String optnam = opt.getFriendlyName();
            if (!optnam.equals(entry.getKey()))
                continue;
            IOptionGroup group = opt.getGroup();
            if (group == null)
                group = program;
            Set<AbstractOption> groupOpts = groups.get(group);
            if (groupOpts == null)
                groups.put(group, //
                        groupOpts = new TreeSet<AbstractOption>(optnamsort));
            groupOpts.add(opt);
        }

        OptionFormat groupfmt = new OptionFormat() {
            @Override
            public String filter(Set<AbstractOption> opts) {
                StringBuilder buffer = new StringBuilder(opts.size() * 80);
                StringBuilder line = new StringBuilder(80);
                for (AbstractOption opt : opts) {
                    String[] aliases = copt.getAliases(opt);
                    Arrays.sort(aliases, StringLengthComparator.INSTANCE);

                    line.setLength(0);
                    line.append(tab);
                    int col = tabsize;
                    boolean hasshort = false;
                    for (int i = 0; i < aliases.length; i++) {
                        String nam = aliases[i];
                        if (i > 0) {
                            line.append(',');
                            while ((++col % tabsize) != 0)
                                line.append(' ');
                        }
                        if (nam.length() == 1) {
                            line.append('-' + nam);
                            hasshort = true;
                            col += 2;
                        } else {
                            line.append("--" + nam);
                            col += 3 + nam.length();
                        }
                    }
                    if (!hasshort)
                        line.insert(tabsize, tab);
                    String vnam = opt.getValueHint();
                    if (!vnam.isEmpty()) {
                        if (aliases[aliases.length - 1].length() > 1)
                            line.append('=');
                        line.append(vnam);
                    }

                    String doc = opt.getDescription();

                    col = line.length();
                    if (col >= docColumn && !doc.isEmpty()) {
                        line.append('\n');
                        col = 0;
                    }
                    while (col < docColumn) {
                        col++;
                        line.append(' ');
                    }

                    buffer.append(line.toString());

                    buffer.append(doc);
                    buffer.append('\n');
                }
                return buffer.toString();
            }
        };

        boolean first = true;
        for (Map.Entry<String, Set<AbstractOption>> group : groups.entrySet()) {
            if (first)
                first = false;
            else
                buffer.append("\n");
            String name = group.getKey();
            Set<AbstractOption> grpopts = group.getValue();
            buffer.append(Strings.ucfirst(name) + " options: \n");
            buffer.append(groupfmt.filter(grpopts));
        }
        return buffer.toString();
    }

}
