package net.bodz.bas.cli;

import java.lang.reflect.AnnotatedElement;
import java.util.Collection;
import java.util.Map;

import net.bodz.bas.c.string.Strings;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.program.OptionGroup;
import net.bodz.bas.meta.util.ValueType;
import net.bodz.bas.potato.traits.AbstractElement;
import net.bodz.bas.trait.Traits;
import net.bodz.bas.traits.IParser;
import net.bodz.bas.traits.IValidator;
import net.bodz.bas.traits.ValidateException;

public abstract class _Option<T>
        extends AbstractElement {

    protected String name; // listDetai l
    protected final String hname; // list-detail

    protected String vnam;
    protected String doc;
    protected boolean hidden;
    protected boolean required;
    protected String[] aliases;
    protected int fileIndex;
    protected String defaultVal;

    protected final boolean weak;
    protected final Class<?> type;
    protected final boolean multi;
    protected Class<T> valtype;

    protected final IParser<?> parser;
    protected final ItemTypeParser valparser;
    protected final IValidator<Object> check;

    protected final String optgrp;

    @SuppressWarnings("unchecked")
    public _Option(String reflectName, AnnotatedElement elm, Class<?> type, OptionGroup optgrp) {
        super(type, reflectName);
        String optionName = "XXX"; // option.name();
        if (!optionName.isEmpty()) {
            this.weak = optionName.startsWith(".");
            if (weak)
                optionName = optionName.substring(1);
            this.hname = "XXX"; // option.name();
            this.name = Strings.dehyphenatize(hname);
        } else {
            this.name = reflectName;
            this.hname = Strings.hyphenatize(reflectName);
            this.weak = false; // default strict.
        }
        this.type = type;
        this.optgrp = optgrp == null ? null : optgrp.value();

        Class<?> parserClass = null; // XXX
        if (type.isArray() && parserClass == null)
            valtype = (Class<T>) type.getComponentType();

        ValueType valueTypeAnn = elm.getAnnotation(ValueType.class);
        if (valueTypeAnn != null)
            valtype = (Class<T>) valueTypeAnn.value();
        multi = valtype != null;
        if (multi) {
            if (!(isArray() || isCollection() || isMap()))
                throw new CLIError("valtype can only be specified on Array/Collection/Map types: " + type);
        } else
            valtype = (Class<T>) type;

        if (type == MethodCall.class) {
            parser = null;
            valparser = null;
            check = null;
        } else {
            try {
                parser = Traits.getTrait(elm, IParser.class);
                if (parser instanceof ItemTypeParser)
                    valparser = (ItemTypeParser) parser;
                else
                    valparser = null;

                check = Traits.getTrait(elm, IValidator.class);
            } catch (CLIError e) {
                throw new CLIError("can't init option " + reflectName, e);
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

//    @Override
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

    public Object parse(String s)
            throws ParseException {
        Object val = parser.parse(s);
        if (check != null)
            try {
                check.validate(val);
            } catch (ValidateException e) {
                throw new ParseException(e);
            }
        return val;
    }

    public Object parse(String s, Object index)
            throws ParseException {
        Object val;
        if (valparser != null)
            val = valparser.parse(index, s);
        else
            val = parser.parse(s);
        if (check != null)
            try {
                check.validate(val);
            } catch (ValidateException e) {
                throw new ParseException(e);
            }
        return val;
    }

}
