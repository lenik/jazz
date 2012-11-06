package net.bodz.bas.cli.model;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.cli.skel.CLISyntaxException;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.potato.model.IProperty;

public class OptionApplier {

    final IOptionGroup optionGroup;
    Object context;
    boolean stopAtFirstNonOption;

    public OptionApplier(IOptionGroup optionGroup) {
        if (optionGroup == null)
            throw new NullPointerException("optionGroup");
        this.optionGroup = optionGroup;
    }

    public OptionApplier(IOptionGroup optionGroup, Object context) {
        this(optionGroup);
        this.context = context;
    }

    public List<String> apply(String... args)
            throws CLISyntaxException {
        List<String> rejected = new ArrayList<String>();

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            IOption option = null;
            String argArg = null;
            Object argValue = null;

            if (arg.startsWith("--")) { // long-option
                arg = arg.substring(2);

                int eq = arg.indexOf('=');
                argArg = eq == -1 ? null : arg.substring(eq + 1);
                if (eq != -1)
                    arg = arg.substring(0, eq);

                option = optionGroup.getUniqueOption(arg);

                boolean maybeNegative = arg.startsWith("no-");
                if (option == null && maybeNegative) {
                    option = optionGroup.getUniqueOption(arg.substring(3));
                    argValue = false;
                }

            } else if (arg.startsWith("-")) {
                arg = arg.substring(1);
                int argLen = arg.length();

                for (int j = 0; j < argLen; j++) {
                    String shortKey = arg.substring(j, j + 1);
                    IOption shortOption = optionGroup.getOption(shortKey);
                    if (shortOption == null)
                        throw new NoSuchOptionException(shortKey);

                    if (shortOption.getParameterCount() == 0) {
                        Object trueValue = shortOption.getDefaultValue();
                        argValue = trueValue;
                        setProperty(context, shortOption.property(), argValue);
                        continue;
                    } else {
                        option = shortOption; // The remaining chars are used as opt arg..
                        argArg = arg.substring(j + 1);
                        break;
                    }
                }
                if (option == null) // no shortopt needs an arg.
                    continue;

            } else {
                if (stopAtFirstNonOption) {
                    for (int j = i; j < args.length; j++)
                        rejected.add(args[j]);
                    break;
                } else {
                    rejected.add(arg);
                    continue;
                }
            }

            if (option.getType() == Boolean.class)
                if (argValue == null)
                    argValue = true;

            int parameterCount = option.getParameterCount();
            String[] parameters = new String[parameterCount];
            int n = 0;
            if (argArg != null)
                parameters[n++] = argArg;
            if (args.length - (i + 1) < parameterCount - n) {
                throw new CLISyntaxException(String.format("Option %s expects %d parameters, but only %d given.",
                        option.getName(), parameterCount, args.length - (i + 1)));
            }
            while (n < parameterCount) {
                parameters[n++] = args[++i];
            }

            if (n > 0) {
                try {
                    argValue = option.parseValue(context, parameters);
                } catch (ParseException e) {
                    throw new ParseOptionException(option, parameters, e);
                }
                setProperty(context, option.property(), argValue);
            }
        }
        return rejected;
    }

    static void setProperty(Object obj, IProperty property, Object value) {
        try {
            property.setValue(obj, value);
        } catch (ReflectiveOperationException e) {
            throw new IllegalUsageException(String.format(//
                    "Can't set property %s.%s to %s", //
                    property.getDeclaringClass().getSimpleName(), property.getName(), //
                    value), e);
        }
    }

    public Object getContext() {
        return context;
    }

    public void setContext(Object context) {
        this.context = context;
    }

    public boolean isStopAtFirstNonOption() {
        return stopAtFirstNonOption;
    }

    public void setStopAtFirstNonOption(boolean stopAtFirstNonOption) {
        this.stopAtFirstNonOption = stopAtFirstNonOption;
    }

}
