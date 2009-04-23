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

import net.bodz.bas.a.A_bas;
import net.bodz.bas.cli.a.Option;
import net.bodz.bas.lang.ClassLocal;
import net.bodz.bas.lang.Filt1;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.lang.util.Members;
import net.bodz.bas.lang.util.Reflects;
import net.bodz.bas.nls.AppNLS;
import net.bodz.bas.types.util.Comparators;
import net.bodz.bas.types.util.Ns;
import net.bodz.bas.types.util.Strings;

public class ClassCLI {

    private static boolean                     cache = false;
    private static ClassLocal<ClassOptions<?>> clOptions;
    static {
        clOptions = new ClassLocal<ClassOptions<?>>();
    }

    @SuppressWarnings("unchecked")
    public static <T> ClassOptions<T> getClassOptions(Class<T> clazz) {
        ClassOptions<T> copt = (ClassOptions<T>) clOptions.get(clazz);
        if (copt == null) {
            copt = new ClassOptions<T>(clazz);
            if (cache)
                clOptions.put(clazz, copt);
        }
        return copt;
    }

    public static String[] loadOptions(Object classobj, String[] args) throws CLIException {
        List<String> list = new ArrayList<String>(args.length);
        for (String arg : args)
            list.add(arg);
        loadOptions(classobj, list);
        return list.toArray(new String[0]);
    }

    @SuppressWarnings("unchecked")
    public static void loadOptions(Object classobj, List<String> args) throws CLIException {
        assert classobj != null;
        Class<Object> clazz = (Class<Object>) classobj.getClass();
        ClassOptions<Object> copt = getClassOptions(clazz);
        try {
            copt.load(classobj, args);
        } catch (ParseException e) {
            throw new CLIException(e.getMessage(), e);
        }
    }

    protected static abstract class OptionFormat extends Filt1<String, Set<_Option<?>>> {

        @Override
        public abstract String filter(Set<_Option<?>> a);

    }

    @SuppressWarnings("unchecked")
    public static String helpOptions(Class<?> clazz, String restSyntax, final int tabsize,
            final int docColumn) throws CLIException {
        final ClassOptions<Object> copt = (ClassOptions<Object>) getClassOptions(clazz);
        TreeMap<String, _Option<?>> options = copt.getOptions();
        StringBuffer buffer = new StringBuffer(options.size() * 80);
        final char[] tab = new char[tabsize];
        Arrays.fill(tab, ' ');

        String program = A_bas.getProgramName(clazz);
        buffer.append(AppNLS.getString("ClassCLI.help.syntax")); //$NON-NLS-1$
        buffer.append(tab);
        buffer.append(program + AppNLS.getString("ClassCLI.help.option_")); //$NON-NLS-1$
        if (copt.specfiles != null) {
            int ifile = 0;
            while (true) {
                _Option<?> fopt = copt.specfiles.get(ifile++);
                if (fopt == null)
                    break;
                String fnam = fopt.getCLIName();
                buffer.append(' ');
                buffer.append(fnam);
            }
        }

        buffer.append(" "); //$NON-NLS-1$
        if (restSyntax == null) {
            boolean usingRestSyntax = false;
            if (usingRestSyntax) {
                // getRestSyntax();
                Method restf = Members.findDeclaredMethod(clazz, "getRestSyntax"); //$NON-NLS-1$
                if (restf == null)
                    buffer.append("..."); //$NON-NLS-1$
                else {
                    restSyntax = (String) Reflects.invoke(null, restf);
                    buffer.append(restSyntax);
                }
            } else {
                Option appopt = Ns.getN(clazz, Option.class);
                restSyntax = appopt.vnam();
                buffer.append(restSyntax);
            }
        }
        buffer.append(restSyntax);
        buffer.append("\n"); //$NON-NLS-1$

        buffer.append("\n"); //$NON-NLS-1$

        Map<String, Set<_Option<?>>> groups = new HashMap<String, Set<_Option<?>>>();
        Comparator<_Option<?>> optnamsort = new Comparator<_Option<?>>() {
            @Override
            public int compare(_Option<?> a, _Option<?> b) {
                return a.getCLIName().compareTo(b.getCLIName());
            }
        };
        for (Map.Entry<String, _Option<?>> entry : options.entrySet()) {
            _Option<?> opt = entry.getValue();
            if (opt.o.hidden())
                continue;
            String optnam = opt.getCLIName();
            if (!optnam.equals(entry.getKey()))
                continue;
            String groupName = opt.getGroup();
            if (groupName == null)
                groupName = program;
            Set<_Option<?>> group = groups.get(groupName);
            if (group == null)
                groups.put(groupName, //
                        group = new TreeSet<_Option<?>>(optnamsort));
            group.add(opt);
        }

        OptionFormat groupfmt = new OptionFormat() {
            @Override
            public String filter(Set<_Option<?>> opts) {
                StringBuffer buffer = new StringBuffer(opts.size() * 80);
                StringBuffer line = new StringBuffer(80);
                for (_Option<?> opt : opts) {
                    String[] aliases = copt.getAliases(opt);
                    Arrays.sort(aliases, Comparators.STRLEN);

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
                            line.append("--" + nam); //$NON-NLS-1$
                            col += 3 + nam.length();
                        }
                    }
                    if (!hasshort)
                        line.insert(tabsize, tab);
                    String vnam = opt.o.vnam();
                    if (!vnam.isEmpty()) {
                        if (aliases[aliases.length - 1].length() > 1)
                            line.append('=');
                        line.append(vnam);
                    }

                    String doc = opt.o.doc();

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
        for (Map.Entry<String, Set<_Option<?>>> group : groups.entrySet()) {
            if (first)
                first = false;
            else
                buffer.append("\n"); //$NON-NLS-1$
            String name = group.getKey();
            Set<_Option<?>> grpopts = group.getValue();
            buffer.append(Strings.ucfirst(name) + AppNLS.getString("ClassCLI.help.options")); //$NON-NLS-1$
            buffer.append(groupfmt.filter(grpopts));
        }
        return buffer.toString();
    }

}
