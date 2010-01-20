package net.bodz.bas.cli;

import java.lang.reflect.AnnotatedElement;
import java.util.Collection;
import java.util.Map;

import net.bodz.bas.cli.a.Option;
import net.bodz.bas.cli.a.OptionGroup;
import net.bodz.bas.exceptions.CreateException;
import net.bodz.bas.type.util.Types;

public abstract class _Option<T> implements ScriptField<T> {

    protected String name; // listDetai l
    protected final String hname; // list-detail
    public final Option o;

    protected final boolean weak;
    protected final Class<?> type;
    protected final boolean multi;
    protected Class<T> valtype;

    protected final TypeParser parser;
    protected final ItemTypeParser valparser;
    protected final Checker check;

    protected final String optgrp;

    @SuppressWarnings("unchecked")
    public _Option(String reflectName, AnnotatedElement elm, Class<?> type, OptionGroup optgrp) {
        Option option = elm.getAnnotation(Option.class);
        String optionName = option.name();
        if (!optionName.isEmpty()) {
            this.weak = optionName.startsWith(".");
            if (weak)
                optionName = optionName.substring(1);
            this.hname = option.name();
            this.name = Strings.dehyphenatize(hname);
        } else {
            this.name = reflectName;
            this.hname = Strings.hyphenatize(reflectName);
            this.weak = false; // default strict.
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
                throw new CLIError("valtype can only be specified on Array/Collection/Map types: " 
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

                ValidateBy checkBy = elm.getAnnotation(ValidateBy.class);
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
                throw new CLIError("can\'t init option " + reflectName, e); 
            } catch (CLIError e) {
                throw new CLIError("can\'t init option " + reflectName, e); 
            } catch (ParseException e) {
                throw new CLIError("can\'t init option " + reflectName, e); 
            }
        }
    }

    @Override
    public String getName() {
        return name;
    }

    public boolean isWeak() {
        return weak;
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
