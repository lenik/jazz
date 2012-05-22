package net.bodz.bas.cli.opt;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.rmi.CORBA.Util;

import net.bodz.bas.cli.CLIException;
import net.bodz.bas.collection.preorder.PrefixMap;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.lang.negotiation.Option;
import net.bodz.bas.potato.traits.AbstractElement;
import net.bodz.bas.util.Pair;
import net.bodz.bas.util.iter.Iterables;

public class OptionGroup
        extends AbstractElement
        implements IOptionGroup {

    Map<String, IOption> nameMap = new TreeMap<String, IOption>();
    PrefixMap<IOption> prefixMap = new PrefixMap<IOption>();
    TreeMap<Integer, IOption> positionMap = new TreeMap<Integer, IOption>();

    public OptionGroup(OptionGroup parent, Class<?> declaringClass) {
        super(declaringClass, declaringClass.getSimpleName());
    }

    protected void addOption(IOption option) {
        String name = option.getName();
        IOption existing = nameMap.get(name);
        if (existing != null)
            throw new IllegalUsageException(String.format("Option name '%s' conflicts with %s: %s.", //
                    name, existing.getDisplayName(), existing.getDescription()));

        String friendlyName = option.getFriendlyName();
        existing = prefixMap.get(friendlyName);
        if (existing != null)
            throw new IllegalUsageException(String.format("Option friendly name '%s' conflicts with %s: %s.",//
                    friendlyName, existing.getDisplayName(), existing.getDescription()));

        for (String alias : option.getAliases()) {
            existing = prefixMap.get(friendlyName);
            if (existing != null)
                throw new IllegalUsageException(String.format("Option alias name '%s' conflicts with %s: %s.",//
                        alias, existing.getDisplayName(), existing.getDescription()));
        }

        int position = option.getArgPosition();
        if (position > 0) {
            existing = positionMap.get(position);
            if (existing != null)
                throw new IllegalUsageException(String.format("Option friendly name '%s' conflicts with %s: %s.",//
                        friendlyName, existing.getDisplayName(), existing.getDescription()));
        }

        nameMap.put(name, option);
        prefixMap.put(friendlyName, option);
        for (String alias : option.getAliases())
            prefixMap.put(alias, option);
        positionMap.put(position, option);
    }

    @Override
    public Map<String, IOption> getOptions() {
        return nameMap;
    }

    @Override
    public IOption getOption(String key, boolean canonicalForm) {
        if (canonicalForm)
            return nameMap.get(key);
        else
            return prefixMap.get(key);
    }

    @Override
    public Map<String, IOption> findOptions(String prefix) {
        Map<String, IOption> map = new LinkedHashMap<String, IOption>();
        Entry<String, IOption> entry = prefixMap.floorEntry(prefix);
        while (entry != null) {
            // prefixMap.
            prefixMap.higherEntry(e);
        }

        return null;
    }

    /**
     * @param possible
     *            prefix of option name
     * @exception CLIException
     *                if option does not exist
     */
    public AbstractOption findOption(String name)
            throws CLIException {
        if (name.isEmpty())
            throw new CLIException("option name is empty");
        if (name.startsWith("no-"))
            name = name.substring(3);
        if (prefixMap.containsKey(name))
            return (AbstractOption) prefixMap.get(name);
        List<String> fullNames = Iterables.toList(prefixMap.ceilingKeys(name));
        if (fullNames.isEmpty())
            throw new CLIException("no such option: " + name);
        if (fullNames.size() > 1) {
            StringBuilder cands = new StringBuilder();
            for (String nam : fullNames) {
                cands.append(nam);
                cands.append('\n');
                nam = prefixMap.higherKey(nam);
                if (nam == null || !nam.startsWith(name))
                    break;
            }
            throw new CLIException("ambiguous option " + name + ": \n" + cands.toString());
        }
        String fullname = fullNames.get(0);
        return (AbstractOption) prefixMap.get(fullname);
    }

    public Set<String> getNames(IOption option) {
        Set<String> names = new LinkedHashSet<String>();
        for (Entry<String, IOption> e : prefixMap.entrySet()) {
            if (e.getValue() != option)
                continue;
            String name = e.getKey();
            names.add(name);
        }
        return names;
    }

    /**
     * @param name
     *            (H-)name or alias.
     * @return <code>true</code> if specified name or alias hasn't been used, or it's a weak name.
     * @see Option#name()
     * @see Option#alias()
     */
    public boolean isReusable(String name) {
        if (!prefixMap.containsKey(name))
            return true;
        if (weaks.contains(name))
            return true;
        return false;
    }

    private Object _parseOptVal(AbstractOption opt, String optarg)
            throws ParseException {
        Class<?> valtype = opt.getType();
        Object optval = null;

        if (optarg == null) {
            optarg = opt.defaultVal;
            if (optarg.isEmpty())
                throw new ParseException("Option value expected: " + opt.getFriendlyName());
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
                throw new ParseException(String.format("Can\'t parse option %s of %s with argument %s",
                        opt.getFriendlyName(), valtype, optarg), e);
            }
        if (key != null)
            optval = new Pair<String, Object>(key, optval);
        return optval;
    }

    private int loadCall(CT object, MethodOption optcall, List<String> args, int off)
            throws CLIException, ParseException {
        int argc = optcall.getParameterCount();
        int rest = args.size() - off;
        if (argc > rest)
            throw new CLIException("not enough parameters for function-option " + optcall.getFriendlyName());
        String[] argv = args.subList(off, off + argc).toArray(new String[0]);
        try {
            optcall.call(object, argv);
        } catch (ReflectiveOperationException e) {
            throw new CLIException(e.getMessage(), e);
        }
        return argc;
    }

}
