package net.bodz.bas.cli;

import java.lang.reflect.AnnotatedElement;
import java.util.Collection;
import java.util.Map;

import net.bodz.bas.cli.a.CheckBy;
import net.bodz.bas.cli.a.Option;
import net.bodz.bas.cli.a.OptionGroup;
import net.bodz.bas.cli.a.ParseBy;
import net.bodz.bas.lang.err.CheckException;
import net.bodz.bas.lang.err.CreateException;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.lang.script.ScriptField;
import net.bodz.bas.nls.AppNLS;
import net.bodz.bas.types.Checker;
import net.bodz.bas.types.TypeParser;
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
    protected final Checker        check;

    protected final String         optgrp;

    @SuppressWarnings("unchecked")
    public _Option(String name, AnnotatedElement elm, Class<?> type,
            OptionGroup optgrp) {
        Option option = elm.getAnnotation(Option.class);
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

        ParseBy parseBy = elm.getAnnotation(ParseBy.class);
        Class<? extends TypeParser> parserClass = null;
        String parserParam = null;
        if (parseBy != null) {
            parserClass = parseBy.value();
            parserParam = parseBy.param();
            if (parserClass == TypeParser.class)
                parserClass = null;
            if (parserParam.isEmpty())
                parserParam = null;
        }

        if (type.isArray() && parserClass == null)
            valtype = (Class<T>) type.getComponentType();
        if (option.valtype() != void.class)
            valtype = (Class<T>) option.valtype();
        multi = valtype != null;
        if (multi) {
            if (!(isArray() || isCollection() || isMap()))
                throw new CLIError(AppNLS.getString("_Option.errValTtype") //$NON-NLS-1$
                        + type);
        } else
            valtype = (Class<T>) type;

        if (type == CallInfo.class) {
            parser = null;
            valparser = null;
            check = null;
        } else {
            try {
                TypeParser parser0;
                if (parserParam == null)
                    parser0 = Types.getClassInstance(parserClass);
                else
                    parser0 = Types.getClassInstance(parserClass, parserParam);
                parser = Util.guessParser(parser0, valtype);
                if (parser instanceof ItemTypeParser)
                    valparser = (ItemTypeParser) parser;
                else
                    valparser = null;

                CheckBy checkBy = elm.getAnnotation(CheckBy.class);
                Class<? extends Checker> check0 = null;
                if (checkBy != null) {
                    check0 = checkBy.value();
                    if (check0 == Checker.class)
                        check = null;
                    else {
                        String checkinfo = checkBy.param();
                        if (checkinfo.isEmpty())
                            check = Types.getClassInstance(check0);
                        else
                            check = Types.getClassInstance(check0, checkinfo);
                    }
                } else
                    check = null;
            } catch (CreateException e) {
                throw new CLIError(
                        AppNLS.getString("_Option.cantInitOption") + name, e); //$NON-NLS-1$
            } catch (CLIError e) {
                throw new CLIError(
                        AppNLS.getString("_Option.cantInitOption") + name, e); //$NON-NLS-1$
            } catch (ParseException e) {
                throw new CLIError(
                        AppNLS.getString("_Option.cantInitOption") + name, e); //$NON-NLS-1$
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
            try {
                check.check(val);
            } catch (CheckException e) {
                throw new ParseException(e);
            }
        return val;
    }

    public Object parse(String s, Object index) throws ParseException {
        Object val;
        if (valparser != null)
            val = valparser.parse(index, s);
        else
            val = parser.parse(s);
        if (check != null)
            try {
                check.check(val);
            } catch (CheckException e) {
                throw new ParseException(e);
            }
        return val;
    }

}
