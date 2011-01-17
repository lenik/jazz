package net.bodz.bas.cli;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.script.ScriptException;

import net.bodz.bas.collection.preorder.PrefixMap;
import net.bodz.bas.collection.util.IterableToList;
import net.bodz.bas.meta.program.Option;
import net.bodz.bas.meta.program.OptionGroup;
import net.bodz.bas.util.Pair;
import net.bodz.bas.util.exception.ParseException;
import net.bodz.bas.valtype.util.TypeChain;

public class ClassOptions<CT> {

    private PrefixMap<_Option<?>> all;
    private Set<String> weaks;
    public TreeMap<Integer, _Option<?>> specfiles;
    private HashSet<_Option<?>> required;

    public ClassOptions(Class<CT> clazz) {
        all = new PrefixMap<_Option<?>>();
        weaks = new HashSet<String>();

        BeanInfo beanInfo;
        try {
            beanInfo = Introspector.getBeanInfo(clazz);
        } catch (IntrospectionException e) {
            throw new CLIError(e.getMessage(), e);
        }

        for (Class<?> c : TypeChain.listSuperFirst(clazz)) {
            if (c == Object.class) // opt for speed
                continue;
            OptionGroup optgrp = c.getAnnotation(OptionGroup.class);
            importFields(optgrp, c.getDeclaredFields());
            importMethods(optgrp, c.getDeclaredMethods());
        }

        // bean properties overwrite fields
        importProperties(beanInfo.getPropertyDescriptors());

        // importMethods(optgrp, beanInfo.getMethodDescriptors());
    }

    protected void importFields(OptionGroup optgrp, Field... fields) {
        for (final Field field : fields) {
            if (!field.isAnnotationPresent(Option.class))
                continue;
            FieldOption<Object> fieldopt = new FieldOption<Object>(//
                    field.getName(), field, optgrp);
            addOption(fieldopt);
        }
    }

    /**
     * Only annotations on read method is used.
     */
    protected void importProperties(PropertyDescriptor... properties) {
        for (final PropertyDescriptor property : properties) {
            Method readf = property.getReadMethod();
            if (readf == null)
                continue;
            if (!readf.isAnnotationPresent(Option.class))
                continue;
            OptionGroup optgrp = readf.getDeclaringClass().getAnnotation(OptionGroup.class);
            PropertyOption<Object> propopt = new PropertyOption<Object>( //
                    property.getName(), property, optgrp);
            addOption(propopt);
        }
    }

    protected void importMethod(OptionGroup optgrp, final Method method) {
        if (!method.isAnnotationPresent(Option.class))
            return;
        _Option<CallInfo> copt = new MethodOption(//
                method.getName(), method, optgrp);
        addOption(copt);
    }

    protected void importMethods(OptionGroup optgrp, Method... methods) {
        for (Method method : methods)
            importMethod(optgrp, method);
    }

    protected void importMethods(OptionGroup optgrp, MethodDescriptor... methods) {
        for (MethodDescriptor _method : methods)
            importMethod(optgrp, _method.getMethod());
    }

    protected void addOption(_Option<?> newopt) {
        String optnam = newopt.getCLIName();
        _Option<?> existing = all.get(optnam);
        if (existing != null && !existing.isWeak())
            throw new IllegalArgumentException("option name " + optnam + " is already existed");
        all.put(optnam, newopt);
        for (String newalias : newopt.o.alias()) {
            boolean newweak = newalias.startsWith(".");
            if (newweak)
                newalias = newalias.substring(1);
            newweak |= newopt.isWeak();

            boolean aliasExisted = all.containsKey(newalias) && !weaks.contains(newalias);
            if (aliasExisted)
                throw new IllegalArgumentException("option alias " + newalias + " is already existed");
            all.put(newalias, newopt);
            if (newweak)
                weaks.add(newalias);
            else
                weaks.remove(newalias);
        }
        if (newopt.o.required()) {
            if (required == null)
                required = new HashSet<_Option<?>>();
            required.add(newopt);
        }
        int index = newopt.o.fileIndex();
        if (index != -1) {
            if (specfiles == null)
                specfiles = new TreeMap<Integer, _Option<?>>();
            specfiles.put(index, newopt);
        }
    }

    public TreeMap<String, _Option<?>> getOptions() {
        return all;
    }

    /**
     * @param name
     *            explicit option name
     * @return null if option does not exist
     */
    public _Option<?> getOption(String name) {
        return all.get(name);
    }

    /**
     * @param possible
     *            prefix of option name
     * @exception CLIException
     *                if option does not exist
     */
    @SuppressWarnings("unchecked")
    public _Option<Object> findOption(String name)
            throws CLIException {
        if (name.isEmpty())
            throw new CLIException("option name is empty");
        if (name.startsWith("no-"))
            name = name.substring(3);
        if (all.containsKey(name))
            return (_Option<Object>) all.get(name);
        List<String> fullnames = IterableToList.toList(all.ceilingKeys(name));
        if (fullnames.isEmpty())
            throw new CLIException("no such option: " + name);
        if (fullnames.size() > 1) {
            StringBuffer cands = new StringBuffer();
            for (String nam : fullnames) {
                cands.append(nam);
                cands.append('\n');
                nam = all.higherKey(nam);
                if (nam == null || !nam.startsWith(name))
                    break;
            }
            throw new CLIException("ambiguous option " + name + ": \n" + cands.toString());
        }
        String fullname = fullnames.get(0);
        return (_Option<Object>) all.get(fullname);
    }

