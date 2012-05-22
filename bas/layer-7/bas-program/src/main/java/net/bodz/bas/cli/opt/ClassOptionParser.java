package net.bodz.bas.cli.opt;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.c.type.TypeChain;
import net.bodz.bas.cli.CLIError;
import net.bodz.bas.cli.CLIException;
import net.bodz.bas.err.ParseException;

public class ClassOptionParser {

    void f() {
        BeanInfo beanInfo;
        try {
            beanInfo = Introspector.getBeanInfo(clazz);
        } catch (IntrospectionException e) {
            throw new CLIError(e.getMessage(), e);
        }

        for (Class<?> c : TypeChain.listSuperFirst(clazz)) {
            if (c == Object.class) // opt for speed
                continue;
            OptionGroup group = c.getAnnotation(OptionGroup.class);
            importFields(group, c.getDeclaredFields());
            importMethods(group, c.getDeclaredMethods());
        }

        // bean properties overwrite fields
        importProperties(beanInfo.getPropertyDescriptors());

        // importMethods(group, beanInfo.getMethodDescriptors());
    }

    protected void importFields(OptionGroup group, Field... fields) {
        for (final Field field : fields) {
            // XXX if (!field.isAnnotationPresent(Option.class))
            // continue;
            FieldOption fieldopt = new FieldOption(field);
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
            // XXX if (!readf.isAnnotationPresent(Option.class))
            // continue;
            OptionGroup group = readf.getDeclaringClass().getAnnotation(OptionGroup.class);
            PropertyOption propopt = new PropertyOption(property);
            addOption(propopt);
        }
    }

    protected void importMethod(OptionGroup group, final Method method) {
        // XXX if (!method.isAnnotationPresent(Option.class))
        // return;
        AbstractOption copt = new MethodOption(method);
        addOption(copt);
    }

    protected void importMethods(OptionGroup group, Method... methods) {
        for (Method method : methods)
            importMethod(group, method);
    }

    protected void importMethods(OptionGroup group, MethodDescriptor... methods) {
        for (MethodDescriptor _method : methods)
            importMethod(group, _method.getMethod());
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
            AbstractOption opt = null;
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
                    if (opt.getParameterCount() == 0 || !opt.defaultVal.isEmpty()) {
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

            if (missing != null && opt.required)
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
                    AbstractOption _opt = (AbstractOption) opt;
                    _opt.set(classobj, optval);
                } catch (ReflectiveOperationException e) {
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
            AbstractOption opt = getOption(optnam);
            if (opt == null)
                continue;
            // argmap.remove(optnam)
            if (missing != null && opt.required)
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
                    AbstractOption _opt = (AbstractOption) opt;
                    _opt.set(classobj, optval);
                } catch (ReflectiveOperationException e) {
                    throw new CLIException(e.getMessage(), e);
                }
        }
        _checkMissings(missing);
    }

    private static void _checkMissings(Set<?> missing)
            throws CLIException {
        if (missing != null && !missing.isEmpty()) {
            StringBuilder buf = new StringBuilder(missing.size() * 20);
            for (Object m : missing) {
                AbstractOption mopt = (AbstractOption) m;
                buf.append("    " + mopt.getFriendlyName() + "\n");
            }
            throw new CLIException("missing required option(s): \n" + buf);
        }
    }

}
