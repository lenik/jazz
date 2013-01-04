package net.bodz.bas.program.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.c.type.addor.IAddor;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.program.skel.CLISyntaxException;
import net.bodz.mda.xjdoc.model.javadoc.SemiMutableXjdocElement;

/**
 * Though {@link AbstractOptionGroup} is-a {@link Serializable}, the serialization on this object
 * (of any subclass) won't work because of the underlying transient fields.
 */
public abstract class AbstractOptionGroup
        extends SemiMutableXjdocElement
        implements IOptionGroup {

    private static final long serialVersionUID = 1L;

    /**
     * Get the explicitly declared local option.
     * 
     * @param optionKey
     *            Explicitly declared local option key.
     * @return <code>null</code> if the local option isn't existed.
     */
    protected abstract IOption getLocalOption(String optionKey);

    @Override
    public IOption getOption(String optionKey) {
        IOption option = getLocalOption(optionKey);
        if (option != null)
            return option;

        IOptionGroup parent = getParent();
        if (parent == null)
            return null;
        else
            return parent.getOption(optionKey);
    }

    @Override
    public IOption getUniqueOption(String prefix)
            throws AmbiguousOptionKeyException {
        if (prefix == null)
            throw new NullPointerException("optionKeyPrefix");
        if (prefix.startsWith("no-"))
            prefix = prefix.substring(3);
        if (prefix.isEmpty())
            throw new IllegalArgumentException("prefix is empty");

        IOption option = getOption(prefix);
        if (option != null)
            return option;

        List<String> optionKeys = getSuggestKeys(prefix);
        if (optionKeys.isEmpty())
            return null;

        if (optionKeys.size() > 1) {
            StringBuilder suggestions = new StringBuilder();
            for (String key : optionKeys) {
                suggestions.append(key);
                suggestions.append('\n');
            }
            throw new AmbiguousOptionKeyException(prefix, suggestions.toString());
        }
        String optionKey = optionKeys.get(0);
        return getLocalOption(optionKey);
    }

    public List<String> getSuggestKeys(String optionKeyPrefix) {
        List<String> suggestKeys = new ArrayList<String>();
        fillSuggestKeys(optionKeyPrefix, suggestKeys);
        return suggestKeys;
    }

    public Map<String, IOption> getSuggestMap(String prefix) {
        Map<String, IOption> map = new LinkedHashMap<String, IOption>();
        fillSuggestMap(prefix, map);
        return map;
    }

    public Set<String> getEnabledKeys(IOption option) {
        Set<String> enabledKeys = new LinkedHashSet<String>();
        fillEnabledKeys(option, enabledKeys);
        return enabledKeys;
    }

    public Set<String> getUsageIds() {
        Set<String> usageIds = new LinkedHashSet<String>();
        fillUsageIds(usageIds);
        return usageIds;
    }

    @Override
    public List<String> receive(Object context, String[] args, OptionGroupParseFlags flags)
            throws CLISyntaxException {
        List<String> rejected = new ArrayList<String>();

        for (int argIndex = 0; argIndex < args.length; argIndex++) {
            String arg = args[argIndex];
            IOption option = null;
            String argArg = null;
            Object argValue = null;

            if (arg.startsWith("--")) { // long-option
                arg = arg.substring(2);

                if (arg.isEmpty()) { // [OPTIONS} [--] ...
                    while (++argIndex < args.length)
                        rejected.add(args[argIndex]);
                    break;
                }

                int eq = arg.indexOf('=');
                if (eq != -1) {
                    argArg = arg.substring(eq + 1);
                    arg = arg.substring(0, eq);
                }

                option = this.getUniqueOption(arg);

                if (option == null) {
                    if (arg.startsWith("no-")) {
                        option = this.getUniqueOption(arg.substring(3));
                        if (option != null)
                            argValue = false;
                    }
                    if (option == null)
                        throw new NoSuchOptionException(arg);
                }

            } else if (arg.startsWith("-") && arg.length() > 1) { // short-options
                arg = arg.substring(1);
                int argLen = arg.length();

                for (int j = 0; j < argLen; j++) {
                    String shortKey = arg.substring(j, j + 1);
                    IOption shortOption = this.getOption(shortKey);

                    if (shortOption == null)
                        throw new NoSuchOptionException(shortKey);

                    if (shortOption.getParameterCount() == 0) {
                        Object trueValue = shortOption.getDefaultValue();
                        argValue = trueValue;
                        receiveOptionValue(context, shortOption, argValue);
                        continue;
                    } else {
                        option = shortOption; // The remaining chars are used as opt arg..
                        argArg = arg.substring(j + 1);
                        break;
                    }
                }
                if (option == null)
                    continue;

            } else {
                if (flags.isStopAtFirstNonOption()) {
                    for (int j = argIndex; j < args.length; j++)
                        rejected.add(args[j]);
                    break;
                } else {
                    rejected.add(arg);
                    continue;
                }
            }

            Class<?> valueType = option.getValueType();

            if (argValue == null)
                if (valueType == Boolean.class)
                    argValue = true;

            int parameterCount = option.getParameterCount();
            String[] parameters = new String[parameterCount];
            int n = 0;

            if (argArg != null) {
                if (parameterCount == 0)
                    throw new CLISyntaxException(String.format("Unexpected parameter for option %s: %s", //
                            argArg, option.getName()));
                parameters[n++] = argArg;
            }

            if (args.length - (argIndex + 1) < parameterCount - n) {
                throw new CLISyntaxException(String.format("Option %s expects %d parameters, but only %d given.",
                        option.getName(), parameterCount, args.length - (argIndex + 1)));
            }
            while (n < parameterCount) {
                parameters[n++] = args[++argIndex];
            }

            if (parameterCount > 0) {
                try {
                    argValue = option.parseValue(context, parameters);
                } catch (ParseException e) {
                    throw new ParseOptionException(option, parameters, e);
                }
                receiveOptionValue(context, option, argValue);
            }
        }
        return rejected;
    }

    static void receiveOptionValue(Object obj, IOption option, Object value) {
        IProperty property = option.property();
        IAddor addor = option.getAddor();
        if (addor == null)
            throw new NullPointerException("addor");

        Object prev;
        try {
            prev = property.getValue(obj);
        } catch (ReflectiveOperationException e1) {
            throw new RuntimeException(e1.getMessage(), e1);
        }

        Object result = addor.add(prev, value);
        if (result == null)
            throw new NullPointerException("result");

        try {
            property.setValue(obj, result);
        } catch (ReflectiveOperationException e) {
            throw new IllegalUsageException(String.format(//
                    "Can't set property %s.%s to %s", //
                    property.getDeclaringClass().getSimpleName(), property.getName(), //
                    result), e);
        }
    }

}
