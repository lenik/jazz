package net.bodz.bas.cli.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.cli.skel.CLIException;
import net.bodz.bas.err.ParseException;

public class OptionLoader {

    public void load(Object instance, List<String> args)
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
