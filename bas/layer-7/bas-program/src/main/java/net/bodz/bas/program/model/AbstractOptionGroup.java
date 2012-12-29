package net.bodz.bas.program.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

                option = this.getUniqueOption(arg);

                boolean maybeNegative = arg.startsWith("no-");
                if (option == null && maybeNegative) {
                    option = this.getUniqueOption(arg.substring(3));
                    argValue = false;
                }

            } else if (arg.startsWith("-")) {
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
                if (flags.isStopAtFirstNonOption()) {
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

}
