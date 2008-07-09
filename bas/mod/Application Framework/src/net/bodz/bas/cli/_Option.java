package net.bodz.bas.cli;

import static net.bodz.bas.cli.Util.getClassInstance;
import static net.bodz.bas.cli.Util.guessParser;
import static net.bodz.bas.cli.Util.hyphen;

import java.util.Collection;
import java.util.Map;

import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.lang.script.ScriptField;

public abstract class _Option<T> implements ScriptField<T> {

    protected final String        name;
    public final Option           o;

    protected final Class<?>      type;
    protected final boolean       multi;
    protected Class<T>            valtype;

    protected final TypeParser<T> parser;
    protected final ValueCheck     check;

    protected final String        optgrp;

    @SuppressWarnings("unchecked")
    public _Option(String name, Option option, Class<T> type, OptionGroup optgrp) {
        if (!option.name().isEmpty())
            name = option.name();
        this.name = hyphen(name);
        this.o = option;
        this.type = type;
        this.optgrp = optgrp == null ? null : optgrp.value();

        valtype = (Class<T>) option.valtype();
        multi = valtype != void.class;
        if (multi) {
            if (!(isArray() || isCollection() || isMap()))
                throw new CLIError(
                        "valtype can only be specified on Array/Collection/Map types: "
                                + type);
        } else
            valtype = type;

        if (type == CallInfo.class) {
            parser = null;
            check = null;
        } else {
            try {
                parser = guessParser(getClassInstance(option.parser()), valtype);
            } catch (CLIError e) {
                throw new CLIError("option " + name + ": " + e.getMessage(), e);
            }
            String checkinfo = option.checkinfo();
            check = checkinfo.isEmpty() ? getClassInstance(option.check())
                    : getClassInstance(option.check(), checkinfo);
        }

    }

    @Override
    public Class<T> getType() {
        return valtype;
    }

    public String getCanonicalName() {
        return name;
    }

    public Class<?> getFieldType() {
        return type;
    }

    public boolean isBoolean() {
        return valtype == boolean.class || valtype == Boolean.class;
    }

    public int getParameterCount() {
        if (isBoolean())
            return 0;
        return 1;
    }

    public boolean isArray() {
        return multi && type.isArray();
    }

    public boolean isCollection() {
        return multi && Collection.class.isAssignableFrom(type);
    }

    public boolean isMap() {
        return multi && Map.class.isAssignableFrom(type);
    }

    public String getGroup() {
        return optgrp;
    }

    public Object parse(String s) throws ParseException {
        Object val = parser.parse(s);
        if (check != null)
            check.check(val);
        return val;
    }

}
