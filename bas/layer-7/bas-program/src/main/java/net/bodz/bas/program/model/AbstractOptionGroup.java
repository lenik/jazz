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
        implements
            IOptionGroup {

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
        _Parser parser = new _Parser(flags, args);

        while (parser.next()) {
            IOption option = parser.option;
            Object optValue = parser.optValue;
            if (option.getParameterCount() > 0) {
                String[] parameters = parser.optArgs.toArray(new String[0]);
                try {
                    optValue = option.parseValue(context, parameters);
                } catch (ParseException e) {
                    throw new ParseOptionException(option, parameters, e);
                }
            }

            receiveOptionValue(context, option, optValue);
        }
        return parser.rejected;
    }

    static void receiveOptionValue(Object obj, IOption option, Object value) {
        IProperty property = option.property();
        IAddor addor = option.getAddor();
        if (addor == null)
            throw new NullPointerException("addor");

        Object result = value;

        Object prev;
        if (property.isReadable()) {
            try {
                prev = property.getValue(obj);
            } catch (ReflectiveOperationException e1) {
                throw new RuntimeException(e1.getMessage(), e1);
            }
            result = addor.add(prev, value);
        }

        try {
            property.setValue(obj, result);
        } catch (ReflectiveOperationException e) {
            throw new IllegalUsageException(String.format(//
                    "Can't set property %s.%s to %s", //
                    property.getDeclaringClass().getSimpleName(), property.getName(), //
                    result), e);
        }
    }

    class _Parser {

        static final int START = 0;
        static final int UNPACKING = 1;
        static final int SHIFT_ARGS = 2;
        static final int STOPPED = 3;

        private int state = START;

        private OptionGroupParseFlags flags;

        private String[] args;
        private int argIndex;
        private String packed;

        private IOption option = null;
        private List<String> optArgs = new ArrayList<String>();
        private Object optValue = null;

        private List<String> rejected = new ArrayList<String>();

        public _Parser(OptionGroupParseFlags flags, String[] args) {
            this.flags = flags;
            this.args = args;
        }

        public boolean next()
                throws CLISyntaxException {
            option = null;
            optArgs.clear();
            optValue = null;

            while (true)
                switch (state) {
                case START:
                    if (argIndex == args.length)
                        return false;

                    String arg = args[argIndex++];

                    // long-option
                    if (arg.startsWith("--")) {
                        arg = arg.substring(2);

                        int eq = arg.indexOf('=');
                        if (eq != -1) {
                            optArgs.add(arg.substring(eq + 1));
                            arg = arg.substring(0, eq);
                        }

                        option = getUniqueOption(arg);

                        if (option == null) {
                            if (arg.startsWith("no-")) {
                                option = getUniqueOption(arg.substring(3));
                                if (option != null)
                                    optValue = false;
                            }
                            if (option == null)
                                throw new NoSuchOptionException(arg);
                        }

                        state = SHIFT_ARGS;
                        continue;
                    }

                    // short-option[s]
                    if (arg.startsWith("-") && arg.length() > 1) {
                        packed = arg.substring(1);
                        state = UNPACKING;
                        continue;
                    }

                    // non-option
                    if (flags.isStopAtFirstNonOption())
                        rejected.add(arg);
                    else
                        state = STOPPED;
                    continue;

                case UNPACKING:
                    if (packed.isEmpty()) {
                        state = START;
                        continue;
                    }

                    String shortKey = packed.substring(0, 1);
                    packed = packed.substring(1);

                    option = getOption(shortKey);
                    if (option == null)
                        throw new NoSuchOptionException(shortKey);

                    if (option.getParameterCount() == 0) {
                        Object trueValue = option.getDefaultValue();
                        optValue = trueValue;
                        return true;
                    }

                    if (!packed.isEmpty()) {
                        // The remaining chars are used as opt arg..
                        optArgs.add(packed);
                    }

                    // The next args (if exist) are used as opt arg.
                    state = SHIFT_ARGS;
                    continue;

                case SHIFT_ARGS:
                    if (optValue == null)
                        optValue = option.getDefaultValue();

                    int parameterCount = option.getParameterCount();
                    int shiftCount = parameterCount - optArgs.size();
                    int available = args.length - argIndex;

                    if (available < shiftCount) {
                        throw new CLISyntaxException(
                                String.format("Option %s expects %d parameters, but only %d given.", //
                                        option.getName(), parameterCount, optArgs.size() + available));
                    }

                    for (int i = 0; i < shiftCount; i++)
                        optArgs.add(args[argIndex++]);

                    state = START;
                    return true;

                case STOPPED:
                    while (argIndex < args.length)
                        rejected.add(args[argIndex++]);
                    return false;
                }
        }

    } // Local parser class

}
