package net.bodz.bas.cli;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.lang.script.ScriptException;
import net.bodz.bas.types.Pair;
import net.bodz.bas.types.util.Types;

public class ClassOptions<CT> {

    private TreeMap<String, _Option<?>> options;
    public TreeMap<Integer, _Option<?>> specfiles;
    private HashSet<_Option<?>>         required;

    public ClassOptions(Class<CT> clazz) {
        options = new TreeMap<String, _Option<?>>();

        BeanInfo beanInfo;
        try {
            beanInfo = Introspector.getBeanInfo(clazz);
        } catch (IntrospectionException e) {
            throw new CLIError(e.getMessage(), e);
        }

        Option optfull = clazz.getAnnotation(Option.class);

        for (Class<?> c : Types.getTypeChain(clazz, true)) {
            if (c == Object.class) // opt for speed
                continue;
            OptionGroup optgrp = c.getAnnotation(OptionGroup.class);
            importFields(optgrp, optfull, c.getDeclaredFields());
            importMethods(optgrp, optfull, c.getDeclaredMethods());
        }

        // bean properties overwrite fields
        importProperties(optfull, beanInfo.getPropertyDescriptors());

        // importMethods(optgrp, optfull, beanInfo.getMethodDescriptors());
    }

    protected void importFields(OptionGroup optgrp, Option optf,
            Field... fields) {
        for (final Field field : fields) {
            Option opt = field.getAnnotation(Option.class);
            if (opt == null)
                opt = optf;
            if (opt == null)
                continue;

            FieldOption<Object> fieldopt = new FieldOption<Object>(field
                    .getName(), opt, field, optgrp);
            addOption(fieldopt);
        }
    }

    protected void importProperties(Option optf,
            PropertyDescriptor... properties) {
        for (final PropertyDescriptor property : properties) {
            Method write = property.getWriteMethod();
            if (write == null)
                continue;

            Method read = property.getReadMethod();
            Option opt = read.getAnnotation(Option.class);
            Option optw = write.getAnnotation(Option.class);
            if (optw != null)
                opt = optw;
            if (opt == null)
                opt = optf;
            if (opt == null)
                continue;

            OptionGroup optgrp = read.getDeclaringClass().getAnnotation(
                    OptionGroup.class);
            PropertyOption<Object> propopt = new PropertyOption<Object>(
                    property.getName(), opt, property, optgrp);
            addOption(propopt);
        }
    }

    protected void importMethod(OptionGroup optgrp, Option optf,
            final Method method) {
        Option opt = method.getAnnotation(Option.class);
        if (opt == null)
            opt = optf;
        if (opt == null)
            return;
        _Option<CallInfo> copt = new MethodOption(method.getName(), opt,
                method, optgrp);
        addOption(copt);
    }

    protected void importMethods(OptionGroup optgrp, Option optf,
            Method... methods) {
        for (Method method : methods)
            importMethod(optgrp, optf, method);
    }

    protected void importMethods(OptionGroup optgrp, Option optf,
            MethodDescriptor... methods) {
        for (MethodDescriptor _method : methods)
            importMethod(optgrp, optf, _method.getMethod());
    }

    protected void addOption(_Option<?> copt) {
        String optnam = copt.getCanonicalName();
        _Option<?> prev = options.get(optnam);
        if (prev != null && prev.o._final())
            throw new IllegalArgumentException("option name " + optnam
                    + " is already existed");
        options.put(optnam, copt);
        for (String alias : copt.o.alias()) {
            prev = options.get(alias);
            if (prev != null && prev.o._final())
                throw new IllegalArgumentException("option alias " + alias
                        + " is already existed");
            options.put(alias, copt);
        }
        if (copt.o.required()) {
            if (required == null)
                required = new HashSet<_Option<?>>();
            required.add(copt);
        }
        int index = copt.o.fileIndex();
        if (index != -1) {
            if (specfiles == null)
                specfiles = new TreeMap<Integer, _Option<?>>();
            specfiles.put(index, copt);
        }
    }

    public TreeMap<String, _Option<?>> getOptions() {
        return options;
    }

    public _Option<?> getOption(String name) throws CLIException {
        if (name.isEmpty())
            throw new CLIException("option name is empty");
        if (name.startsWith("no-"))
            name = name.substring(3);
        Entry<String, _Option<?>> entry = options.ceilingEntry(name);
        if (entry == null)
            throw new CLIException("no such option: " + name);
        String nam = entry.getKey();
        if (!nam.startsWith(name))
            throw new CLIException("no such option: " + name);
        if (!nam.equals(name)) {
            String nam2 = options.higherKey(nam);
            if (nam2.startsWith(name)) {
                StringBuffer cands = new StringBuffer();
                while (nam != null) {
                    cands.append(nam);
                    cands.append('\n');
                    nam = options.higherKey(nam);
                    if (nam == null || !nam.startsWith(name))
                        break;
                }
                throw new CLIException("ambiguous option " + name + ": \n"
                        + cands.toString());
            }
        }
        return entry.getValue();
    }