    private static final String[] String_0 = {};

    public String[] getAliases(_Option<?> opt) {
        List<String> aliases = new ArrayList<String>();
        for (Entry<String, _Option<?>> e : all.entrySet()) {
            if (e.getValue() != opt)
                continue;
            String alias = e.getKey();
            aliases.add(alias);
        }
        return aliases.toArray(String_0);
    }

    /**
     * @param name
     *            (H-)name or alias.
     * @return <code>true</code> if specified name or alias hasn't been used, or it's a weak name.
     * @see Option#name()
     * @see Option#alias()
     */
    public boolean isReusable(String name) {
        if (!all.containsKey(name))
            return true;
        if (weaks.contains(name))
            return true;
        return false;
    }

    public String[] load(CT classobj, String... args)
            throws CLIException, ParseException {
        List<String> list = new ArrayList<String>(args.length);
        for (String arg : args)
            list.add(arg);
        load(classobj, list);
        return list.toArray(new String[0]);
    }

    public void load(CT classobj, List<String> args)
            throws CLIException, ParseException {
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
                    opt = findOption(optnam);
                } else if (optnam.startsWith("-")) {
                    String chr = optnam.substring(1, 2);
                    opt = findOption(chr);
                    if (opt.getParameterCount() == 0 || !opt.o.optional().isEmpty()) {
                        // boolean option, or option has a default value,
                        if (optnam.length() > 2)
                            // remove the shortopt from --abcdefg => -bcdefg
                            args.set(i, "-" + optnam.substring(2));
                        else
                            // remove the shortopt entirely
                            args.remove(i);
                    } else {
                        // option expects a value
                        if (optnam.length() > 2)
                            // -aVALUE => VALUE
                            args.set(i, optnam.substring(2));
                        else
                            // {-a, VALUE} => {VALUE}
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
                optval = _parseOptVal(opt, optarg);
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
        _checkMissings(missing);
    }

    @SuppressWarnings("unchecked")
    public void load(CT classobj, Map<String, ?> argmap)
            throws CLIException, ParseException {
        Set<?> missing = required == null ? null : (Set<?>) required.clone();

        for (Map.Entry<String, ?> entry : argmap.entrySet()) {
            String optnam = entry.getKey();
            _Option<?> opt = getOption(optnam);
            if (opt == null)
                continue;
            // argmap.remove(optnam)
            if (missing != null && opt.o.required())
                missing.remove(opt);

            Object optval = entry.getValue();
            if (opt instanceof MethodOption) {
                String arg = String.valueOf(optval);
                String[] cargs = arg.split(",");
                loadCall(classobj, (MethodOption) opt, Arrays.asList(cargs), 0);
            } else if (opt.getParameterCount() > 0) {
                // assert opt.getParameterCount() == 1;
                if (optval instanceof String)
                    optval = _parseOptVal(opt, (String) optval);
            }

            if (optval != null)
                try {
                    _Option<Object> _opt = (_Option<Object>) opt;
                    _opt.set(classobj, optval);
                } catch (ScriptException e) {
                    throw new CLIException(e.getMessage(), e);
                }
        }
        _checkMissings(missing);
    }

    private Object _parseOptVal(_Option<?> opt, String optarg)
            throws ParseException {
        Class<?> valtype = opt.getType();
        Object optval = null;

        if (optarg == null) {
            optarg = opt.o.optional();
            if (optarg.isEmpty())
                throw new ParseException("Option value expected: " + opt.getCLIName());
        }

        String key = null;
        if (opt.isMap()) {
            int eq = optarg.indexOf('=');
            if (eq == -1) {
                key = optarg;
                optarg = null;
            } else {
                key = optarg.substring(0, eq);
                optarg = optarg.substring(eq + 1);
            }
        }

        if (optarg == null)
            optval = Util.getTrueValue(valtype);
        if (optval == null)
            try {
                optval = opt.parse(optarg, key);
            } catch (ParseException e) {
                throw new ParseException(String.format("Can\'t parse option %s of %s with argument %s", opt
                        .getCLIName(), valtype, optarg), e);
            }
        if (key != null)
            optval = new Pair<String, Object>(key, optval);
        return optval;
    }

    private static void _checkMissings(Set<?> missing)
            throws CLIException {
        if (missing != null && !missing.isEmpty()) {
            StringBuffer buf = new StringBuffer(missing.size() * 20);
            for (Object m : missing) {
                _Option<?> mopt = (_Option<?>) m;
                buf.append("    " + mopt.getCLIName() + "\n");
            }
            throw new CLIException("missing required option(s): \n" + buf);
        }
    }

    private int loadCall(CT object, MethodOption optcall, List<String> args, int off)
            throws CLIException, ParseException {
        int argc = optcall.getParameterCount();
        int rest = args.size() - off;
        if (argc > rest)
            throw new CLIException("not enough parameters for function-option " + optcall.getCLIName());
        String[] argv = args.subList(off, off + argc).toArray(new String[0]);
        try {
            optcall.call(object, argv);
        } catch (ScriptException e) {
            throw new CLIException(e.getMessage(), e);
        }
        return argc;
    }

}
