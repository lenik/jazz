package net.bodz.bas.net.util;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.repr.path.IBasicTokenQueue;
import net.bodz.bas.t.variant.IVariant;

public class CompositeSettings
        implements ISettings,
                   ISettingParsable {

    Map<String, ISettings> map = new LinkedHashMap<>();
    Map<String, ISettingParsable> parsableMap = new LinkedHashMap<>();
    ISettings _default;
    ISettingParsable defaultParsable;
    Set<String> names = new LinkedHashSet<>();

    public CompositeSettings add(String name, @NotNull ISettingParsable settings) {
        add(name, (ISettings) settings);
        if (name == null)
            defaultParsable = settings;
        else
            parsableMap.put(name, settings);
        return this;
    }

    public CompositeSettings add(String name, @NotNull ISettings settings) {
        if (name == null) {
            if (_default == null)
                throw new DuplicatedKeyException("(default)");
            else
                _default = settings;

            for (String n : settings.getSettingNames())
                if (!names.add(n))
                    throw new DuplicatedKeyException(n);
        } else {
            if (map.containsKey(name))
                throw new DuplicatedKeyException(name);
            map.put(name, settings);

            for (String n : settings.getSettingNames()) {
                String qName = name + "." + n;
                if (!names.add(qName))
                    throw new DuplicatedKeyException(qName);
            }
        }
        return this;
    }

    public boolean remove(String name) {
        if (name == null) {
            if (_default == null)
                return false;
            names.removeAll(_default.getSettingNames());
            _default = null;
            defaultParsable = null;
        } else {
            ISettings sub = map.remove(name);
            if (sub == null)
                return false;
            else
                parsableMap.remove(name);
            for (String n : sub.getSettingNames()) {
                String qName = name + "." + n;
                names.remove(qName);
            }
        }
        return true;
    }

    public void clear() {
        map.clear();
        parsableMap.clear();
        names.clear();
    }

    @NotNull
    @Override
    public Set<String> getSettingNames() {
        return Collections.unmodifiableSet(names);
    }

    @Override
    public Object getSetting(@NotNull String qName) {
        int dot = qName.indexOf('.');
        if (dot != -1) {
            String parent = qName.substring(0, dot);
            ISettings sub = map.get(parent);
            if (sub != null) {
                String name = qName.substring(dot + 1);
                if (sub.isSettingDefined(name))
                    return sub.getSetting(name);
            }
        }
        if (_default != null)
            return _default.getSetting(qName);
        else
            return null;
    }

    @Override
    public boolean setSetting(@NotNull String qName, Object value) {
        int dot = qName.indexOf('.');
        if (dot != -1) {
            String parent = qName.substring(0, dot);
            ISettings sub = map.get(parent);
            if (sub != null) {
                String name = qName.substring(dot + 1);
                if (sub.isSettingDefined(name))
                    return sub.setSetting(name, value);
            }
        }
        if (_default != null)
            return _default.setSetting(qName, value);
        else
            return false;
    }

    @Override
    public boolean parseSetting(@NotNull String qName, @NotNull IBasicTokenQueue args)
            throws ParseException {
        int dot = qName.indexOf('.');
        if (dot != -1) {
            String parent = qName.substring(0, dot);
            ISettingParsable sub = parsableMap.get(parent);
            if (sub != null) {
                String name = qName.substring(dot + 1);
                if (sub.isSettingDefined(name))
                    return sub.parseSetting(name, args);
            }
        }
        if (defaultParsable != null)
            return defaultParsable.parseSetting(qName, args);
        else
            return false;
    }

    @Override
    public boolean setSettingVar(@NotNull String qName, @NotNull IVariant var) {
        int dot = qName.indexOf('.');
        if (dot != -1) {
            String parent = qName.substring(0, dot);
            ISettingParsable sub = parsableMap.get(parent);
            if (sub != null) {
                String name = qName.substring(dot + 1);
                if (sub.isSettingDefined(name))
                    return sub.setSettingVar(name, var);
            }
        }
        if (defaultParsable != null)
            return defaultParsable.setSettingVar(qName, var);
        else
            return false;
    }

}