    public String[] load(CT classobj, String... args) throws CLIException,
            ParseException {
        List<String> list = new ArrayList<String>(args.length);
        for (String arg : args)
            list.add(arg);
        load(classobj, list);
        return list.toArray(new String[0]);
    }

    public void load(CT classobj, List<String> args) throws CLIException,
            ParseException {
        Set<?> missing = required == null ? null : (Set<?>) required.clone();

        boolean optionsEnd = false;

        int fileIndex = 0;
        for (int i = 0; i < args.size();) {
            String optnam = args.get(i);
            _Option<?> opt = null;
            if (!optionsEnd) {
                if ("--".equals(optnam)) {
                    optionsEnd = true;
                    args.remove(i);
                    continue;
                }
                if (optnam.startsWith("--")) {
                    optnam = optnam.substring(2);
                    int eq = optnam.indexOf('=');
                    if (eq != -1) {
                        args.set(i, optnam.substring(eq + 1));
                        optnam = optnam.substring(0, eq);
                    } else {
                        args.remove(i);
                    }
                    opt = getOption(optnam);
                } else if (optnam.startsWith("-")) {
                    String chr = optnam.substring(1, 2);
                    opt = getOption(chr);
                    if (opt.getParameterCount() == 0
                            || !opt.o.optional().isEmpty()) {
                        if (optnam.length() > 2)
                            args.set(i, "-" + optnam.substring(2));
                        else
                            args.remove(i);
                    } else {
                        if (optnam.length() > 2)
                            args.set(i, optnam.substring(2));
                        else
                            args.remove(i);
                    }
                    optnam = chr;
                }
            }
            if (opt == null) {
                if (specfiles != null)
                    opt = specfiles.get(fileIndex);
                fileIndex++;
            }
            if (opt == null) {
                i++;
                continue;
            }

            if (missing != null && opt.o.required())
                missing.remove(opt);

            int usedArgs = 0;
            Class<?> valtype = opt.getType();
            Object optval = null;

            if (opt instanceof MethodOption) {
                usedArgs = loadCall(classobj, (MethodOption) opt, args, i);
            } else if (opt.isBoolean()) {
                optval = !optnam.startsWith("no-");
            } else if (opt.getParameterCount() > 0) {
                // assert opt.getParameterCount() == 1;
                String optarg = null;
                if (optarg == null && i < args.size())
                    if (optionsEnd || !args.get(i).startsWith("-")) {
                        usedArgs++;
                        optarg = args.get(i);
                    }
                if (optarg == null)
                    optarg = opt.o.optional();

                String key = null;
                if (opt.isMap()) {
                    int eq = optarg.indexOf('=');
                    if (eq == -1) {
                        key = optarg;
                        optarg = "";
                    } else {
                        key = optarg.substring(0, eq);
                        optarg = optarg.substring(eq + 1);
                    }
                }

                if (optarg == null || optarg.isEmpty())
                    optval = Util.getTrueValue(valtype);
                if (optval == null)
                    try {
                        optval = opt.parse(optarg);
                    } catch (ParseException e) {
                        throw new ParseException(
                                "Can't get default/true value for type "
                                        + valtype + "\n" + e.getMessage(), e);
                    }
                if (key != null)
                    optval = new Pair<String, Object>(key, optval);
            }

            if (optval != null)
                try {
                    @SuppressWarnings("unchecked")
                    _Option<Object> _opt = (_Option<Object>) opt;
                    _opt.set(classobj, optval);
                } catch (ScriptException e) {
                    throw new CLIException(e.getMessage(), e);
                }

            while (usedArgs-- > 0)
                args.remove(i);
        }

        if (missing != null && !missing.isEmpty()) {
            StringBuffer buf = new StringBuffer(missing.size() * 20);
            for (Object m : missing) {
                _Option<?> mopt = (_Option<?>) m;
                buf.append("    " + mopt.getCanonicalName() + "\n");
            }
            throw new CLIException("missing required option(s): \n" + buf);
        }
    }

    private int loadCall(CT object, MethodOption optcall, List<String> args,
            int off) throws CLIException, ParseException {
        int cargs = optcall.getParameterCount();
        int rest = args.size() - off;
        if (cargs > rest)
            throw new CLIException("not enough parameters for function-option "
                    + optcall.getCanonicalName());
        CallInfo call = new CallInfo(cargs);
        for (int i = 0; i < cargs; i++) {
            String arg = args.get(i);
            call.argv[i] = optcall.parseParameter(arg, i);
        }
        try {
            optcall.set(object, call);
        } catch (ScriptException e) {
            throw new CLIException(e.getMessage(), e);
        }
        return cargs;
    }

}
