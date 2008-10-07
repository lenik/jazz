package net.bodz.bas.cli;

import java.util.Collection;
import java.util.Map;

import net.bodz.bas.cli.a.Option;
import net.bodz.bas.cli.a.OptionGroup;
import net.bodz.bas.lang.err.CreateException;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.lang.script.ScriptField;
import net.bodz.bas.types.TypeParser;
import net.bodz.bas.types.ValueCheck;
import net.bodz.bas.types.util.Strings;
import net.bodz.bas.types.util.Types;

public abstract class _Option<T> implements ScriptField<T> {

    protected String               name;
    protected final String         hname;
    public final Option            o;

    protected final Class<?>       type;
    protected final boolean        multi;
    protected Class<T>             valtype;

    protected final TypeParser     parser;
    protected final ItemTypeParser valparser;
    protected final ValueCheck     check;

    protected final String         optgrp;

    @SuppressWarnings("unchecked")
    public _Option(String name, Option option, Class<?> type, OptionGroup optgrp) {
        if (!option.name().isEmpty()) {
            this.hname = option.name();
            this.name = Strings.dehyphenatize(hname);
        } else {
            this.name = name;
            this.hname = Strings.hyphenatize(name);
        }
        this.o = option;
        this.type = type;
        this.optgrp = optgrp == null ? null : optgrp.value();

        Class<? extends TypeParser> parser0 = option.parser();
        if (parser0 == TypeParser.class)
            parser0 = null;

        if (type.isArray() && parser0 == null)
            valtype = (Class<T>) type.getComponentType();
        if (option.valtype() != void.class)
            valtype = (Class<T>) option.valtype();
        multi = valtype != null;
        if (multi) {
            if (!(isArray() || isCollection() || isMap()))
                throw new CLIError(
                        "valtype can only be specified on Array/Collection/Map types: "
                                + type);
        } else
            valtype = (Class<T>) type;

        if (type == CallInfo.class) {
            parser = null;
            valparser = null;
            check = null;
        } else {
            try {
                parser = Util.guessParser(Types.getClassInstance(parser0),
                        valtype);
                if (parser instanceof ItemTypeParser)
                    valparser = (ItemTypeParser) parser;
                else
                    valparser = null;

                Class<? extends ValueCheck> check0 = option.check();
                if (check0 == ValueCheck.class)
                    check = null;
                else {
                    String checkinfo = option.checkinfo();
                    if (checkinfo.isEmpty())
                        check = Types.getClassInstance(check0);
                    else
                        check = Types.getClassInstance(check0, checkinfo);
                }
            } catch (CreateException e) {
                throw new CLIError(e.getMessage(), e);
            } catch (CLIError e) {
                throw new CLIError("option " + name + ": " + e.getMessage(), e);
            }
        }

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Class<T> getType() {
        return valtype;
    }

    public String getCLIName() {
        return hname;
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

    public Object parse(String s, Object index) throws ParseException {
        Object val;
        if (valparser != null)
            val = valparser.parse(index, s);
        else
            val = parser.parse(s);
        if (check != null)
            check.check(val);
        return val;
    }

}
